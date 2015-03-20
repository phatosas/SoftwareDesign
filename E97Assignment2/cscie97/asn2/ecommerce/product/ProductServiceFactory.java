package cscie97.asn2.ecommerce.product;

/**
 * The public class which acts as a factory of Product service object.
 * This class has a factory method which returns handle to product 
 * service singleton object.
 * @author ssingh
 *
 */
public class ProductServiceFactory {
	
	/**
	 * factory method to return handle to product  service singleton object.
	 * @return returns handle to product  service singleton object.
	 */
	public IProductService getProductService(){
		return ProductService.getInstance();
	}
}
