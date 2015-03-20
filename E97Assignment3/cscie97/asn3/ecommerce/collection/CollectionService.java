package cscie97.asn3.ecommerce.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import cscie97.asn3.ecommerce.product.ProductServiceFactory;

/**
 * @author ssingh
 *CollectionService implements all the methods declared in ICollectionService. 
 *It keeps map of collection id and collection object. It also keeps map of product 
 *id and product object as well as parent collection object of all the top level 
 *collection objects. This parent object acts as root of the collection tree.
 */
class CollectionService implements ICollectionService {

	/**
	 * map of collection id and collection object
	 */
	Map<String,Collection> collectionMap;
	/**
	 * map of product id and product object
	 */
	Map<String,ProductProxy> productMap;
	/**
	 * Collection	root of the collection tree
	 */
	Collection headCollection;
	
	private static CollectionService instance = null;
	
	/**
	 * private constructor
	 */
	private CollectionService(){
		collectionMap = new HashMap<String, Collection>();
		productMap = new HashMap<String, ProductProxy>();
		headCollection = new StaticCollection(null,null,null);
	}
	
	/**
	 * @return - singleton CollectionService object
	 */
	public static CollectionService getInstance()
	{
		if(instance == null) 
		{
			instance = new CollectionService();
		}
      	return instance;
	}
	
	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.ICollectionService#create(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void create(String collType, String collId, String collName,
			String collDesc, String GUID) throws CollectionServiceException{
		CollectionValidator.validateId(collId);
		CollectionValidator.validateType(collType);
		CollectionValidator.validateName(collName);
		CollectionValidator.validateDescription(collDesc);
		
		Collection coll = null;
		collId = collId.toUpperCase();
		if(collType.trim().compareToIgnoreCase("static") == 0){
			coll = new StaticCollection(collId, collName, collDesc);
		}
		else if(collType.trim().compareToIgnoreCase("dynamic") == 0){
			coll = new DynamicCollection(collId, collName, collDesc);
		}
		collectionMap.put(collId, coll);
		headCollection.addChild(collId);
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.ICollectionService#addContent(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addContent(String collId, String contentType, String contentId,
			String GUID) throws CollectionServiceException{
		CollectionValidator.validateId(collId);
		CollectionValidator.validateContentType(contentType);
		CollectionValidator.validateContentId(contentId);
		
		collId = collId.toUpperCase();
		contentId = contentId.toUpperCase();
		Collection parent = collectionMap.get(collId);
		if(parent != null){
			if(contentType.trim().compareToIgnoreCase("product") == 0){
				ProductServiceFactory psf = new ProductServiceFactory();
				if(psf.getProductService().getContent(contentId) != null){
					ProductProxy product = new ProductProxy(contentId);
					((StaticCollection)parent).addProduct(contentId);
					productMap.put(contentId, product);
				}
				else
					throw new CollectionServiceException(contentId, "Content does not exist");
			}
			else if(contentType.trim().compareToIgnoreCase("collection") == 0){
				Collection coll = collectionMap.get(contentId);
				if(coll != null){
					headCollection.removeChild(contentId);
					parent.addChild(contentId);
					collectionMap.put(contentId, coll);
				}
				else
					throw new CollectionServiceException(contentId, "Collection does not exist");
			}
		}
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.ICollectionService#search(java.lang.String)
	 */
	@Override
	public List<Collection> search(String searchText) {
		if(searchText == null)
			searchText = "";
		List<Collection> resultSet = new ArrayList<Collection>();
		for (String contentId : collectionMap.keySet()) {
			Collection coll = collectionMap.get(contentId);
			Boolean addToList = true;
			 // check text search
		    if (!searchText.trim().isEmpty()) {
		    	addToList = ((coll.getName().toLowerCase().indexOf(searchText.toLowerCase()) >= 0)  
		    			|| (coll.getDescription().toLowerCase().indexOf(searchText.toLowerCase()) >= 0));
		    }
		    if(addToList){
		    	resultSet.add(coll);
		    }
		}
		return resultSet;
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.ICollectionService#iterator(java.lang.String)
	 */
	@Override
	public CollectionIterator iterator(String collId){
		if(collId == null || collId.trim().isEmpty())
			return headCollection.getIterator();
		Collection coll = collectionMap.get(collId.toUpperCase());
		if(coll != null)
			return coll.getIterator();
		return null;
	}
	Collection getCollection(String collId) {
		return collectionMap.get(collId);
	}

	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.ICollectionService#setSearchCriteria(java.lang.String, cscie97.asn3.ecommerce.collection.Criteria)
	 */
	@Override
	public void setSearchCriteria(String collId, Criteria criteria) throws CollectionServiceException{	
		Collection coll = collectionMap.get(collId.toUpperCase());
		if(coll == null)
			throw new CollectionServiceException(collId, "Collection does not exist");
		if(coll instanceof DynamicCollection){
			((DynamicCollection) coll).setSearchCriteria(criteria);
		}
		else
			throw new CollectionServiceException(collId, "Not a Dynamic Collection");
	}

}
