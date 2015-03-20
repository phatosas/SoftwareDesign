package cscie97.asn3.ecommerce.collection;


/**
 * @author ssingh
 *This auxiliary class defines set of static functions to validate 
 *collection service parameters.
 */
class CollectionValidator {
	/**
	 * Throws CollectionServiceException if name is empty or null.
	 * @param name - collection name
	 * @throws CollectionServiceException
	 */
	public static void validateName(String name) throws CollectionServiceException{	
		if(name.isEmpty())
			throw new CollectionServiceException("name","is blank");
	}
	/**
	 * Throws CollectionServiceException if description is empty or null.
	 * @param desc - collection description
	 * @throws CollectionServiceException
	 */
	public static void validateDescription(String desc) throws CollectionServiceException{	
		if(desc.isEmpty())
			throw new CollectionServiceException("description","is blank");
	}
	/**
	 * Throws CollectionServiceException if id is empty or null.
	 * @param id - collection id
	 * @throws CollectionServiceException
	 */
	public static void validateId(String id) throws CollectionServiceException{	
		if(id.isEmpty())
			throw new CollectionServiceException("id","is blank");
	}
	/**
	 * Throws CollectionServiceException if type is empty or null or not equal 
	 * to “static” or “dynamic”.
	 * @param type - collection type
	 * @throws CollectionServiceException
	 */
	public static void validateType(String type) throws CollectionServiceException{	
		if(type.isEmpty())
			throw new CollectionServiceException("type","is blank");
		if(type.compareToIgnoreCase("static") != 0 && 
				type.compareToIgnoreCase("dynamic") != 0)
			throw new CollectionServiceException("invalid type",type);
	}
	/**
	 * Throws CollectionServiceException if type is empty or null or not equal 
	 * to “product” or “collection”.
	 * @param type - content type
	 * @throws CollectionServiceException
	 */
	public static void validateContentType(String type) throws CollectionServiceException{	
		if(type.isEmpty())
			throw new CollectionServiceException("contentType","is blank");
		if(type.compareToIgnoreCase("product") != 0 && 
				type.compareToIgnoreCase("collection") != 0)
			throw new CollectionServiceException("invalid content type",type);
	}
	
	/**
	 * Throws CollectionServiceException if contentId is empty or null.
	 * @param contentId - content id
	 * @throws CollectionServiceException
	 */
	public static void validateContentId(String contentId) throws CollectionServiceException{	
		if(contentId.isEmpty())
			throw new CollectionServiceException("contentId","is blank");
	}
}
