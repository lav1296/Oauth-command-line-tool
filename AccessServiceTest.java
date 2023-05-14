import java.util.ArrayList;
import java.util.List;

public class AccessServiceTest {
static IAccessService accessService;
//Initialize accessService object
public static void Initialize(){
     accessService=new AccessService(new AccessDbMock());

}

//testAddAccess
     public static void addAccessTestInvalidInputMissingAllowedApplication() {
          Initialize();
          AccessModel accessModel = new AccessModel();
          AccessViewModel accessView;


          //Invalid Input, missing Allowed application
          accessModel.setName("Spotify");
          accessView = accessService.addAccess(accessModel);
          if (accessView.getAccessOperationResult().toString()
                  .equals(AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString())) {
               System.out.println("PASS - AccessServiceTest addAccessTestInvalidInputMissingAllowedApplication - Invalid Input, missing Allowed application ");
          } else {
               System.out.println("Fail - AccessServiceTest addAccessTestInvalidInputMissingAllowedApplication - Invalid Input, missing Allowed application ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString() + "got " +
                       accessView.getAccessOperationResult().toString());
          }
     }

     public static void addAccessTestInvalidInputMissingAccessName() {

          //Invalid Input, missing access name
          Initialize();
          AccessModel accessModel = new AccessModel();
          AccessViewModel accessView;
          List<String> allowedApplication = new ArrayList<>();
          allowedApplication.add("Docker");
          accessModel.setAllowedApplication(allowedApplication);
          accessView = accessService.addAccess(accessModel);
          if (accessView.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString())) {
               System.out.println("PASS - AccessServiceTest addAccessTestInvalidInputMissingAccessName - Invalid Input, missing access name ");
          } else {
               System.out.println("Fail - AccessServiceTest addAccessTestInvalidInputMissingAccessName - Invalid Input, missing access name ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.ACCESS_INFORMATION_MISSING.toString() + "got " +
                       accessView.getAccessOperationResult().toString());
          }
     }
     public static void addAccessTestNoDBFound() {

          //No Db Found
          Initialize();
          AccessModel accessModel = new AccessModel();
          AccessViewModel accessView;
          accessModel = new AccessModel();
          List<String> allowedApplication = new ArrayList<>();
          allowedApplication.add("Spotify");
          accessModel.setName("Docker");
          accessModel.setAllowedApplication(allowedApplication);
          accessView = accessService.addAccess(accessModel);
          if (accessView.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.INTERNAL_ERROR.toString())) {
               System.out.println("PASS - AccessServiceTest addAccessTestNoDBFound - No Db Found ");
          } else {
               System.out.println("Fail - AccessServiceTest addAccessTestNoDBFound - No Db Found ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.INTERNAL_ERROR.toString() + "got " +
                       accessView.getAccessOperationResult().toString());
          }
     }
     public static void addAccessTestAccessAlreadyPresent() {
          //Access Already Present
          Initialize();
          AccessModel accessModel = new AccessModel();
          AccessViewModel accessView;
          accessModel = new AccessModel();
          List<String> allowedApplication = new ArrayList<>();
          accessModel.setName("IntellIjDocker");
          allowedApplication.add("Docker");
          accessModel.setAllowedApplication(allowedApplication);
          accessView = accessService.addAccess(accessModel);

          if (accessView.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.ACCESS_ALREADY_PRESENT.toString())) {
               System.out.println("PASS - AccessServiceTest addAccessTestAccessAlreadyPresent - Access Already Present");
          } else {
               System.out.println("Fail - AccessServiceTest addAccessTestAccessAlreadyPresent - Access Already Present ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.ACCESS_ALREADY_PRESENT.toString() + "got " +
                       accessView.getAccessOperationResult().toString());
          }
     }
     public static void addAccessTestAccessAccessSaved() {

          //Access gets saved to DB
          Initialize();
          AccessModel accessModel = new AccessModel();
          AccessViewModel accessView;
          accessModel = new AccessModel();
          List<String> allowedApplication = new ArrayList<>();
          accessModel.setName("IntellIjGit");
          allowedApplication.add("Intellij");
          accessModel.setAllowedApplication(allowedApplication);
          accessView=accessService.addAccess(accessModel);
          AccessModel accessModelNew=accessView.getAccessModel();
          if (accessModelNew==null||accessModelNew.getAccessId()==""||accessModelNew.getAllowedApplication()==null
                  ||accessModelNew.getSecret()==""||accessModelNew.getName()==""){
               System.out.println("Fail - addAccessTestAccessAccessSaved addAccessTest - Access gets saved to DB ");
               System.out.println("Got "+accessModel);
          }else{
               System.out.println("PASS - addAccessTestAccessAccessSaved addAccessTest - Access gets saved to DB");
          }



     }
     public static void GetInformationByAccessNameTestNoDbFound() {

          Initialize();
          AccessViewModel accessViewModel;


          //No Db Found
          accessViewModel = accessService.getAccessByName("Docker");
          if (accessViewModel.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.INTERNAL_ERROR.toString())) {
               System.out.println("PASS - AccessServiceTest GetInformationByAccessName - No Db Found ");
          } else {
               System.out.println("Fail - AccessServiceTest GetInformationByAccessName - No Db Found ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.INTERNAL_ERROR.toString() + "got " +
                       accessViewModel.getAccessOperationResult().toString());
          }
     }
     public static void GetInformationByAccessNameTestNoAccessFound() {

          //No Access Found Exception
          Initialize();
          AccessViewModel accessViewModel;
          accessViewModel = accessService.getAccessByName("VsCodeGit");
          if (accessViewModel.getAccessOperationResult().toString().equals(AccessViewModel.AccessOperationResult.ACCESS_DOES_NOT_EXIST.toString())) {
               System.out.println("PASS - AccessServiceTest GetInformationByAccessNameTestNoAccessFound - No Access Found ");
          } else {
               System.out.println("Fail - AccessServiceTest GetInformationByAccessNameTestNoAccessFound - No Access Found ");
               System.out.println("Want " + AccessViewModel.AccessOperationResult.ACCESS_DOES_NOT_EXIST.toString() + "got " +
                       accessViewModel.getAccessOperationResult().toString());
          }
     }
     public static void GetInformationByAccessNameTestAccessFound() {

          //AccessFound
          Initialize();
          AccessViewModel accessViewModel;
          accessViewModel=accessService.getAccessByName("IntellIjGit");
          AccessModel accessModel= accessViewModel.getAccessModel();

          if (accessModel==null||accessModel.getName()==""||accessModel.getAllowedApplication()==null||
                  accessModel.getSecret()==""||accessModel.getAccessId()==""){
               System.out.println("Fail - GetInformationByAccessNameTestAccessFound GetInformationByAccessName - AccessFound ");
               System.out.println("Got "+accessModel);
          }else{
               System.out.println("PASS - GetInformationByAccessNameTestAccessFound GetInformationByAccessName - AccessFound");
          }

     }

}
