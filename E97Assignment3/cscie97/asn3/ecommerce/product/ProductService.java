package cscie97.asn3.ecommerce.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cscie97.asn3.ecommerce.product.Content.CONTENT_TYPE;
import cscie97.asn3.ecommerce.product.Country.EXPORT_STATUS;

/**
 * This class implements all the methods of product service interface. 
 * This call has package visibility. This class composes a hash map for countries 
 * (with country code as key), a hash map for devices (device id as key) and a 
 * hash map for content objects with content id as key. Keys are stored in 
 * upper case in order to make respective searches case insensitive. 
 * These composed objects are added in memory only if does not already exist.
 * The content is searched in the data structure mentioned above given a search criteria.

 * @author ssingh
 *
 */
class ProductService implements IProductService {
	
	private Map<String,Country> countryMap;
	private Map<String,Device> deviceMap;
	private Map<String, Content> contentMap;
	
	private static ProductService instance = null;
	
	private ProductService(){
		super();
		countryMap = new HashMap<String, Country>();
		deviceMap = new HashMap<String, Device>();
		contentMap = new HashMap<String, Content>();
	}

	/**
	 * Singleton ProductService method
	 * @return ProductService singleton object
	 */
	public static ProductService getInstance()
	{
		if(instance == null) 
		{
			instance = new ProductService();
		}
      	return instance;
	}
	
	/**
	 * Helper method to check if there is any common element in two lists
	 * @param l1 List one
	 * @param l2 List Two
	 * @return true if there is at least one common element, otherwise false
	 */
	private boolean intersection(List<String> l1,List<String> l2){
		List<String> l3 = new ArrayList<String>();
		List<String> l4 = new ArrayList<String>();
		for(String st : l1) {
		    l3.add(st.trim().toUpperCase());
		}
		for(String st : l2) {
		    l4.add(st.trim().toUpperCase());
		}
		return l3.retainAll(l4);
	}

	/**
	 * Converts String content type to CONTENT_TYPE Enum
	 * @param contentType
	 * @return
	 * @throws ContentParameterException
	 */
	private CONTENT_TYPE getContentType(String contentType)  throws ContentParameterException{
		if(contentType.trim().compareToIgnoreCase("APPLICATION") == 0)
			return CONTENT_TYPE.APPLICATION;
		if(contentType.trim().compareToIgnoreCase("WALLPAPER") == 0)
			return CONTENT_TYPE.WALLPAPER;
		if(contentType.trim().compareToIgnoreCase("RINGTONE") == 0)
			return CONTENT_TYPE.RINGTONE;
		throw new ContentParameterException("Invalid Content Type",contentType);
	}
	
	
	/* (non-Javadoc)
	 * @see cscie97.asn2.ecommerce.product.IProductService#addCountry(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addCountry(String countryCode, String countryName,String exportStatus,
			String GUID) throws ContentParameterException {
		// TODO Auto-generated method stub
		ContentValidator.validateCountryCode(countryCode);
		ContentValidator.validateCountryName(countryName);
		ContentValidator.validateExportStatus(exportStatus);
		
		String counteryCodeU = countryCode.toUpperCase().trim();
		if(!countryMap.containsKey(counteryCodeU)){
			EXPORT_STATUS es = EXPORT_STATUS.OPEN;
			if(exportStatus.trim().compareToIgnoreCase("closed") == 0){
				es = EXPORT_STATUS.CLOSED;
			}
			Country country = new Country(countryCode, countryName, es);
			countryMap.put(counteryCodeU, country);
		}
	}
	/* (non-Javadoc)
	 * @see cscie97.asn2.ecommerce.product.IProductService#addDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addDevice(String deviceId, String deviceName, String mfgName,
			String GUID)  throws ContentParameterException{
		// TODO Auto-generated method stub
		ContentValidator.validateDevice(deviceId);
		ContentValidator.validateDevice(deviceName);
		
		String deviceIdU = deviceId.toUpperCase().trim();
		if(!deviceMap.containsKey(deviceIdU)){
			Device device = new Device(deviceId, deviceName, mfgName);
			deviceMap.put(deviceIdU, device);
		}
	}
	/* (non-Javadoc)
	 * @see cscie97.asn2.ecommerce.product.IProductService#addContent(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.util.List, java.util.List, java.util.List, float, java.util.List, java.lang.String, int, java.lang.String)
	 */
	@Override
	public void addContent(String contentId,String contentType, String contentName,
			String contentDescription, String contentAuthor, int contentRating,
			List<String> contentCategories, List<String> contentCountries,
			List<String> contentDevices, float contentPrice,
			List<String> contentLanguages, String contentImageURL,
			int contentSize,String GUID) throws ContentParameterException{
		// TODO Auto-generated method stub
		if(contentMap.containsKey(contentId.toUpperCase())){
			throw new ContentParameterException("Content already exists",contentId);
		}
		
		ContentValidator.validateContentId(contentId);
		ContentValidator.validateContentType(contentType);
		ContentValidator.validateName(contentName);
		ContentValidator.validateDescription(contentName);
		ContentValidator.validateAuthor(contentDescription);
		ContentValidator.validateRating(contentRating);
		ContentValidator.validateImageURL(contentImageURL);
		ContentValidator.validateCountry(contentCountries);
		ContentValidator.validateDevice(contentDevices);
		ContentValidator.validateLanguage(contentLanguages);
		
		List<Country> countries = new ArrayList<Country>();
		for(String country : contentCountries){
			if(!countryMap.containsKey(country.toUpperCase())){
				throw new ContentParameterException("Country does not exist",country);
			}
			Country countryObj = countryMap.get(country.toUpperCase());
			if(countryObj.getExportStatus() == EXPORT_STATUS.OPEN)
				countries.add(countryMap.get(country.toUpperCase()));
		}
		if(countries.isEmpty())
			throw new ContentParameterException("No Allowed Countries",contentName);
		List<Device> devices = new ArrayList<Device>();
		for(String device : contentDevices){
			if(!deviceMap.containsKey(device.toUpperCase())){
				throw new ContentParameterException("Device does not exist",device);
			}
			devices.add(deviceMap.get(device.toUpperCase()));
		}
		if(devices.isEmpty())
			throw new ContentParameterException("No Compatible Devices",contentName);
		Content content = null;
		CONTENT_TYPE ctype = getContentType(contentType);
		if(ctype == CONTENT_TYPE.APPLICATION){
			content = new Application(contentId,ctype,contentName, contentDescription, contentAuthor, contentRating,
					contentCategories, countries, devices, contentPrice,
					contentLanguages, contentImageURL, contentSize);
		}
		else if(ctype == CONTENT_TYPE.WALLPAPER){
			content = new Wallpaper(contentId,ctype,contentName, contentDescription, contentAuthor, contentRating,
					contentCategories, countries, devices, contentPrice,
					contentLanguages, contentImageURL);	
		}
		else if(ctype == CONTENT_TYPE.RINGTONE){
			content = new RingTone(contentId,ctype,contentName, contentDescription, contentAuthor, contentRating,
					contentCategories, countries, devices, contentPrice,
					contentLanguages, contentImageURL);	
		}
		
		contentMap.put(contentId.toUpperCase(),content);			
	}
	/* (non-Javadoc)
	 * @see cscie97.asn2.ecommerce.product.IProductService#searchContent(java.util.List, java.lang.String, int, float, java.lang.String, java.util.List, java.lang.String, java.util.List)
	 */
	@Override
	public List<Content> searchContent(List<String> contentCategories,
			String searchText, int minRating, float maxPrice,
			String countryCode, List<String> contentLanguages, String deviceId,
			List<String> contentType) {
			
		List<Content> resultSet = new ArrayList<Content>();
		
		for (String contentId : contentMap.keySet()) {
		    Content content = contentMap.get(contentId);
		    boolean addToList = true;
		 // check categories
		    if(contentCategories.size() > 0){
		    	addToList = intersection(contentCategories,content.getContentCategories());
		    }
		    
		 // check text search
		    if (addToList) {
			    if (!searchText.trim().isEmpty()) {
			    	addToList = ((content.getContentName().toLowerCase().indexOf(searchText.toLowerCase()) >= 0)  
			    			|| (content.getContentDescription().toLowerCase().indexOf(searchText.toLowerCase()) >= 0));
			    }
		    }

		 // check minimum rating
		    if (addToList) {
		    	if(minRating != -1){
		    		addToList = (content.getContentRating() >= minRating);
		    	}
		    }
		    
		 // check maximum price
		    if (addToList) {
		    	if(maxPrice != -1){
		    		addToList = (content.getContentPrice() <= maxPrice);
		    	}
		    }
		    
		 // check country
		    if (addToList) {
			    if (!countryCode.trim().isEmpty()) {
			    	addToList = false;
			    	for(Country cntry : content.getContentCountries())
			    		if(countryCode.compareToIgnoreCase(cntry.getCounteryCode()) == 0){
			    			addToList = true;
			    			break;
			    		}
			    }
		    }
		    
		 // check languages
		    if(addToList && contentLanguages.size() > 0){
		    	addToList = intersection(contentLanguages,content.getContentLanguages());
		    }
		    
		 // check device
		    if (addToList) {
			    if (!deviceId.trim().isEmpty()) {
			    	addToList = false;
			    	for(Device dvce : content.getContentDevices())
			    		if(deviceId.compareToIgnoreCase(dvce.getDeviceId()) == 0){
			    			addToList = true;
			    			break;
			    		}
			    }
		    }
		    
		 // check content type
		    if(addToList && contentType.size() > 0){
		    	addToList = false;
		    	for(String type : contentType){
		    		CONTENT_TYPE ctype;
					try {
						ctype = getContentType(type);
					} catch (ContentParameterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
		    		if(ctype == content.getContentType()){
		    			addToList = true;
		    			break;
		    		}
		    	}
		    }
		    if(addToList){
		    	resultSet.add(content);
		    }
		}
		return resultSet;
		// TODO Auto-generated method stub
	}

	@Override
	public Content getContent(String id) {
		// TODO Auto-generated method stub
		return contentMap.get(id.toUpperCase());
	}
}
