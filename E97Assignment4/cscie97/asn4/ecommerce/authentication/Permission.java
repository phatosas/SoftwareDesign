package cscie97.asn4.ecommerce.authentication;


/**
 * Permission is subclass of Entitlement and implements visitable interface. 
 * Each permission object represents a service which can be performed if user 
 * has the permission
 * @author ssingh
 *
 */
class Permission extends Entitlement implements Visitable{
	public Permission(String id, String name, String description, TYPE type) {
		super(id, name, description, type);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
