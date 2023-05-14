import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class AccessDb implements IAccessPersistence {

    private Map<String, AccessModel> AccessList;

    public AccessDb() {
        super();
        AccessList=new HashMap<String, AccessModel>();
        initData();

    }

    public Map<String, AccessModel> getAccessList() {
        return AccessList;
    }

    public void setAccessList(Map<String, AccessModel> accessList) {
        AccessList = accessList;
    }
    public void  saveAccess(AccessModel accessModel)throws Exception{
        Map<String, AccessModel> accessMap=this.getAccessList();
        if (accessMap==null){
            throw new NoDbFoundException();
        }
        AccessModel access= accessMap.get(accessModel.getName());
        if (access!=null){
            throw new AccessAlreadyExistsException();
        }
        accessMap.put(accessModel.getName(), accessModel);
        this.setAccessList(accessMap);
        return;


    };
    public AccessModel getAccessInformationByName(String AccessName)throws Exception{
        Map<String, AccessModel> accessMap=this.getAccessList();
        if (accessMap==null){
            throw new NoDbFoundException();
        }
        AccessModel access= accessMap.get(AccessName);
        if (access==null){
            throw new AccessDoesNotExistException();
        }
        return access;
    }

    //method that loads 1 test data for runtime usage.
    //not used for testcase validation
    private void initData(){
        AccessModel accessModel=new AccessModel();
        accessModel.setName("IntellIjGit");
        accessModel.setSecret("secret");
        List<String> allowedApplication=new ArrayList<>();
        allowedApplication.add("git");
        accessModel.setAllowedApplication(allowedApplication);
        AccessList.put(accessModel.getName(),accessModel);


    }
}
