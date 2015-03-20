package cscie97.asn4.ecommerce.collection;

import java.util.List;

import cscie97.asn4.ecommerce.authentication.AccessDeniedException;
import cscie97.asn4.ecommerce.authentication.InvalidAccessTokenException;

/**
 * @author ssingh
 *
 */
public interface ICollectionService {
	/**
	 * Creates collection with given type, id, name and description. 
	 * Throws CollectionServiceException if id is not unique or type nor 
	 * static nor dynamic or any of the fields are blank.
	 * @param collType - collection type ( static or dynamic )
	 * @param collId - collection id
	 * @param collName - collection name
	 * @param collDesc - collection description
	 * @param GUID - GUID for authentication
	 * @throws CollectionServiceException
	 * @throws AccessDeniedException 
	 * @throws InvalidAccessTokenException 
	 */
	void create(String collType,String collId,String collName,
			String collDesc,String GUID) throws CollectionServiceException, InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Add content to a given collection. Throws CollectionServiceException if 
	 * collection or content does not exist in the store or any of the fields 
	 * are null or blank.
	 * @param collId - collection id
	 * @param contentType - content type ( product or collection )
	 * @param contentId - content id
	 * @param GUID - GUID for authentication
	 * @throws CollectionServiceException
	 * @throws AccessDeniedException 
	 * @throws InvalidAccessTokenException 
	 */
	void addContent(String collId,String contentType,String contentId,
				String GUID)throws CollectionServiceException, InvalidAccessTokenException, AccessDeniedException;
	/**
	 * Perform text search on collections. Return all collections which contain 
	 * the text in their name or description
	 * @param searchText - text to search
	 * @return -  list of matching collection
	 */
	List<Collection> search(String searchText);
	/**
	 * Returns the begin iterator of the collection. If collId is blank 
	 * or null then it returns a parent iterator of all the top level collection.
	 * @param collId - collection id
	 * @return - iterator rooted at collection with collId as its id.
	 */
	CollectionIterator iterator(String collId);
	/**
	 * Set search criteria for a dynamic collection. Throws CollectionServiceException 
	 * if collId is blank or does not refer to a dynamic collection.
	 * @param collId - collection id
	 * @param criteria - dynamic collection search criteria
	 * @throws CollectionServiceException
	 */
	void setSearchCriteria(String collId,Criteria criteria)throws CollectionServiceException;
}
