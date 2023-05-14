import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;

public class AccessCodeDB implements IAccessCodePersistence {
    private Map<String, AccessCodeModel> accessCodeMap;

    public AccessCodeDB() {
        super();
        accessCodeMap=new HashMap<String, AccessCodeModel>();
        initData();

    }

    public Map<String, AccessCodeModel> getAccessCodeMap() {
        return accessCodeMap;
    }

    public void setAccessCodeMap(Map<String, AccessCodeModel> accessCodeMap) {
        this.accessCodeMap = accessCodeMap;
    }
    public void  saveAccessCode(AccessCodeModel accessCodeModel)throws Exception{
         Map<String, AccessCodeModel> allAccessCode=this.getAccessCodeMap();
         if (allAccessCode==null){
             throw new NoDbFoundException();
         }
         if (allAccessCode.get(accessCodeModel.getCode())!=null){
            throw new Exception("access code Already present");
         }
         allAccessCode.put(accessCodeModel.getCode(),accessCodeModel);
         this.setAccessCodeMap(allAccessCode);
         return;


    }
    public AccessCodeModel getAccessCodeInformationByCode(String accessCode)throws Exception{
        Map<String, AccessCodeModel> allAccessCode=this.getAccessCodeMap();
        if (allAccessCode==null){
            throw new NoDbFoundException();
        }
        AccessCodeModel accessCodeModel=allAccessCode.get(accessCode);
        if (accessCodeModel==null){
            throw new AccessCodeNotFound();
        }
        return accessCodeModel;

    }
    //method that loads 1 test data for runtime usage.
    //not used for testcase validation
    private void initData(){
        AccessCodeModel accessCodeModel=new AccessCodeModel();
        accessCodeModel.setCode("test12");
        accessCodeModel.setAccessName("IntellijGit");
        long currentMillisecondTime=System.currentTimeMillis();
        accessCodeModel.setCreatedTime(new Timestamp(currentMillisecondTime));
        accessCodeModel.setAliveUntill(new Timestamp(currentMillisecondTime+(20*60*1000)));
        accessCodeMap.put(accessCodeModel.getCode(),accessCodeModel);


    }


}
