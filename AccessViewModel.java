import java.util.List;

public class AccessViewModel {
    private AccessModel accessModel;

    public enum AccessOperationResult
    {
        ACCESS_ALREADY_PRESENT,
        ACCESS_INFORMATION_MISSING,

        INTERNAL_ERROR,

        ACCESS_DOES_NOT_EXIST,
    }

    private AccessOperationResult accessOperationResult;

    public AccessOperationResult getAccessOperationResult() {
        return accessOperationResult;
    }

    public void setAccessOperationResult(AccessOperationResult accessOperationResult) {
        this.accessOperationResult = accessOperationResult;
    }

    public AccessModel getAccessModel() {
        return accessModel;
    }

    public void setAccessModel(AccessModel accessModel) {
        this.accessModel = accessModel;
    }

    @Override
    public String toString() {
        return "AccessViewModel{" +
                "accessModel=" + accessModel +
                ", accessOperationResult=" + accessOperationResult +
                '}';
    }
}

