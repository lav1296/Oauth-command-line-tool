import java.sql.Timestamp;

public class AccessCodeDBMock implements IAccessCodePersistence {
    public void  saveAccessCode(AccessCodeModel accessCodeModel)throws Exception{
        if (accessCodeModel.getAccessName()=="AppStore"&&accessCodeModel.getSecret()=="abc12345"){
            throw new NoDbFoundException();
        }
        if (accessCodeModel.getAccessName()=="GIT" && accessCodeModel.getSecret()=="abc12345"){
            throw new Exception("access code Already present");

        }
        if (accessCodeModel.getCode()=="IntellIjGit"){
            return;
        }
    }
    public AccessCodeModel getAccessCodeInformationByCode(String accessCode)throws Exception{
        if (accessCode=="abc123"){
            throw new NoDbFoundException();
        }
        if (accessCode=="x1y2z3"){
            throw new AccessCodeNotFound();
        }
        //access code with a never expiring code set to 100000 minutes alive time

        if (accessCode=="alive1"){
            AccessCodeModel accessCodeModel=new AccessCodeModel();
            accessCodeModel.setAccessName("IntellIjGit");
            accessCodeModel.setSecret("12345678");
           long currentMilli=System.currentTimeMillis();
            accessCodeModel.setAliveUntill(new Timestamp(currentMilli+(100000*60*1000)));
            return accessCodeModel;
        }

        //access code with a already expired code set to 10 minutes prior to current time
        if (accessCode=="expired"){
            AccessCodeModel accessCodeModel=new AccessCodeModel();
            accessCodeModel.setAccessName("IntellIjGit");
            accessCodeModel.setSecret("12345678");
            long currentMilli=System.currentTimeMillis();
            accessCodeModel.setAliveUntill(new Timestamp(currentMilli-(10*60*1000)));
            return accessCodeModel;
        }

        //access code whose access was deleted
        if (accessCode=="noAcces"){
            AccessCodeModel accessCodeModel=new AccessCodeModel();
            accessCodeModel.setAccessName("VsCodeGit");
            accessCodeModel.setSecret("12345678");
            long currentMilli=System.currentTimeMillis();
            accessCodeModel.setAliveUntill(new Timestamp(currentMilli-(10*60*1000)));
            return accessCodeModel;
        }

        //accessCode for which access Db is not Found
        if (accessCode=="noDbAc"){
            AccessCodeModel accessCodeModel=new AccessCodeModel();
            accessCodeModel.setAccessName("Docker");
            accessCodeModel.setSecret("12345678");
            long currentMilli=System.currentTimeMillis();
            accessCodeModel.setAliveUntill(new Timestamp(currentMilli-(10*60*1000)));
            return accessCodeModel;
        }
        return new AccessCodeModel();

    }


}
