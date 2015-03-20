package cscie97.asn4.ecommerce.authentication;

import java.util.Date;
import java.util.List;


/**
 * Concrete implementation of visitor interface> the purpose of the visitor 
 * class is to print the objects information on standard output.
 * @author ssingh
 *
 */
public class PrintInfoVisitor implements Visitor {

	private void print(Entitlement entitlement,String delim) {
		System.out.println(delim + "Entitlement : " + entitlement.getName());
		System.out.println(delim + "Type = " + entitlement.getType());
		System.out.println(delim + "Id = " + entitlement.getId());
		System.out.println(delim + "Name = " + entitlement.getName());
		System.out.println(delim + "Description = " + entitlement.getDescription());
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.Visitor#visit(cscie97.asn4.ecommerce.authentication.Service)
	 */
	@Override
	public void visit(Service service) {
		System.out.println("Service : " + service.getName());
		System.out.println();
		System.out.println("Id = " + service.getId());
		System.out.println("Name = " + service.getName());
		System.out.println("Description = " + service.getDescription());
		System.out.println("Permissions : ");
		List<Permission> permissions = service.getPermissions();
		for(Permission permission:permissions){
			System.out.println();
			visit(permission);
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.Visitor#visit(cscie97.asn4.ecommerce.authentication.Permission)
	 */
	@Override
	public void visit(Permission permission) {	
		print(permission,"\t");
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.Visitor#visit(cscie97.asn4.ecommerce.authentication.Role)
	 */
	@Override
	public void visit(Role role) {
		RoleIterator it = role.getIterator();
		if(it.hasNext()){
			Entitlement childEntitlement = it.currentItem();
			print(childEntitlement,"");
			it.next();
		}
		while(it.hasNext()){
			Entitlement childEntitlement = it.currentItem();
			print(childEntitlement,"\t");
			it.next();
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.Visitor#visit(cscie97.asn4.ecommerce.authentication.User)
	 */
	@Override
	public void visit(User user) {
		System.out.println("User : " + user.getName());
		System.out.println("Id = " + user.getId());
		System.out.println("Name = " + user.getName());
		System.out.println("Login = " + user.getCredential().getUserName());
		System.out.println("Encrypted Password = " + user.getCredential().getPassword());
	}

	/* (non-Javadoc)
	 * @see cscie97.asn4.ecommerce.authentication.Visitor#visit(cscie97.asn4.ecommerce.authentication.AccessToken)
	 */
	@Override
	public void visit(AccessToken accessToken) {
		System.out.println("Access Token Id = " + accessToken.getGUID());
		System.out.println("Access Token State = " + accessToken.isState());
		System.out.println("Access Token Expiration = " + new Date(accessToken.getExpirationTime()));
		visit(accessToken.getUser());
	}

}
