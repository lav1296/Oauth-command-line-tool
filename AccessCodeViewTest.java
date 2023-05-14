public class AccessCodeViewTest {

    public static void InitTest(){
        AccessCodeView accessCodeView = new AccessCodeView();
        if (accessCodeView.getAccessCodeOperationResult()==null){
            System.out.println("PASS - AccessCodeViewTest  INITTEST");
        }
        else{
            System.out.println("FAIL - AccessCodeViewTest  INITTEST");
        }

    }
    public static void SetterGetterTest(){
        AccessCodeView accessCodeView= new AccessCodeView();
        AccessCodeModel accessCodeModel=new AccessCodeModel();
        String code="123";
        String secret="secret1";
        accessCodeModel.setCode(code);
        accessCodeModel.setSecret(secret);
        accessCodeView.setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult.CODE_EXPIRED);

        accessCodeView.setAccessCodeModel(accessCodeModel);



        if (accessCodeView.getAccessCodeModel()!=null&& accessCodeView.getAccessCodeModel().getCode().equals(code)&&
                accessCodeView.getAccessCodeOperationResult().toString().equals(AccessCodeView.AccessCodeOperationResult.CODE_EXPIRED.toString())){
            System.out.println("PASS - AccessCodeViewTest SetterGetterTest");
        }else{
            System.out.println("FAIL - AccessCodeViewTest SetterGetterTest");
        }

    }

}
