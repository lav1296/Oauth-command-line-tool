public interface IAccessCodePersistence {

    public void  saveAccessCode(AccessCodeModel accessCodeModel)throws Exception;
    public AccessCodeModel getAccessCodeInformationByCode(String AccessCode)throws Exception;
}
