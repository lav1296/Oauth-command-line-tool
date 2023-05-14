import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main{
	public static void main(String args[]){
		if (args.length==0 ||args[0].equalsIgnoreCase("run")){

			displayHelpOptions();
			System.out.println("please select one of the  Oath options");
			int choice=100;
			IAccessPersistence accessPersistence=new AccessDb();
			IAccessCodePersistence accessCodePersistence=new AccessCodeDB();
			IAccessService accessOperation=new AccessService(accessPersistence);
			IAccessCodeService accessCodeOperation=new AccessCodeService(accessOperation,accessCodePersistence);
			try {
				while (choice != 0) {
					displayOptions();
					Scanner input = new Scanner(System.in);
					choice = input.nextInt();
					if (choice == 1) {
						AccessModel accessModel = GetNewAccessInformation();
						String displayResult=addAccess(accessOperation,accessModel);
						System.out.println(displayResult);

						}
						if (choice == 2) {
							AccessCodeModel accessCode = GetNewAccessCodeInformation();
							String displayResult=GenerateNewCode(accessCodeOperation,accessCode);
							System.out.println(displayResult);

						}
						if (choice == 3) {
							AccessCodeModel accessCode = GetValidateAccessCodeInformation();
							IAccessCodeService.AccessCodeOperationResult result = accessCodeOperation.validateAccessCode(accessCode);
							System.out.println(result.toString());


						}

					}
			}
			catch(InputMismatchException e){
				System.out.println("Please the input in correct format :- integer or string and try again");
			}
			catch(Exception e){
				System.out.println("Something went wrong , please try again");
			}
			return;


		}
		if (args[0].equalsIgnoreCase("test")){
			System.out.println("executing all tests");
			ExecuteTest.execute();
            return;
		}

		System.out.println("please provide valid arguments");
		displayHelpOptions();



	}

	private static void displayHelpOptions(){
		System.out.println("execute the command \"java Main test\" , to execute the test cases");
		System.out.println("execute the command \"java Main run \" or \"java Main\" , to run the program");
		System.out.println("the following project is used for One time authentication between 2 application to exchange " +
				"sensitive data exposed over external ports or network,"+" by authenticating the consumer"+
				"it is very simple to use"+
				"perform the following steps to create and validate your access code"
		);
		System.out.println("1.create an Access , new Access is created for any application seeking access "+
				"for example Intellij wants to access git application"+
				"intellij will create a new Access named IntellIjGIT and enter Git as the allowed application"+
				"as the access is created the program will provide a 8 digit secret to create new oauth codes as and when required"
		);
		System.out.println("2.create an Access code , access codes can be created by any application which has the right Access name and Access Secret"
				+"enter the access name IntellIjGIT and the 8 digit secret generated in the previous step "+
				"the system will provide a 6 digit authentication code that is only alive for 5 minutes "
		);
		System.out.println("3. Validate the generated Access code , In our Example Intellij created a 6 digit code "+
				"Sends it over to GIT along with a request. GIT will then validate this code ," +
				" by providing the six digit code and its application name, if any other application name is provided it will not authenticate " +
				"as the access does not include that application in the allowed application name"
		);
	}
	private static void displayOptions(){
		System.out.println("Enter 1 to create a new Access");
		System.out.println("Enter 2 to create a new Access Code using existing Access Information");
		System.out.println("Enter 3 to validate an Access code");
		System.out.println("Enter 0 to exit");
	}
	private static AccessModel GetNewAccessInformation(){
		System.out.println("Enter Access Name");
		Scanner input= new Scanner(System.in);
		String accessName= input.nextLine();
		System.out.println("Enter number of applications allowed to use the access code");
		int noOfApplications=input.nextInt();
		input= new Scanner(System.in);
		List<String> allowedApplications=new ArrayList<String>();
		for (int i=1;i<=noOfApplications;i++){
			System.out.println("Enter name of number "+i+" application");
			allowedApplications.add(input.nextLine());
		}
		AccessModel accessModel=new AccessModel();
		accessModel.setName(accessName);
		accessModel.setAllowedApplication(allowedApplications);
		return accessModel;
	}
	public static String DisplayAccessViewModelInformation(AccessViewModel accessViewModel){
		AccessModel accessModel=accessViewModel.getAccessModel();
		String display="please save the details of the access to generate access code in future \n"+
		"AccessName: " +accessModel.getName()+" Access Secret: "+ accessModel.getSecret()+"\n"
				+"AllowedApplications\n";

		List <String> allowedapplications =accessModel.getAllowedApplication();

		String Applications="";
		for (String x :allowedapplications){
			Applications=Applications+x+"\n";
		}


		return display+Applications;

	}
	private static AccessCodeModel GetNewAccessCodeInformation(){
		AccessCodeModel accessCodeModel=new AccessCodeModel();
		System.out.println("Enter Access Name");
		Scanner input= new Scanner(System.in);
		accessCodeModel.setAccessName(input.nextLine());
		System.out.println("Enter Access Secret");
		accessCodeModel.setSecret(input.nextLine());

		return accessCodeModel;
	}
	public static String DisplayAccessCodeViewInformation(AccessCodeView accessCodeView){
		AccessCodeModel accessCode=accessCodeView.getAccessCodeModel();
		return "the given code is only valid for 5 minutes\n"+accessCode.getCode();

	}
	private static AccessCodeModel GetValidateAccessCodeInformation(){
		AccessCodeModel accessCodeModel=new AccessCodeModel();
		System.out.println("Enter Access Code");
		Scanner input= new Scanner(System.in);
		accessCodeModel.setCode(input.nextLine());
		System.out.println("Enter Application Requesting access");
		accessCodeModel.setApplication(input.nextLine());

		return accessCodeModel;
	}
	public static String addAccess(IAccessService accessOperation,AccessModel access){
		AccessViewModel accessViewModel = accessOperation.addAccess(access);
		if (accessViewModel.getAccessOperationResult() != null) {
			return(accessViewModel.getAccessOperationResult().toString());

		}
		return DisplayAccessViewModelInformation(accessViewModel);
	}

	public static String GenerateNewCode(IAccessCodeService accessCodeOperation,AccessCodeModel accessCode){
		AccessCodeView GeneratedAccessCode = accessCodeOperation.generateAccessCode(accessCode);

		if (GeneratedAccessCode.getAccessCodeOperationResult() != null) {
			return (GeneratedAccessCode.getAccessCodeOperationResult().toString());

		}
		return DisplayAccessCodeViewInformation(GeneratedAccessCode);
	}

}