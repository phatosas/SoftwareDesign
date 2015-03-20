package cscie97.asn4.ecommerce.authentication;

/**
 * Declares method to accept visitor. The interface is implemented by user, 
 * service, role, permission and access token.
 * @author ssingh
 *
 */
public interface Visitable {
	/**
	 * Accepts visitor and calls visit() of visitor.
	 * @param visitor visitor object
	 */
	public void accept(Visitor visitor);
}
