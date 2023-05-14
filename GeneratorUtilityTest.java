public class GeneratorUtilityTest {

    public static void generateRandomStringTest(){

        //Generated String Length Test
        String random= GeneratorUtility.generateRandomString(20);
        if (random.length()==20){
            System.out.println("PASS - GeneratorUtilityTest generateRandomStringTest - Generated String Length Test ");
        }else{
            System.out.println("FAIL - GeneratorUtilityTest generateRandomStringTest - Generated String Length Test ");
            System.out.println("Want "+20+" got "+random.length() );
        }
    }
}
