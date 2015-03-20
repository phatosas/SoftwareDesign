package cscie97.asn3.ecommerce.product;

import java.util.List;


/**
 * This abstract class is derived from Content and declares content size property. 
 * This is inherited by all types of contents which has a size.
 * @author ssingh
 *
 */
public abstract class ContentWithSize extends Content {
	private int contentSize;
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
	public ContentWithSize(String contentId,CONTENT_TYPE contentType,String contentName, String contentDescription,
			String contentAuthor, int contentRating,
			List<String> contentCategories, List<Country> contentCountries,
			List<Device> contentDevices, float contentPrice,
			List<String> contentLanguages, String contentImageURL,
			int contentSize) {
		super(contentId,contentType,contentName, contentDescription, contentAuthor, contentRating,
				contentCategories, contentCountries, contentDevices,
				contentPrice, contentLanguages, contentImageURL);
		this.contentSize = contentSize;
	}
	
	/**
	 * @return Size of Content in bytes
	 */
	public int getContentSize() {
		return contentSize;
	}

	/**
	 * @param contentSize Size of Content in bytes
	 */
	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}
}
