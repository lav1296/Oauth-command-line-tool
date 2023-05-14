import java.sql.Timestamp;

public class AccessCodeModel {
    private String code;
    private String accessName;
    private Timestamp createdTime;

    public AccessCodeModel() {
        this.code = "";
        this.accessName="";
    }

    @Override
    public String toString() {
        return "AccessCodeModel{" +
                "code='" + code + '\'' +
                ", accessName='" + accessName + '\'' +
                ", createdTime=" + createdTime +
                ", application='" + application + '\'' +
                ", secret='" + secret + '\'' +
                ", aliveUntill=" + aliveUntill +
                '}';
    }


    private String application;

    private String secret;

    private Timestamp aliveUntill;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = sanitizeInput(accessName);
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getAliveUntill() {
        return aliveUntill;
    }

    public void setAliveUntill(Timestamp aliveUntill) {
        this.aliveUntill = aliveUntill;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = sanitizeInput(secret);
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = sanitizeInput(application);
    }

    private String sanitizeInput(String input){
        return input.trim();
    }

}

