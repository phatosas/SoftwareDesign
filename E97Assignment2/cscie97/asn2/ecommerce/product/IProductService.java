package cscie97.asn2.ecommerce.product;

import java.util.List;



/**
 * This is a public interface which declares APIs exposed by Product API service. 
 * ProductService implements and defines all the methods of this interface. 
 * @author ssingh
 *
 */
public interface IProductService {
	/**
	 * Adds country to product service, validates properties before inserting 
	 * and also checks if country is already in the service. Inserts only if 
	 * properties are valid otherwise throws ContentParameterException. 
	 * Does nothing if country already exists in the service.
	 * @param countryCode Country Code
	 * @param countryName Country Name
	 * @param exportStatus Export status of the country
	 * @param GUID user's credentials
	 * @throws ContentParameterException when country properties are invalid 
	 */
	void addCountry(String countryCode, String countryName,String exportStatus,
			String GUID ) throws ContentParameterException;
	/**
	 * Adds device to product service, validates properties before inserting 
	 * and also checks if device is already in the service. Inserts only if 
	 * properties are valid otherwise throws ContentParameterException. 
	 * Does nothing if device already exists in the service.
	 * @param deviceId device id
	 * @param deviceName device name
	 * @param mfgName manufacturer's name
	 * @param GUID user's credentials
	 * @throws ContentParameterException when device properties are invalid
	 */
	void addDevice(String deviceId, String deviceName,String mfgName,
			String GUID ) throws ContentParameterException;
	
	/**
	 * Adds content to product service, validates properties before inserting and also 
	 * checks if content is already in the service. Inserts only if properties are valid 
	 * otherwise throws ContentParameterException. Does nothing if content already 
	 * exists in the service.
	 * @param contentId Content id
	 * @param contentType Type of Content ( Application, Wallpaper, Ringtone)
	 * @param contentName Name of Content
	 * @param contentDescription Description of Content
	 * @param contentAuthor Type of Content
	 * @param contentRating Content Rating ( 0 to 5 )
	 * @param contentCategories Categories of a content
	 * @param contentCountries Countries in which content is available
	 * @param contentDevices Devices with which content is compatible
	 * @param contentPrice Price of content
	 * @param contentLanguages Languages a content supports
	 * @param contentImageURL image URL of content
	 * @param contentSize size of content
	 * @param GUID user's credentials
	 * @throws ContentParameterException when device properties are invalid or
	 * there is no compatible device or there is no country where content is available
	 */
	void addContent(String contentId,String contentType, String contentName,
			String contentDescription, String contentAuthor, int contentRating,
			List<String> contentCategories, List<String> contentCountries,
			List<String> contentDevices, float contentPrice,
			List<String> contentLanguages, String contentImageURL,
			int contentSize,String GUID) throws ContentParameterException;
	/**
	 * Search contents in the contentMap based on the search criteria 
	 * given as arguments of this function. Returns list of contents which match 
	 * the search criteria.
	 * @param contentCategories Categories of a content
	 * @param searchText Text in Name or Description
	 * @param minRating minimum content rating
	 * @param maxPrice maximum content price
	 * @param countryCode country where content is available
	 * @param contentLanguages languages content supports
	 * @param deviceId devices content is compatible with
	 * @param contentType Type of Content ( Application, Wallpaper, Ringtone)
	 * @return List of Matching Contents
	 */
	List<Content> searchContent(List<String> contentCategories, String searchText, 
			int minRating, float maxPrice, String countryCode, 
			List<String> contentLanguages, String deviceId, List<String> contentType);
}
