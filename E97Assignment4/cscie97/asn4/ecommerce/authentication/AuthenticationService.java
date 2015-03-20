package cscie97.asn4.ecommerce.authentication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import cscie97.asn4.ecommerce.authentication.Entitlement.TYPE;

/**
 * This class implements IAuthenticationService interface. It keeps map 
 * of services, entitlements, users and access tokens with key as their 
 * identifiers. The keys of these maps other than access tokens are stored 
 * in upper case to make search case insensitive
 * @author ssingh
 *
 */
public class AuthenticationService implements IAuthenticationService {
	
	Map<String,Service> serviceMap;
	Map<String,Entitlement> entitlementMap;
	Map<String,User> userMap;
	Map<String,String> loginMap;
	Map<String,AccessToken> accessTokenMap;
	String adminUid;
	
	private static AuthenticationService instance = null;
	
	private AuthenticationService(){
		serviceMap = new HashMap<String,Service>();
		entitlementMap = new HashMap<String,Entitlement>();
		userMap = new HashMap<String,User>();
		loginMap = new HashMap<String,String>();
		accessTokenMap = new HashMap<String,AccessToken>();
	}
	
	/**
	 * @return - singleton AuthenticationService object
	 */
	public static AuthenticationService getInstance()
	{
		if(instance == null) 
		{
			instance = new AuthenticationService();
		}
      	return instance;
	}

	
	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#createService(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createService(String id, String name, String description,
			String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "define_service")){
			Service service = new Service(id, name, description);
			serviceMap.put(id.toUpperCase(),service);
		}

	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#createPermission(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createPermission(String serviceId, String id, String name,
			String description, String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "define_permission")){
			Service service = serviceMap.get(serviceId.toUpperCase());
			if(service == null){
				//throw exception
			}
			Permission permission = new Permission(id, name, description,TYPE.PERMISSION);
			service.addPermission(permission);	
			entitlementMap.put(id.toUpperCase(),permission);
		}

	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#createRole(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createRole(String id, String name, String description,
			String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "define_role")){
			Role role = new Role(id, name, description,TYPE.ROLE);
			entitlementMap.put(id.toUpperCase(),role);
		}

	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#addEntitlementToRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addEntitlementToRole(String roleId, String entitlementId,
			String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "add_entitlement")){
			Role role = (Role)entitlementMap.get(roleId.toUpperCase());
			Entitlement entitlement = entitlementMap.get(entitlementId.toUpperCase());
			if(role != null && entitlement != null){
				role.addEntitlement(entitlement);
			}
		}

	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#createUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createUser(String id, String name, String accessToken) 
			throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "create_user")){
			User user = new User(id, name);
			userMap.put(id.toUpperCase(),user);
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#addCredentialToUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addCredentialToUser(String id, String userName,
			String password, String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "add_credential_to_user")){
			User user = userMap.get(id.toUpperCase());
			if(user != null){
				user.setCredential(userName, password);
				loginMap.put(userName, id);
			}
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public String login(String userName, String password) 
			throws AuthenticationException {
		if(userName.compareTo("admin") == 0 && 	
				password.compareTo("admin") == 0){
			adminUid = UUID.randomUUID().toString();
			return adminUid;
		}
		String userId = loginMap.get(userName);
		if(userId == null){
			throw new AuthenticationException(userName);
		}
		User user = userMap.get(userId.toUpperCase());
		if(user == null){
			throw new AuthenticationException(userName);
		}
		if(user.checkCredential(userName,password)){
			String guid = UUID.randomUUID().toString();
			AccessToken accessToken = new AccessToken(guid, user, true);
			accessTokenMap.put(guid, accessToken);
			return guid;		
		}
		throw new AuthenticationException(userName);
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#logout(java.lang.String)
	 */
	@Override
	public void logout(String accessTokenId) 
			throws InvalidAccessTokenException {
		AccessToken accessToken = accessTokenMap.get(accessTokenId);
		if(accessToken == null){
			throw new InvalidAccessTokenException(accessTokenId,0,null,false);
		}
		accessToken.setState(false);
	}
	
	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#addEntitlementIdToUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addEntitlementIdToUser(String userId, String entitlementId,
			String accessToken) throws InvalidAccessTokenException, AccessDeniedException {
		if(checkAccess(accessToken, "add_entitlement_to_user")){
			User user = userMap.get(userId.toUpperCase());
			if(user != null){
				Entitlement entitlement = entitlementMap.get(entitlementId.toUpperCase());
				if(entitlement != null){
					user.addEntitlement(entitlement);
				}
			}
		}	
	}
	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#checkAccess(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkAccess(String accessTokenId, String permissionId) 
			throws InvalidAccessTokenException, AccessDeniedException {
		if(adminUid.compareTo(accessTokenId) == 0)
			return true;
		AccessToken accessToken = accessTokenMap.get(accessTokenId);
		if(!accessToken.isValid())
			throw new InvalidAccessTokenException(accessTokenId,accessToken.getExpirationTime(),accessToken.getUser().getName(),accessToken.isState());
		User user = accessToken.getUser();
		List<Entitlement> entitlements = user.getEntitlements();
		for(Entitlement entitlement:entitlements){
			if(entitlement.getType() == TYPE.PERMISSION){
				if(entitlement.getId().compareToIgnoreCase(permissionId) == 0)
					return true;
			}
			else if(entitlement.getType() == TYPE.ROLE){
				RoleIterator it = ((Role)entitlement).getIterator();
				while(it.hasNext()){
					Entitlement childEntitlement = it.currentItem();
					if(childEntitlement.getType() == TYPE.PERMISSION && 
							childEntitlement.getId().compareToIgnoreCase(permissionId) == 0)
						return true;
					it.next();
				}
			}
		}
		throw new AccessDeniedException(user.getName(),permissionId);
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.IAuthenticationService#printAuthenticationInfo(cscie97.asn4.ecommerce.authentication.Visitor)
	 */
	@Override
	public void printAuthenticationInfo(Visitor visitor) {
		//print services and permissions
		System.out.println("Services : ");
		System.out.println("-------------");
		Iterator<Entry<String, Service>> ServiceMapIt = serviceMap.entrySet().iterator();
		while (ServiceMapIt.hasNext()) {
			ServiceMapIt.next().getValue().accept(visitor);
			System.out.println();
		}
		
		//print roles
		System.out.println("Roles : ");
		System.out.println("-------------");
		Iterator<Entry<String, Entitlement>> entitlementMapIt = entitlementMap.entrySet().iterator();
		while (entitlementMapIt.hasNext()) {
			Entitlement childEntitlement = entitlementMapIt.next().getValue();
			if(childEntitlement.getType() == TYPE.ROLE)
				((Role)childEntitlement).accept(visitor);
			System.out.println();
		}
		
		//print users
		System.out.println("Users : ");
		System.out.println("-------------");
		Iterator<Entry<String, User>> userMapIt = userMap.entrySet().iterator();
		while (userMapIt.hasNext()) {
			userMapIt.next().getValue().accept(visitor);
			System.out.println();
		}
		
		//print AccessTokens
		System.out.println("Access Tokens : ");
		System.out.println("----------------------------");
		Iterator<Entry<String, AccessToken>> accessTokenIt = accessTokenMap.entrySet().iterator();
		while (accessTokenIt.hasNext()) {
			accessTokenIt.next().getValue().accept(visitor);
			System.out.println();
		}
	}
}
