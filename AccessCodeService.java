import java.sql.Timestamp;
import java.util.List;

public class AccessCodeService implements IAccessCodeService {

   private IAccessService accessOperation;
   private IAccessCodePersistence accessCodePersistence;

    public AccessCodeService(IAccessService accessOperation, IAccessCodePersistence accessCodePersistence) {
        this.accessOperation = accessOperation;
        this.accessCodePersistence = accessCodePersistence;
    }

    public AccessCodeView generateAccessCode(AccessCodeModel accessCode){
        AccessCodeView accessCodeView=new AccessCodeView();
        AccessModel access;
        if (!validateAccessCodeModel(accessCode)){

            accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.INSUFFICIENT_INFORMATION);
            return  accessCodeView;
        }
        AccessViewModel accessView=accessOperation.getAccessByName(accessCode.getAccessName());

        if (accessView.getAccessOperationResult()!=null){
            if (accessView.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.ACCESS_DOES_NOT_EXIST.toString())){
                accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.INVALID_ACCESS);
                return accessCodeView;
            }
            accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR);
            return accessCodeView;
        }

        access=accessView.getAccessModel();
        if (!accessCode.getSecret().equals(access.getSecret())){
            accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.INVALID_SECRET);
            return accessCodeView;
        }
        accessCode.setAccessName(access.getName());


        accessCode.setCode(GeneratorUtility.generateRandomString(8));

        //referred to the documentation for the following block of code
        //https://docs.oracle.com/javase/8/docs/api/java/sql/Timestamp.html
        //to understand working of timestamp in java
        long currentMillisecondTime=System.currentTimeMillis();
        accessCode.setCreatedTime(new Timestamp(currentMillisecondTime));

        //add 5 minute interval to time , as the access code is only valid for 5 minutes, can be modified dynamically
        accessCode.setAliveUntill(new Timestamp(currentMillisecondTime+(5*60*1000)));
        try{
            accessCodePersistence.saveAccessCode(accessCode);
        }
        catch(Exception e ){
            accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR);
            return accessCodeView;
        }
        accessCodeView.setAccessCodeModel(accessCode);

        return accessCodeView;

    }
    public IAccessCodeService.AccessCodeOperationResult validateAccessCode(AccessCodeModel accessCode){
        AccessCodeModel accessCodeInfo;
        if (!validateAccessCodeInformation(accessCode)){
            return IAccessCodeService.AccessCodeOperationResult.INSUFFICIENT_INFORMATION;

        }

        try{
            accessCodeInfo=accessCodePersistence.getAccessCodeInformationByCode(accessCode.getCode());
        }
        catch(AccessCodeNotFound e){
            return IAccessCodeService.AccessCodeOperationResult.INVALID_CODE;
        }
        catch (Exception e ){
            return IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR;
        }

        //get Access information
        AccessViewModel accessView=accessOperation.getAccessByName(accessCodeInfo.getAccessName());

        if (accessView.getAccessOperationResult()!=null){
            if (accessView.getAccessOperationResult()== AccessViewModel.AccessOperationResult.ACCESS_DOES_NOT_EXIST){

                return IAccessCodeService.AccessCodeOperationResult.INVALID_CODE;
            }
            return IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR;
        }

       AccessModel access=accessView.getAccessModel();
        if (!isApplicationAllowedForAccessCode(access,accessCode.getApplication())){
            return IAccessCodeService.AccessCodeOperationResult.APPLICATION_NOT_ALLOWED;
        }
        if (!isAccessCodeAlive(accessCodeInfo.getAliveUntill())){
            return IAccessCodeService.AccessCodeOperationResult.CODE_EXPIRED;
        }
        return IAccessCodeService.AccessCodeOperationResult.CODE_VALIDATED_SUCCESSFULLY;

    }

    private boolean validateAccessCodeModel(AccessCodeModel accessCode){
        if (accessCode.getAccessName()==null ||accessCode.getAccessName()==""||accessCode.getSecret()==null||accessCode.getSecret()==""){
            return false;
        }
        return true;
    }

    private boolean validateAccessCodeInformation(AccessCodeModel accessCode){
        if (accessCode.getCode()==null ||accessCode.getCode()==""||accessCode.getApplication()==null||accessCode.getApplication()==""){
            return false;
        }
        return true;
    }

    public  boolean isApplicationAllowedForAccessCode(AccessModel access , String requestingApplication){
        List<String> allowedApplication=access.getAllowedApplication();
        for (String app:allowedApplication){
            if (app.equalsIgnoreCase(requestingApplication)){
                return true;
            }
        }

        return false;
    }

    public boolean isAccessCodeAlive(Timestamp aliveUntil){
        Timestamp currentTimestamp=new Timestamp(System.currentTimeMillis());
        return currentTimestamp.before(aliveUntil);
    }
}
