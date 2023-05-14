public interface IAccessPersistence {

    public void  saveAccess(AccessModel accessModel)throws Exception;
    public AccessModel getAccessInformationByName(String AccessName)throws Exception;
}
