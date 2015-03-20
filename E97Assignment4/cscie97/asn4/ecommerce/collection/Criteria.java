package cscie97.asn4.ecommerce.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ssingh
 *Defines search criteria for dynamic collection.
 */
public class Criteria {
	private List<String> contentCategories;
	private String searchText;
	private int minRating;
	private float maxPrice;
	private String countryCode;
	private List<String> contentLanguages;
	private String deviceId;
	private List<String> contentType;
	
	/**
	 * Default Criteria construct to create empty criteria
	 */
	public Criteria(){
		contentCategories = new ArrayList<String>();
		contentLanguages = new ArrayList<String>();
		contentLanguages = new ArrayList<String>();
		minRating = -1;
		maxPrice = -1;
	}
	
	/**
	 * @return - Product categories.
	 */
	public List<String> getContentCategories() {
		return contentCategories;
	}

	/**
	 * @param contentCategories - Product categories.
	 */
	public void setContentCategories(List<String> contentCategories) {
		this.contentCategories = contentCategories;
	}

	/**
	 * @return - Product search text.
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText - Product search text.
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	/**
	 * @return - Product rating.
	 */
	public int getMinRating() {
		return minRating;
	}

	/**
	 * @param minRating - Product rating.
	 */
	public void setMinRating(int minRating) {
		this.minRating = minRating;
	}

	/**
	 * @return - Price of product.
	 */
	public float getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice - Price of product.
	 */
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return - Country where product is available.
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode - Country where product is available.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return - Languages product supports.
	 */
	public List<String> getContentLanguages() {
		return contentLanguages;
	}

	/**
	 * @param contentLanguages - Languages product supports.
	 */
	public void setContentLanguages(List<String> contentLanguages) {
		this.contentLanguages = contentLanguages;
	}

	/**
	 * @return - Device product supports.
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId - Device product supports.
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return - Product types.
	 */
	public List<String> getContentType() {
		return contentType;
	}

	/**
	 * @param contentType - Product types.
	 */
	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * @return - string of comma separated key value pairs of criteria 
	 */
	public String getInfo(){
		 String result = "{";
		 result += "contentCategories = " + contentCategories;
		 result += " , contentType = " + contentType;
		 result += " , searchText = " + ((searchText.trim().isEmpty()) ? "null" : searchText);
		 result += " , minRating = " + ((minRating == -1 ) ?  "null" :  minRating);
		 result += " , maxPrice = " + ((maxPrice == -1 ) ?  "null" :  maxPrice);
		 result += " , countryCode = " + ((countryCode.trim().isEmpty()) ? "null" : countryCode);
		 result += " , contentLanguages = " + contentLanguages;
		 result += "}";
		 return result;
	}
}
