package cscie97.asn4.ecommerce.authentication;

/**
 * Defines authentication service interface
 * @author ssingh
 *
 */
public interface IAuthenticationService {
	/**
	 * Creates Service with given id, name and description. 
	 * Throws exception if access token is invalid or user does not 
	 * have permission to invoke the method.
	 * @param id service id
	 * @param name service name
	 * @param description service description
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void createService(String id,String name,String description,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Creates permission with given id, name and description. 
	 * Attaches permission with service if serviceId as identifier. 
	 * Throws exception if access token is invalid or user does not have 
	 * permission to invoke the method.
	 * @param serviceId service id
	 * @param id permission id
	 * @param name permission name
	 * @param description permission description
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void createPermission(String serviceId,String id,String name,String description,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Creates role with given id, name and description. 
	 * Throws exception if access token is invalid or user does not
	 * have permission to invoke the method.
	 * @param id role id
	 * @param name role name
	 * @param description role description
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void createRole(String id,String name,String description,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Adds entilement ( role or permission) to a role. Throws exception 
	 * if access token is invalid or user does not have permission to 
	 * invoke the method.
	 * @param roleId role id
	 * @param entitlementId entitlement id
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void addEntitlementToRole(String roleId,String entitlementId,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Creates user with given id and name. Throws exception if access 
	 * token is invalid or user does not have permission to invoke the method.
	 * @param id user id
	 * @param name user name
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void createUser(String id,String name,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Associates and user name and password with a user. Throws exception 
	 * if access token is invalid or user does not have permission to invoke 
	 * the method.
	 * @param id user id
	 * @param userName login name
	 * @param password password
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void addCredentialToUser(String id,String userName,String password,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Adds entilement ( role or permission) to a user. Throws exception 
	 * if access token is invalid or user does not have permission to invoke 
	 * the method
	 * @param userId user id
	 * @param entitlementId entitlement id
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	void addEntitlementIdToUser(String userId,String entitlementId,String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Logs in into application store using user name and password. 
	 * Returns access token on success. Throws exception if authentication fails.
	 * @param userName login name
	 * @param password password
	 * @return access token id
	 * @throws AuthenticationException
	 */
	String login(String userName,String password) throws AuthenticationException;
	/**
	 * Invalidates the access token. Throws exception if access token is invalid
	 * @param accessToken access token id
	 * @throws InvalidAccessTokenException
	 */
	void logout(String accessToken) throws InvalidAccessTokenException;
	/**
	 * Checks if user associated with the access token has permission to 
	 * invoke method with a given permission id. Throws exception if 
	 * access token is invalid or user does not have permission to 
	 * invoke the method with the given permission Id
	 * @param accessTokenId access token id
	 * @param permissionId permission id
	 * @return true if token has permission to invoke method, otherwise false
	 * @throws InvalidAccessTokenException
	 * @throws AccessDeniedException
	 */
	boolean checkAccess(String accessTokenId,String permissionId)
			throws InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Calls accept() function for user,role, permission, service, 
	 * access tokens of visitable interface to print object’s information on 
	 * standard output. 
	 * @param visitor visitor object
	 */
	void printAuthenticationInfo(Visitor visitor);
}
