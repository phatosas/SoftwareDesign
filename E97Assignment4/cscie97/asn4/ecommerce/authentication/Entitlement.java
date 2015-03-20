package cscie97.asn4.ecommerce.authentication;

/**
 * This acts as base class for roles and permissions. 
 * Entitlement has and id , name description and type. 
 * A type is either role or permission. 
 * @author ssingh
 *
 */
public abstract class Entitlement {
	public enum TYPE{ROLE,PERMISSION}
	final String id;
	String name;
	String description;
	TYPE type;
	public Entitlement(String id, String name, String description, TYPE type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
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
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	
}
