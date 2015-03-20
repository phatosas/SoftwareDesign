package cscie97.asn4.ecommerce.authentication;

import java.util.ArrayList;
import java.util.List;


/**
 * Role is subclass of Entitlement and implements visitable interface. 
 * Role class exhibits composite pattern by containing permissions and 
 * nested roles which are entitlements. It exposes getiterator() function 
 * which returns iterator in order to traverse though child elements of role.
 * @author ssingh
 *
 */
class Role extends Entitlement implements Visitable{
	List<Entitlement> entitlements;

	public Role(String id, String name, String description, TYPE type) {
		super(id, name, description, type);
		this.entitlements = new ArrayList<Entitlement>();
	}

	public List<Entitlement> getEntitlements() {
		return entitlements;
	}

	public void setEntitlements(List<Entitlement> entitlements) {
		this.entitlements = entitlements;
	}
	
	/**
	 * Associates entitlement with the role.
	 * @param entitlement entitlement object ( role or permission )
	 */
	void addEntitlement(Entitlement entitlement){
		entitlements.add(entitlement);
	}
	/**
	 * @return - Returns the begin iterator of role object.
	 */
	public RoleIterator getIterator() {
		return new RoleIterator(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
