import java.util.ArrayList;
import java.util.List;

public class AccessModel {
    private String accessId;
    private String secret;
    private String Name;
    private List<String> AllowedApplication;



    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    @Override
    public String toString() {
        return "AccessModel{" +
                "accessId='" + accessId + '\'' +
                ", secret='" + secret + '\'' +
                ", Name='" + Name + '\'' +
                ", AllowedApplication=" + AllowedApplication +
                '}';
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = sanitizeInput(secret);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = sanitizeInput(name);
    }

    public List<String> getAllowedApplication() {
        return AllowedApplication;
    }

    public void setAllowedApplication(List<String> allowedApplication) {
        AllowedApplication = allowedApplication;
    }

    private String sanitizeInput(String input){
        return input.trim();
    }
}
