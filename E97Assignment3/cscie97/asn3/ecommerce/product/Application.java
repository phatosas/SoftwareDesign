package cscie97.asn3.ecommerce.product;

import java.util.List;

/**
 * This is a concrete and specialized class inherited from ContentWithSize and 
 * represents Application objects.
 * @author ssingh
 *
 */

class Application extends ContentWithSize {

	/**
	 * Constructor
	 * @param contentId Content id
	 * @param contentType Type of Content ( Application, Wallpaper, Ringtone)
	 * @param contentName Type of Content
	 * @param contentDescription Description of Content
	 * @param contentAuthor Type of Content
	 * @param contentRating Content Rating ( 0 to 5 )
	 * @param contentCategories 
	 * @param contentCountries Countries in which content is available
	 * @param contentDevices Devices with which content is compatible
	 * @param contentPrice Price of content
	 * @param contentLanguages Languages a content supports
	 * @param contentImageURL image URL of content
	 * @param contentSize size of content
	 */
	public Application(String contentId,CONTENT_TYPE contentType,String contentName, String contentDescription,
			String contentAuthor, int contentRating,
			List<String> contentCategories, List<Country> contentCountries,
			List<Device> contentDevices, float contentPrice,
			List<String> contentLanguages, String contentImageURL,
			int contentSize) {
		super(contentId,contentType,contentName, contentDescription, contentAuthor, contentRating,
				contentCategories, contentCountries, contentDevices, contentPrice,
				contentLanguages, contentImageURL, contentSize);	
		// TODO Auto-generated constructor stub
	}
}
