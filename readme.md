# Oauth

The following project can be  used for One time authentication between 2 application to exchange sensitive data exposed over external ports or network,it is very simple to use, perform the following steps to create and validate your access code
1. Create an Access , new Access is created for any application seeking access,  for example Intellij wants to access git application intellij will create a new Access named IntellIjGIT and enter Git as the allowed application as the access is created the system will provide a secret to create new oauth codes as and when required.
2. Create an Access code , access codes can be created by any application which has the right Access name and Access Secret ,enter the access name IntellIjGIT and the 8 digit secret generated in the previous step the program will provide a 6 digit authentication code that is  alive for  5 minutes.
3. Validate the generated Access code , in our Example Intellij created a 6 digit code, sends it over to GIT along with a request. GIT will then validate this code , by providing the six digit code and its application name, if any other application name is provided it will not authenticate as the access does not include that application in the allowed application name.
4. one code can be used multiple times until it expires.

All data is stored in internal memory, using HasMaps, valid only for the session.

## Usage
Execute  the following commands to run the program from the root of the project A1

```bash
javac *.java 
java Main 
```

To run all the testCases , execute the following command
```bash
javac *.java 
java Main test
```

## Using Pre loaded Data
* One Access is created already to use at runtime,
steps to use it 
1. Run the program 
2. Select option 2 and enter
3. enter "IntellIjGit"
4. enter "secret"
5. program will generate a secret to generate an access code 


* One never expiring AccessCode is already created, steps to use it 
1. Run the program
2. Select 3 and enter
3. Enter "test12"
4. Enter "git"
5. the program will validate the given code

