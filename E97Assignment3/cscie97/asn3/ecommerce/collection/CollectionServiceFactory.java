package cscie97.asn3.ecommerce.collection;

/**
 * The public class which acts as a factory of collection service object.
 * This class has a factory method which returns handle to collection 
 * service singleton object.
 * @author ssingh
 *
 */
public class CollectionServiceFactory {
	/**
	 * factory method to return handle to collection  service singleton object.
	 * @return returns handle to collection  service singleton object.
	 */
	public static ICollectionService getCollectionService(){
		return CollectionService.getInstance();
	}
}
