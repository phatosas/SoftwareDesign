package cscie97.asn4.ecommerce.product;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents content objects and encapsulates all the 
 * properties of a content which are common to Application, Wallpaper and Ringtone
 * @author ssingh
 *
 */
public abstract class Content {
	public enum CONTENT_TYPE{APPLICATION,WALLPAPER,RINGTONE};
	
	private final String contentId;
	private CONTENT_TYPE contentType;
	private String contentName;
	private String contentDescription;
	private String contentAuthor;
	private int contentRating;
	private List<String> contentCategories;
	private List<Country> contentCountries;
	private List<Device> contentDevices;
	private float contentPrice;
	private List<String> contentLanguages;
	private String contentImageURL;
	
	/**
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
	public Content(String contentId,CONTENT_TYPE contentType,String contentName, String contentDescription,
			String contentAuthor, int contentRating,
			List<String> contentCategories, List<Country> contentCountries,
			List<Device> contentDevices, float contentPrice,
			List<String> contentLanguages, String contentImageURL) {
		super();
		this.contentId = contentId;
		this.contentType = contentType;
		this.contentName = contentName;
		this.contentDescription = contentDescription;
		this.contentAuthor = contentAuthor;
		this.contentRating = contentRating;
		this.contentCategories = contentCategories;
		this.contentCountries = contentCountries;
		this.contentDevices = contentDevices;
		this.contentPrice = contentPrice;
		this.contentLanguages = contentLanguages;
		this.contentImageURL = contentImageURL;
	}
	
	/**
	 * @return Content Name
	 */
	public String getContentName() {
		return contentName;
	}
	/**
	 * @return Content Id
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentName content name
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	/**
	 * @return Content Description
	 */
	public String getContentDescription() {
		return contentDescription;
	}
	/**
	 * @param contentDescription Content Description
	 */
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	/**
	 * @return Content Author
	 */
	public String getContentAuthor() {
		return contentAuthor;
	}
	/**
	 * @param contentAuthor Content Author
	 */
	public void setContentAuthor(String contentAuthor) {
		this.contentAuthor = contentAuthor;
	}
	/**
	 * @return Content Rating
	 */
	public int getContentRating() {
		return contentRating;
	}
	/**
	 * @param contentRating Content Rating
	 */
	public void setContentRating(int contentRating) {
		this.contentRating = contentRating;
	}
	/**
	 * @return Content Categories
	 */
	public List<String> getContentCategories() {
		return contentCategories;
	}
	/**
	 * @param contentCategories Content Categories
	 */
	public void setContentCategories(List<String> contentCategories) {
		this.contentCategories = contentCategories;
	}
	/**
	 * @return Countries in which Content is available
	 */
	public List<Country> getContentCountries() {
		return contentCountries;
	}
	/**
	 * @return Countries in which Content is available
	 */
	public List<String> getContentCountryNames() {
		List<String> results = new ArrayList<String>();
		for(Country country : contentCountries)
			results.add(country.getCountryName());
		return results;
	}
	/**
	 * @param contentCountries Countries in which Content is available
	 */
	public void setContentCountries(List<Country> contentCountries) {
		this.contentCountries = contentCountries;
	}
	/**
	 * @return Devices with which content is compatible
	 */
	public List<Device> getContentDevices() {
		return contentDevices;
	}
	/**
	 * @return Devices with which content is compatible
	 */
	public List<String> getContentDeviceNames() {
		List<String> results = new ArrayList<String>();
		for(Device device : contentDevices)
			results.add(device.getDeviceName());
		return results;
	}
	/**
	 * @param contentDevices Devices with which content is compatible
	 */
	public void setContentDevices(List<Device> contentDevices) {
		this.contentDevices = contentDevices;
	}
	/**
	 * @return price of Content
	 */
	public float getContentPrice() {
		return contentPrice;
	}
	/**
	 * @param contentPrice price of Content
	 */
	public void setContentPrice(float contentPrice) {
		this.contentPrice = contentPrice;
	}
	/**
	 * @return Languages content supports
	 */
	public List<String> getContentLanguages() {
		return contentLanguages;
	}
	/**
	 * @param contentLanguages Languages content supports
	 */
	public void setContentLanguages(List<String> contentLanguages) {
		this.contentLanguages = contentLanguages;
	}
	/**
	 * @return Image URL of content
	 */
	public String getContentImageURL() {
		return contentImageURL;
	}
	/**
	 * @param contentImageURL Image URL of content
	 */
	public void setContentImageURL(String contentImageURL) {
		this.contentImageURL = contentImageURL;
	}

	/**
	 * @return Type ( Application, Ringtone, Wallpaper )
	 */
	public CONTENT_TYPE getContentType() {
		return contentType;
	}

	/**
	 * @param contentType Type ( Application, Ringtone, Wallpaper )
	 */
	public void setContentType(CONTENT_TYPE contentType) {
		this.contentType = contentType;
	}
	
	
}
