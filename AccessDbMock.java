import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AccessDbMock implements IAccessPersistence {
    public void  saveAccess(AccessModel accessModel)throws Exception{
        if (accessModel.getName()=="Docker"){

            throw new NoDbFoundException();
        }
        if (accessModel.getName()=="IntellIjDocker"){
            throw new AccessAlreadyExistsException();
        }
        if (accessModel.getName()=="IntellIjGit"){
            return;
        }
    }
    public AccessModel getAccessInformationByName(String AccessName)throws Exception{
        AccessModel access=new AccessModel();
        if (AccessName=="Docker"){

            throw new NoDbFoundException();
        }
        if (AccessName=="VsCodeGit"){
            throw new AccessDoesNotExistException();
        }
        if (AccessName=="IntellIjGit"){

            access.setName("IntellIjGit");
            List<String> applicationList=new ArrayList<>();
            applicationList.add("Docker");
            applicationList.add("Intellij");
            access.setAllowedApplication(applicationList);
            access.setSecret("12345678");
            access.setAccessId("098765");

        }
        if (AccessName=="AppStore"){

            access.setName("AppStore");
            List<String> applicationList=new ArrayList<>();
            applicationList.add("Docker");
            applicationList.add("Intellij");
            access.setAllowedApplication(applicationList);
            access.setSecret("abc12345");
            access.setAccessId("098765");

        }
        if (AccessName=="GIT"){

            access.setName("AppStore");
            List<String> applicationList=new ArrayList<>();
            applicationList.add("Docker");
            applicationList.add("Intellij");
            access.setAllowedApplication(applicationList);
            access.setSecret("abc12345");
            access.setAccessId("098765");

        }
        return access;
    }
}
