package cscie97.asn4.ecommerce.collection;

import cscie97.asn4.ecommerce.product.Content;
import cscie97.asn4.ecommerce.product.Content.CONTENT_TYPE;
import cscie97.asn4.ecommerce.product.ProductServiceFactory;


/**
 * This acts a proxy class for content in product api service. 
 * This class derives from collectible and implements the printInfo() method. 
 * It keeps an id of the product it represents
 * @author ssingh
 *
 */
class ProductProxy extends Collectible {
	final String id;
	/**
	 * Constructor to construct product proxy object from id.
	 * @param id - product id
	 */
	public ProductProxy(String id) {
		super();
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see cscie97.asn3.ecommerce.collection.Collectible#printInfo()
	 */
	@Override
	public void printInfo() {
		ProductServiceFactory psf = new ProductServiceFactory();
		Content content = psf.getProductService().getContent(id);
		if(content != null){
			Content.CONTENT_TYPE type = content.getContentType();
			String contentType = ((type == CONTENT_TYPE.APPLICATION) ? "application" : 
				((type == CONTENT_TYPE.WALLPAPER) ? "wallpaper" : "ringtone"));
			System.out.println("	Type = " + contentType);
			System.out.println("	Id = " + content.getContentId());
			System.out.println("	Name = " + content.getContentName());
			System.out.println("	Description = " + content.getContentDescription());
			System.out.println("	Author = " + content.getContentAuthor());
			System.out.println("	Price = " + content.getContentPrice());
			System.out.println("	Rating = " + content.getContentRating());
			System.out.println("	Countries = " + content.getContentCountryNames());
			System.out.println("	Devices = " + content.getContentDeviceNames());
			System.out.println("	Categories = " + content.getContentLanguages());
			System.out.println("	Image URL = " + content.getContentImageURL());
			System.out.println();
		}
	}
}
