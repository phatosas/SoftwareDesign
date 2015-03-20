package cscie97.asn3.ecommerce.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ssingh
 *Derives from Collection and override getContainedProducts(). 
 *This class keeps static list of products.
 */
class StaticCollection extends Collection {
	List<String> productIds;
	/**
	 * Constructor to construct static collection from id, name and description
	 * @param id - collection id
	 * @param name - collection name
	 * @param desc - collection description
	 */
	StaticCollection(String id, String name, String desc) {
		super(id, name, desc);
		productIds = new ArrayList<String>();
	}

	/**
	 * add product to static collection
	 * @param id - product id
	 */
	public void addProduct(String id){
		productIds.add(id);
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collection#getContainedProducts()
	 * Returns the static list of contained products.
	 */
	@Override
	public List<ProductProxy> getContainedProducts() {
		List<ProductProxy> resultSet = new ArrayList<ProductProxy>();
		for(String id:productIds){
			resultSet.add(new ProductProxy(id));
		}
		return resultSet;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collection#getType()
	 * Returns “static-collection”
	 */
	@Override
	public String getType() {
		return "static_collection";
	}
	
}
