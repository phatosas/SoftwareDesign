package cscie97.asn4.ecommerce.authentication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cscie97.asn4.ecommerce.authentication.Entitlement.TYPE;


/**
 * Iterator class for iterating roles
 * @author ssingh
 *
 */
public class RoleIterator {
	int currIndex;
	List<Entitlement> entitlements;
	Set<Entitlement> visited;
	Role parentRole;
	
	/**
	 * @param parentColl
	 * Constructor to create iterator for collection tree rooted at parentColl 
	 */
	public RoleIterator(Role parentRole) {
		super();
		currIndex = 0;
		this.parentRole = parentRole;
		entitlements= new ArrayList<Entitlement>();
		visited = new HashSet<Entitlement>();
		roleIteratorDFS(parentRole);
	}
	/**
	 * @param parent
	 * Depth first search traversal of collection tree
	 */
	private void roleIteratorDFS(Role parent){
		entitlements.add(parent);
		visited.add(parent);
		List<Entitlement> childEntitlements =  parent.getEntitlements();
		for(Entitlement childEntitlement:childEntitlements){
			if(childEntitlement.getType() == TYPE.PERMISSION)
				entitlements.add(childEntitlement);
			else if(childEntitlement.getType() == TYPE.ROLE){
				if(!visited.contains(childEntitlement))
						roleIteratorDFS((Role)childEntitlement);
			}
		}
	}
	/**
	 * Goes to next item in the iterator.
	 */
	public void next(){	
		++currIndex;
	}
	/**
	 * @return - Returns true if iterator has more items to iterate otherwise false.
	 */
	public Boolean hasNext(){
		return (currIndex < entitlements.size());
	}
	/**
	 * @return - current item in the iterator
	 */
	public Entitlement currentItem(){
		return entitlements.get(currIndex);
	}
}
