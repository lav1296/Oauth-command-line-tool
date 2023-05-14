public class AccessModelTest {

    public static void InitTest(){
        AccessModel test= new AccessModel();
        if (test.getName()==null){
            System.out.println("PASS - AccessModelTest  INITTEST");
        }
        else{
            System.out.println("FAIL - AccessModelTest  INITTEST");
        }

    }
    public static void SetterGetterTest(){
        AccessModel test= new AccessModel();

        String accessName="intellij";
        test.setName(accessName);

        if (test.getName()==accessName&&test.getName()==accessName){
            System.out.println("PASS - AccessModelTest SetterGetterTest");
        }else{
            System.out.println("FAIL - AccessModelTest SetterGetterTest");
        }

        //Sanitize input test SetterGetterTest
        test= new AccessModel();
        String accessName2=" intellij ";
        String accessName2Sanitized="intellij";
        test.setName(accessName2);

        if (test.getName().equals(accessName2Sanitized)){
            System.out.println("PASS - AccessModelTest SetterGetterTest Sanitize input test");
        }else{
            System.out.println("FAIL - AccessModelTest SetterGetterTest Sanitize input test");
        }


    }
}

