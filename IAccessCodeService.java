import java.sql.Timestamp;

public interface IAccessCodeService {
    public enum AccessCodeOperationResult
    {
        INTERNAL_ERROR,
        APPLICATION_NOT_ALLOWED,
        INVALID_SECRET,
        INVALID_CODE,
        CODE_EXPIRED,

        CODE_VALIDATED_SUCCESSFULLY,
        INSUFFICIENT_INFORMATION,

    }
    public AccessCodeView generateAccessCode(AccessCodeModel accessCode);
    public AccessCodeOperationResult validateAccessCode(AccessCodeModel accessCode);

    public  boolean isApplicationAllowedForAccessCode(AccessModel access , String requestingApplication);

    public boolean isAccessCodeAlive(Timestamp aliveUntil);
}
