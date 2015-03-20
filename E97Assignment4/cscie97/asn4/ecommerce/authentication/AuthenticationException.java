package cscie97.asn4.ecommerce.authentication;

/**
 * Represents exception class when user’s credentials are wrong during login
 * @author ssingh
 *
 */
public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 1L;
	private String userName;
	public AuthenticationException(String userName) {
		super(userName + " : Invalid user name or password");
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
