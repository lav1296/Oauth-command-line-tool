public class AccessViewModelTest {

    public static void InitTest(){
        AccessViewModel accessView = new AccessViewModel();
        if (accessView.getAccessOperationResult()==null){
            System.out.println("PASS - AccessViewModelTest  INITTEST");
        }
        else{
            System.out.println("FAIL - AccessViewModelTest  INITTEST");
        }

    }
    public static void SetterGetterTest(){
        AccessViewModel accessView = new AccessViewModel();
        AccessModel accessModel = new AccessModel();

        String name="IntellijGit";
        String secret="secret1";

        accessModel.setName(name);
        accessModel.setSecret(secret);
        accessView.setAccessOperationResult(AccessViewModel.AccessOperationResult.ACCESS_ALREADY_PRESENT);

        accessView.setAccessModel(accessModel);



        if (accessView.getAccessModel()!=null&& accessView.getAccessModel().getName().equals(name)&&
                accessView.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.ACCESS_ALREADY_PRESENT.toString())){
            System.out.println("PASS - AccessViewModelTest SetterGetterTest");
        }else{
            System.out.println("FAIL - AccessViewModelTest SetterGetterTest");
        }

    }
}
