package cscie97.asn4.ecommerce.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * User implements visitable interface. It represents user of mobile 
 * application store. A user has an id, name and credential 
 * (user name and password). User has set of permissions.
 * @author ssingh
 *
 */
class User implements Visitable{
	final String id;
	String name;
	Credential credential;
	List<Entitlement> entitlements;
	
	
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.entitlements = new ArrayList<Entitlement>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public List<Entitlement> getEntitlements() {
		return entitlements;
	}
	public Credential getCredential() {
		return credential;
	}
	void setCredential(String userName,String password){
		Credential credential = new Credential(userName, password);
		this.credential = credential;
	}
	void addEntitlement(Entitlement entitlement){
		entitlements.add(entitlement);
	}
	boolean checkCredential(String userName,String password){
		return credential.checkCredential(userName,password);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
