import java.util.UUID;

public class AccessService implements IAccessService {
    private IAccessPersistence accessPersistence;
    public AccessService(IAccessPersistence accessPersistence) {
    this.accessPersistence=accessPersistence;
    }

    public IAccessPersistence getAccessPersistence() {
        return accessPersistence;
    }

    public void setAccessPersistence(IAccessPersistence accessPersistence) {
        this.accessPersistence = accessPersistence;
    }

    public AccessViewModel addAccess(AccessModel accessModel){
        if (!validateAccessModel(accessModel)){
            AccessViewModel accessViewModel=new AccessViewModel();
            accessViewModel.setAccessOperationResult(AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING);
            return accessViewModel;
        }

        accessPersistence=this.getAccessPersistence();
        AccessViewModel accessViewModel=new AccessViewModel();
        accessModel.setAccessId(UUID.randomUUID().toString());
        accessModel.setSecret(GeneratorUtility.generateRandomString(10));
        try {
            accessPersistence.saveAccess(accessModel);
        }
        catch(AccessAlreadyExistsException e ){

            accessViewModel.setAccessOperationResult(AccessViewModel.AccessOperationResult.ACCESS_ALREADY_PRESENT);
            return accessViewModel;
        }
        catch(Exception e ){

            accessViewModel.setAccessOperationResult(AccessViewModel.AccessOperationResult.INTERNAL_ERROR);
            return accessViewModel;
        }
        accessViewModel.setAccessModel(accessModel);
        return accessViewModel;


    }

    public AccessViewModel getAccessByName(String accessName){
        AccessModel access;
        AccessViewModel accessView=new AccessViewModel();
        try {
            access=accessPersistence.getAccessInformationByName(accessName);
        }
        catch (AccessDoesNotExistException e){
            accessView.setAccessOperationResult(AccessViewModel.AccessOperationResult.ACCESS_DOES_NOT_EXIST);
            return accessView;
        }
        catch (Exception e){
            accessView.setAccessOperationResult(AccessViewModel.AccessOperationResult.INTERNAL_ERROR);
            return accessView;
        }
        accessView.setAccessModel(access);
        return accessView;
    }
    private boolean validateAccessModel(AccessModel accessModel){
        if (accessModel.getName()==null ||accessModel.getName()==""||accessModel.getAllowedApplication()==null){
            return false;
        }
        return true ;

    }

}
