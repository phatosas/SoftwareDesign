package cscie97.asn4.ecommerce.collection;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ssingh
 *Collection class represents the composite collection which includes set of products 
 *and set collections. This is abstract because a collection does not fully qualify the 
 *method by which it can store products. So it declares an abstract method 
 *getContainedProducts() which is overridden by concrete classes DynamicCollection 
 *and StaticCollection. Otherwise this class defined all the methods and properties of a 
 *collection
 */
public abstract class Collection extends Collectible {
	final String id;
	String name;
	String description;
	List<String> childIds;
	
	

	/**
	 * Construct collection from id, name and description
	 * @param id - collection id
	 * @param name - collection name
	 * @param desc - collection description
	 */
	Collection(String id,String name,String desc){
		childIds = new ArrayList<String>();
		this.name = name;
		this.id = id;
		this.description = desc;
	}
	
	/**
	 * Abstract method for subclasses to implement. Concrete classes know the policy of 
	 * fetching products it contains. E.g. Dynamic collection calls product service 
	 * API to get all products which meets its criteria.
	 * @return products contained by collection
	 */
	public abstract List<ProductProxy> getContainedProducts();
	/**
	 * Abstract class for subclasses to override. Subclasses return type of 
	 * collection they are (static or dynamic). Useful in printing info about 
	 * the collection.
	 * @return - type of collection ( static or dynamic )
	 */
	public abstract String getType();
	
	/**
	 * @return - Returns the begin iterator of collection object.
	 */
	public CollectionIterator getIterator() {
		return new CollectionIterator(this);
	}
	
	/**
	 * Adds a child collection to the list of child collections.
	 * @param id - product or collection identifier
	 */
	void addChild(String id){
		childIds.add(id.trim().toUpperCase());
	}
	/**
	 * Removes a child collection to the list of child collections.
	 * @param id - product or collection identifier
	 */
	void removeChild(String id){
		childIds.remove(id.trim().toUpperCase());
	}

	/**
	 * @return - collection name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return - collection name
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return - identifiers of contained products or collections.
	 */
	public List<String> getChildIds() {
		return childIds;
	}


	/**
	 * @return - collection id
	 */
	public String getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collectible#printInfo()
	 */
	public void printInfo() {
		System.out.println("Type = " + getType());
		System.out.println("Id = " + id);
		System.out.println("Name = " + name);
		System.out.println("Description = " + description);
		System.out.println();
	}
}
