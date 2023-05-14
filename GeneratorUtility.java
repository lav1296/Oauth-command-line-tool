import java.util.UUID;

public class GeneratorUtility {
    //code used from https://xperti.io/blogs/generate-random-string-in-java/
    // code in the above link shows to generate random 32 length string using uuid ,
    // I have modified and corrected (to replace "-" in place of "_")it ,
    // to provide a random string of any given length upto 32 characters
    public static String generateRandomString(int length){
         UUID uuid=UUID.randomUUID();
        return uuid.toString().replaceAll("-","").substring(0,length);
    }
}
