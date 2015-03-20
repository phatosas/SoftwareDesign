package cscie97.asn4.ecommerce.authentication;

/**
 * Declares methods to visit user, service, role, permission and access token. 
 * All these classes implements visitable interface to accept visitor object
 * @author ssingh
 *
 */
public interface Visitor {
	/**
	 * Visits service object.
	 * @param service service object
	 */
	public void visit(Service service);
	/**
	 * Visits permission object.
	 * @param permission permission object
	 */
	public void visit(Permission permission);
	/**
	 * Visits role object.
	 * @param role role object
	 */
	public void visit(Role role);
	/**
	 * Visits user object.
	 * @param user user object
	 */
	public void visit(User user);
	/**
	 * Visits accessToken object. 
	 * @param accessToken access token object
	 */
	public void visit(AccessToken accessToken);
}
