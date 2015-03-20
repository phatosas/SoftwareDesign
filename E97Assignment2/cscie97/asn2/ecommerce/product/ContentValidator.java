package cscie97.asn2.ecommerce.product;

import java.util.List;

/**
 * This auxiliary class defines some static functions to validate properties 
 * of country, device and content
 * @author ssingh
 *
 */
class ContentValidator {
	
	/**
	 * Validates if country code is 2 letter word, throws ContentParameterException 
	 * otherwise.
	 * @param countryCode Country Code
	 * @throws ContentParameterException
	 */
	public static void validateCountryCode(String countryCode) throws ContentParameterException{	
		if(countryCode.isEmpty() || countryCode.length() != 2)
			throw new ContentParameterException("Invalid Country Code",countryCode);
	}
	
	/**
	 * Validates if country name is not blank, 
	 * throws ContentParameterException otherwise.
	 * @param countryName Country Name
	 * @throws ContentParameterException
	 */
	public static void validateCountryName(String countryName) throws ContentParameterException{	
		if(countryName.isEmpty())
			throw new ContentParameterException("CountryName","is blank");
	}
	
	/**
	 * Validates if export status is either open or closed. 
	 * Throws ContentParameterException otherwise
	 * @param exportStatus Export Status of a country
	 * @throws ContentParameterException
	 */
	public static void validateExportStatus(String exportStatus) throws ContentParameterException{	
		if(exportStatus.isEmpty() || (exportStatus.compareToIgnoreCase("open") != 0
				&& exportStatus.compareToIgnoreCase("closed") != 0))
			throw new ContentParameterException("Invalid Country Code",exportStatus);
	}
	
	/**
	 * Validates if device name is not blank, 
	 * throws ContentParameterException otherwise.
	 * @param device device name
	 * @throws ContentParameterException
	 */
	public static void validateDevice(String device) throws ContentParameterException{	
		if(device.isEmpty())
			throw new ContentParameterException("Invalid Device Name/Code",device);
	}
	
	/**
	 * Validates if content id is not blank, 
	 * throws ContentParameterException otherwise.
	 * @param contentId Content Id
	 * @throws ContentParameterException
	 */
	public static void validateContentId(String contentId) throws ContentParameterException{	
		if(contentId.isEmpty())
			throw new ContentParameterException("ContentId","is blank");
	}
	/**
	 * Validates if content type is not blank, 
	 * throws ContentParameterException otherwise.
	 * @param contentType type of content
	 * @throws ContentParameterException
	 */
	public static void validateContentType(String contentType) throws ContentParameterException{	
		if(contentType.isEmpty())
			throw new ContentParameterException("ContentType","is blank");
	}
	/**
	 * Validates if content name is not blank, 
	 * throws ContentParameterException otherwise
	 * @param name content name
	 * @throws ContentParameterException
	 */
	public static void validateName(String name) throws ContentParameterException{	
		if(name.isEmpty())
			throw new ContentParameterException("Name","is blank");
	}
	/**
	 * Validates if content description  is not blank, 
	 * throws ContentParameterException otherwise.
	 * @param description content description
	 * @throws ContentParameterException
	 */
	public static void validateDescription(String description) throws ContentParameterException{	
		if(description.isEmpty())
			throw new ContentParameterException("Description","is blank");
	}
	/**
	 * 
	 * Validates if rating is between 0 and 5, 
	 * throws ContentParameterException otherwise.
	 * @param author content author
	 * @throws ContentParameterException
	 */
	public static void validateAuthor(String author) throws ContentParameterException{	
		if(author.isEmpty())
			throw new ContentParameterException("Author","is blank");
	}
	/**
	 * Validates if country list is not empty, 
	 * throws ContentParameterException otherwise.
	 * @param rating content rating
	 * @throws ContentParameterException
	 */
	public static void validateRating(int rating) throws ContentParameterException{	
		if(rating < 0 || rating > 5)
			throw new ContentParameterException("Author",Integer.toString(rating));
	}
	/**
	 * Validates if device list is not empty, 
	 * throws ContentParameterException otherwise.
	 * @param imageURL content image URL
	 * @throws ContentParameterException
	 */
	public static void validateImageURL(String imageURL) throws ContentParameterException{	
		if(imageURL.isEmpty())
			throw new ContentParameterException("IMAGE_URL","is blank");
	}
	/**
	 * Validates if country list is not empty, 
	 * throws ContentParameterException otherwise.
	 * @param countries List of countries in which content is available
	 * @throws ContentParameterException
	 */
	public static void validateCountry(List<String> countries) throws ContentParameterException{	
		if(countries.isEmpty())
			throw new ContentParameterException("Country","List is empty");
	}
	/**
	 * Validates if device list is not empty, 
	 * throws ContentParameterException otherwise.
	 * @param devices List of devices content is compatible with
	 * @throws ContentParameterException
	 */
	public static void validateDevice(List<String> devices) throws ContentParameterException{	
		if(devices.isEmpty())
			throw new ContentParameterException("Device","List is empty");
	}
	/**
	 * Validates if language list is not empty, 
	 * throws ContentParameterException otherwise.
	 * @param languages list of languages content supports
	 * @throws ContentParameterException
	 */
	public static void validateLanguage(List<String> languages) throws ContentParameterException{	
		if(languages.isEmpty())
			throw new ContentParameterException("Languages","List is empty");
	}
}
