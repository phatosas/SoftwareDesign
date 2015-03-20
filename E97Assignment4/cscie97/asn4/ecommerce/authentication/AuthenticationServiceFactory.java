package cscie97.asn4.ecommerce.authentication;

/**
 * The public class which acts as a factory of authentication service object.
 * This class has a factory method which returns handle to authentication 
 * service singleton object.
 * @author ssingh
 *
 */
public class AuthenticationServiceFactory {
	/**
	 * factory method to return handle to authentication  service singleton object.
	 * @return returns handle to authentication  service singleton object.
	 */
	public static IAuthenticationService getAuthenticationService(){
		return AuthenticationService.getInstance();
	}
}
