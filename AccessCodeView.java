public class AccessCodeView {
    public enum AccessCodeOperationResult
    {
        INTERNAL_ERROR,
        INSUFFICIENT_INFORMATION,
        APPLICATION_NOT_ALLOWED,
        INVALID_SECRET,
        INVALID_CODE,
        CODE_EXPIRED,
        INVALID_ACCESS,
    }
    private AccessCodeModel accessCodeModel;
    private AccessCodeOperationResult accessCodeOperationResult;
    public AccessCodeView.AccessCodeOperationResult getAccessCodeOperationResult() {
        return accessCodeOperationResult;
    }

    public void setAccessCodeOperationResult(AccessCodeView.AccessCodeOperationResult accessCodeOpResult) {
        this.accessCodeOperationResult = accessCodeOpResult;
    }

    public AccessCodeModel getAccessCodeModel() {
        return accessCodeModel;
    }

    public void setAccessCodeModel(AccessCodeModel accessCodeModel) {
        this.accessCodeModel = accessCodeModel;
    }
}
