public class ExecuteTest {

    public static void execute(){
        //AccessServiceTest
        //AccessServiceTest addAccess method Tests
        AccessServiceTest.addAccessTestInvalidInputMissingAllowedApplication();
        AccessServiceTest.addAccessTestInvalidInputMissingAccessName();
        AccessServiceTest.addAccessTestNoDBFound();
        AccessServiceTest.addAccessTestAccessAlreadyPresent();
        AccessServiceTest.addAccessTestAccessAccessSaved();

        //AccessServiceTest GetInformationByAccessName method Tests
        AccessServiceTest.GetInformationByAccessNameTestNoDbFound();
        AccessServiceTest.GetInformationByAccessNameTestNoAccessFound();
        AccessServiceTest.GetInformationByAccessNameTestAccessFound();


        //AccessCodeServiceTests
        AccessCodeServiceTest.generateAccessCodeTestInvalidInputMissingAccessName();
        AccessCodeServiceTest.generateAccessCodeTestInvalidInputMissingAccessSecret();
        AccessCodeServiceTest.generateAccessCodeTestAccessDBNotFound();
        AccessCodeServiceTest.generateAccessCodeTestAccessNotPresent();
        AccessCodeServiceTest.generateAccessCodeTestInvalidSecret();
        AccessCodeServiceTest.generateAccessCodeDBNotFound();
        AccessCodeServiceTest.generateAccessCodeAlreadyPresent();
        AccessCodeServiceTest.generateAccessCodeAccessCodeSavedToDB();
        AccessCodeServiceTest.validateAccessCodeTestInvalidInput();
        AccessCodeServiceTest.validateAccessCodeTestInvalidInputMissingAccessCode();
        AccessCodeServiceTest.validateAccessCodeTestAccessCodeDBNotFound();
        AccessCodeServiceTest.validateAccessCodeTestAccessCodeNotFound();
        AccessCodeServiceTest.validateAccessCodeTestAccessNotFound();
        AccessCodeServiceTest.validateAccessCodeTestAccessDbNotFound();
        AccessCodeServiceTest.validateAccessCodeTestApplicationNotAllowed();
        AccessCodeServiceTest.validateAccessCodeTestCodeExpired();
        AccessCodeServiceTest.validateAccessCodeTestCodeSuccessfullyValidated();
        AccessCodeServiceTest.isApplicationAllowedForAccessCodeTestApplicationAllowed();
        AccessCodeServiceTest.isApplicationAllowedForAccessCodeTestApplicationNotAllowed();
        AccessCodeServiceTest.isAccessCodeAliveTestAccessCodeAlive();
        AccessCodeServiceTest.isAccessCodeAliveTestAccessCodeExpired();


        //GeneratorUtility tests
        //generateRandomString method test
        GeneratorUtilityTest.generateRandomStringTest();

        AccessCodeModelTest.InitTest();
        AccessCodeModelTest.SetterGetterTest();

        AccessModelTest.InitTest();
        AccessModelTest.SetterGetterTest();

        //Main test
        //addAccess Method test
        MainTest.addAccessTest();
        //generate new code test
        MainTest.generateNewCodeTest();

        AccessCodeViewTest.InitTest();
        AccessCodeViewTest.SetterGetterTest();

        AccessViewModelTest.InitTest();
        AccessViewModelTest.SetterGetterTest();



    }
}
