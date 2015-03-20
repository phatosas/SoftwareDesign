package cscie97.asn4.ecommerce.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a service from mobile store application. 
 * A service has id, name and description and set of permissions defined 
 * in the service. 
 * @author ssingh
 *
 */
class Service implements Visitable{
	final String id;
	String name;
	String description;
	List<Permission> permissions;
	
	public Service(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.permissions = new ArrayList<Permission>();
	}
	
	void addPermission(Permission permission){
		permissions.add(permission);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getId() {
		return id;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
