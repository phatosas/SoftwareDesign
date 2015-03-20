package cscie97.asn4.ecommerce.collection;

import java.util.ArrayList;
import java.util.List;

import cscie97.asn4.ecommerce.product.Content;
import cscie97.asn4.ecommerce.product.ProductServiceFactory;

/**
 * @author ssingh
 *Derives from Collection and override getContainedProducts(). At runtime it calls 
 *product service api to get all the product which meets its search criteria
 */
class DynamicCollection extends Collection {
	Criteria searchCriteria;
	
	/**
	 * Constructor to construct dynamic collection from id, name and description
	 * @param id - collection id
	 * @param name - collection name
	 * @param desc - collection description
	 */
	DynamicCollection(String id, String name, String desc) {
		super(id, name, desc);
	}
	
	/**
	 * @return - search criteria
	 */
	public Criteria getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param searchCriteria - search criteria
	 */
	public void setSearchCriteria(Criteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collection#getContainedProducts()
	 * Calls product service API to get all products which meets its criteria
	 */
	@Override
	public List<ProductProxy> getContainedProducts() {
		ProductServiceFactory psf = new ProductServiceFactory();
		List<Content> contents = psf.getProductService().searchContent(searchCriteria.getContentCategories(), 
				searchCriteria.getSearchText(),searchCriteria.getMinRating(),searchCriteria.getMaxPrice(), 
				searchCriteria.getCountryCode(), searchCriteria.getContentLanguages(), 
				searchCriteria.getDeviceId(), searchCriteria.getContentType());
		List<ProductProxy> resultSet = new ArrayList<ProductProxy>();
		for(Content content:contents){
			resultSet.add(new ProductProxy(content.getContentId()));
		}
		return resultSet;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collection#getType()
	 * Returns “dynamic-collection”
	 */
	@Override
	public String getType() {
		return "dynamic_collection: Criteria" + searchCriteria.getInfo();
	}
}
