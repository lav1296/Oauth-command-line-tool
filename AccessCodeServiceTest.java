import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccessCodeServiceTest {

    static IAccessCodeService accessCodeOperation;


    private static void Initialize() {
        IAccessCodePersistence accessCodePersistence = new AccessCodeDBMock();
        IAccessPersistence accessPersistence = new AccessDbMock();
        IAccessService accessOperation = new AccessService(accessPersistence);
        accessCodeOperation = new AccessCodeService(accessOperation, accessCodePersistence);
    }

    public static void generateAccessCodeTestInvalidInputMissingAccessName() {
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;

        //Invalid Input, Missing Access Name
        accessCode.setSecret("123456");
        accessCode.setApplication("Docker");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult().toString()
                .equals(AccessCodeView.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString())) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeTestInvalidInputMissingAccessName - Invalid Input, Missing AccessName ");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeTestInvalidInputMissingAccessName - Invalid Input, Missing AccessName ");
            System.out.println("Want " + AccessCodeView.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString() + " got " +
                    accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeTestInvalidInputMissingAccessSecret() {


        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;

        accessCode.setAccessName("MacGit");
        accessCode.setApplication("Docker");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult().toString()
                .equals(AccessCodeView.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString())) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeTestInvalidInputMissingAccessSecret - Invalid Input, Invalid Input, Missing Access Secret");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeTestInvalidInputMissingAccessSecret - Invalid Input, Invalid Input, Missing Access Secret");
            System.out.println("Want " + AccessCodeView.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString() + " got " +
                    accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeTestAccessDBNotFound() {

        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;

        accessCode.setCode("abc123");
        accessCode.setAccessName("Docker");
        accessCode.setApplication("Docker");
        accessCode.setSecret("123456");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult() != null
                && accessCodeView.getAccessCodeOperationResult() == AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeTestAccessDBNotFound - Access DB Not Found");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeTestAccessDBNotFound - Access DB Not Found");
            System.out.println("want " + AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR + " got " + accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeTestAccessNotPresent() {

        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;

        accessCode.setCode("abc123");
        accessCode.setAccessName("VsCodeGit");
        accessCode.setSecret("123456");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult() != null
                && accessCodeView.getAccessCodeOperationResult() == AccessCodeView.AccessCodeOperationResult.INVALID_ACCESS) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeTestAccessNotPresent - Access not Present, Invalid Access");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeTestAccessNotPresent - Access not Present, Invalid Access");
            System.out.println("want " + AccessCodeView.AccessCodeOperationResult.INVALID_ACCESS + " got " + accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeTestInvalidSecret() {

        //Invalid Secret
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;
        accessCode.setCode("abc123");
        accessCode.setAccessName("IntellIjGit");
        accessCode.setSecret("1234567");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult() != null
                && accessCodeView.getAccessCodeOperationResult() == AccessCodeView.AccessCodeOperationResult.INVALID_SECRET) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeTestInvalidSecret - Invalid Secret");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeTestInvalidSecret - Invalid Secret");
            System.out.println("want " + AccessCodeView.AccessCodeOperationResult.INVALID_SECRET + " got " + accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeDBNotFound() {

        //AccessCode Db Not Found
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;
        accessCode.setCode("abc123");
        accessCode.setAccessName("AppStore");
        accessCode.setSecret("abc12345");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult() != null
                && accessCodeView.getAccessCodeOperationResult() == AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeDBNotFound - Invalid Secret");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeDBNotFound - Invalid Secret");
            System.out.println("want " + AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR + " got " + accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeAlreadyPresent() {

        //AccessCode already present
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;

        accessCode = new AccessCodeModel();
        accessCodeView = new AccessCodeView();
        accessCode.setCode("abc123");
        accessCode.setAccessName("GIT");
        accessCode.setSecret("abc12345");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        if (accessCodeView.getAccessCodeOperationResult() != null
                && accessCodeView.getAccessCodeOperationResult() == AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR) {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeAlreadyPresent - AccessCode already present");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeAlreadyPresent - AccessCode already present");
            System.out.println("want " + AccessCodeView.AccessCodeOperationResult.INTERNAL_ERROR + " got " + accessCodeView.getAccessCodeOperationResult().toString());
        }
    }

    public static void generateAccessCodeAccessCodeSavedToDB() {

        //HappyCase Access Code saved to Db
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        AccessCodeView accessCodeView;
        accessCode.setAccessName("IntellIjGit");
        accessCode.setSecret("12345678");
        accessCodeView = accessCodeOperation.generateAccessCode(accessCode);
        AccessCodeModel accessCodeNew = accessCodeView.getAccessCodeModel();
        if (accessCodeNew.getAccessName() != "IntellIjGit" || accessCodeNew.getAliveUntill() == null || accessCodeNew.getCode() == null || accessCodeNew.getCreatedTime() == null) {
            System.out.println("FAIL - AccessCodeOperationTest generateAccessCodeAccessCodeSavedToDB - HappyCase Access Code saved to Db");
            System.out.println("got " + accessCodeNew.toString());
        } else {
            System.out.println("PASS - AccessCodeOperationTest generateAccessCodeAccessCodeSavedToDB - HappyCase Access Code saved to Db");

        }


    }

    public static void validateAccessCodeTestInvalidInput() {
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;

        //Invalid Input, Missing Application Name
        accessCode.setCode("abc123");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestInvalidInput - Invalid Input, Missing Application Name");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestInvalidInput - Invalid Input, Missing Application Name");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestInvalidInputMissingAccessCode() {

        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestInvalidInputMissingAccessCode - Invalid Input, Missing Access Code");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestInvalidInputMissingAccessCode - Invalid Input, Missing Access Code");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INSUFFICIENT_INFORMATION.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestAccessCodeDBNotFound() {

        //Access code DB not found
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("abc123");
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestAccessCodeDBNotFound - Access code DB not found");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestAccessCodeDBNotFound - Access code DB not found");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestAccessCodeNotFound() {

        //Access code not found
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode = new AccessCodeModel();
        accessCode.setCode("x1y2z3");
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INVALID_CODE.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestAccessCodeNotFound - Access code not found");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestAccessCodeNotFound - Access code not found");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INVALID_CODE.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestAccessNotFound() {
        //Access not found, INVALID_CODE
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("noAcces");
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INVALID_CODE.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestAccessNotFound - Access not found, INVALID_CODE");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTest - Access not found, INVALID_CODE");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INVALID_CODE.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestAccessDbNotFound() {
        //Access Db not found
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("noDbAc");
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestAccessDbNotFound - Access Db not found, INTERNAL_ERROR");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestAccessDbNotFound - Access not found, INTERNAL_ERROR");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.INTERNAL_ERROR.toString() + " got " +
                    opResult.toString());
        }
    }
    public static void validateAccessCodeTestApplicationNotAllowed() {
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("alive1");
        accessCode.setApplication("GIT");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.APPLICATION_NOT_ALLOWED.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestApplicationNotAllowed - Application not allowed");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestApplicationNotAllowed - Application not allowed");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.APPLICATION_NOT_ALLOWED.toString() + " got " +
                    opResult.toString());
        }
    }

    public static void validateAccessCodeTestCodeExpired() {
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("expired");
        accessCode.setApplication("Docker");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.CODE_EXPIRED.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestCodeExpired - Code Expired");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestCodeExpired - Code Expired");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.CODE_EXPIRED.toString() + " got " +
                    opResult.toString());
        }
    }
    public static void validateAccessCodeTestCodeSuccessfullyValidated() {

        //Happy Case , Code Successfully Validated
        Initialize();
        AccessCodeModel accessCode = new AccessCodeModel();
        IAccessCodeService.AccessCodeOperationResult opResult;
        accessCode.setCode("alive1");
        accessCode.setApplication("Docker");
        opResult = accessCodeOperation.validateAccessCode(accessCode);
        if (opResult.toString()
                .equals(IAccessCodeService.AccessCodeOperationResult.CODE_VALIDATED_SUCCESSFULLY.toString())) {
            System.out.println("PASS - AccessCodeOperationTest validateAccessCodeTestCodeSuccessfullyValidated - Happy Case , Code Successfully Validated");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest validateAccessCodeTestCodeSuccessfullyValidated - Happy Case , Code Successfully Validated");
            System.out.println("Want " + IAccessCodeService.AccessCodeOperationResult.CODE_VALIDATED_SUCCESSFULLY.toString() + " got " +
                    opResult.toString());
        }
    }


    public static void isApplicationAllowedForAccessCodeTestApplicationAllowed() {

        //Application Allowed"
        AccessModel accessModel = new AccessModel();
        boolean result;
        accessModel.setName("IntellIjGit");
        List<String> allowedApplication = new ArrayList<String>();
        allowedApplication.add("Docker");
        allowedApplication.add("Git");
        accessModel.setAllowedApplication(allowedApplication);
        result = accessCodeOperation.isApplicationAllowedForAccessCode(accessModel, "Docker");

        if (result == true) {
            System.out.println("PASS - AccessCodeOperationTest isApplicationAllowedForAccessCodeTestApplicationAllowed - Application Allowed");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest isApplicationAllowedForAccessCodeTestApplicationAllowed - Application Allowed");
            System.out.println("Want " + true + " Got " + result);
        }
    }
    public static void isApplicationAllowedForAccessCodeTestApplicationNotAllowed() {
        //Application Allowed"
        AccessModel accessModel = new AccessModel();
        boolean result;

        accessModel.setName("IntellIjGit");
        List<String> allowedApplication = new ArrayList<String>();
        allowedApplication= new ArrayList<String>();
        allowedApplication.add("Docker");
        allowedApplication.add("Git");
        accessModel.setAllowedApplication(allowedApplication);
        result=accessCodeOperation.isApplicationAllowedForAccessCode(accessModel,"kubernetes");

        if (result==false){
            System.out.println("PASS - AccessCodeOperationTest isApplicationAllowedForAccessCodeTestApplicationNotAllowed - Application not Allowed");
        }else{
            System.out.println("FAIL - AccessCodeOperationTest isApplicationAllowedForAccessCodeTestApplicationNotAllowed - Application not  Allowed");
            System.out.println("Want "+ false+" Got "+result);
        }



    }
    public static void isAccessCodeAliveTestAccessCodeAlive() {
        //Access code Alive"
        long currentMilli = System.currentTimeMillis();
        new Timestamp(currentMilli + (100000 * 60 * 1000));
        boolean result;
        result = accessCodeOperation.isAccessCodeAlive(new Timestamp(currentMilli + (100000 * 60 * 1000)));
        if (result == true) {
            System.out.println("PASS - AccessCodeOperationTest isAccessCodeAliveTestAccessCodeAlive - Access code Alive");
        } else {
            System.out.println("FAIL - AccessCodeOperationTest isAccessCodeAliveTestAccessCodeAlive - Access code Alive");
            System.out.println("Want " + true + " Got " + result);
        }
    }
    public static void isAccessCodeAliveTestAccessCodeExpired() {
        //Access code Expired"
        long currentMilli=System.currentTimeMillis();
        new Timestamp(currentMilli+(100000*60*1000));
        boolean result;

        result=accessCodeOperation.isAccessCodeAlive(new Timestamp(currentMilli-(100000*60*1000)));
        if (result==false){
            System.out.println("PASS - AccessCodeOperationTest isAccessCodeAliveTestAccessCodeExpired - Access code Expired");
        }else{
            System.out.println("FAIL - AccessCodeOperationTest isAccessCodeAliveTestAccessCodeExpired - Access code Expired");
            System.out.println("Want "+ false+" Got "+result);
        }

    }
}
