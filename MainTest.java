public class MainTest {

    static IAccessPersistence accessPersistence=new AccessDbMock();
    static IAccessCodePersistence accessCodePersistence=new AccessCodeDBMock();
    static IAccessService accessOperation=new AccessService(accessPersistence);
    static IAccessCodeService accessCodeOperation=new AccessCodeService(accessOperation,accessCodePersistence);

    public static void addAccessTest(){

        String result;
        AccessModel accessModel=new AccessModel();
        accessModel.setName("Spotify");
        result=Main.addAccess(accessOperation,accessModel);

        if (result
                .equals(AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString())){
            System.out.println("PASS - MainTest addAccessTest - Invalid Input, missing Allowed application ");
        }else{
            System.out.println("Fail - MainTest addAccessTest - Invalid Input, missing Allowed application ");
            System.out.println("Want "+AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString()+"got "+
                    result);
        }
    }

    public static void generateNewCodeTest(){

        String result;
        AccessCodeModel accessCode=new AccessCodeModel();
        accessCode.setCode("abc123");
        accessCode.setAccessName("VsCodeGit");
        accessCode.setSecret("123456");
        result=Main.GenerateNewCode(accessCodeOperation,accessCode);

        if (result.equals( AccessCodeView.AccessCodeOperationResult.INVALID_ACCESS.toString())){
            System.out.println("PASS - MainTest GenerateNewCodeTest - Access not Present, Invalid Access");
        }else{
            System.out.println("FAIL - MainTest GenerateNewCodeTest- Access not Present, Invalid Access");
            System.out.println("want "+AccessCodeView.AccessCodeOperationResult.INVALID_ACCESS+" got "+result);
        }

    }


}
