package cscie97.asn4.ecommerce.authentication;

/**
 * Represents exception class when user is not permitted to 
 * invoke any restricted method.
 * @author ssingh
 *
 */
public class AccessDeniedException extends Exception{

	private String userName;
	private String methodName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AccessDeniedException(String userName, String methodName) {
		super(methodName + " : Access denied to " + userName);
		this.userName = userName;
		this.methodName = methodName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
