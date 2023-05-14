public class AccessCodeModelTest {

    public static void InitTest(){
        AccessCodeModel test= new AccessCodeModel();
        if (test.getAccessName()==""){
            System.out.println("PASS - AccessCodeModelTest  INITTEST");
        }
        else{
            System.out.println("FAIL - AccessCodeModelTest  INITTEST");
        }

    }
    public static void SetterGetterTest(){
        AccessCodeModel test= new AccessCodeModel();
        String code="abc123";
        String accessName="intellij";
        String secret="poikjuyh";
        test.setCode(code);
        test.setAccessName(accessName);
        test.setSecret(secret);
        if (test.getAccessName()==accessName&&test.getCode()==code&&test.getSecret()==secret){
            System.out.println("PASS - AccessCodeModelTest SetterGetterTest");
        }else{
            System.out.println("FAIL - AccessCodeModelTest SetterGetterTest");
        }

        //Sanitize input test SetterGetterTest
        test= new AccessCodeModel();
        String accessName2=" intellij ";
        String accessName2Sanitized="intellij";
        test.setAccessName(accessName2);

        if (test.getAccessName().equals(accessName2Sanitized)){
            System.out.println("PASS - AccessCodeModelTest SetterGetterTest Sanitize input test");
        }else{
            System.out.println("FAIL - AccessCodeModelTest SetterGetterTest Sanitize input test");
        }


    }
}
