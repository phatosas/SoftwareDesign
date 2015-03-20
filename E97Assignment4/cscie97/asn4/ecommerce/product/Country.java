package cscie97.asn4.ecommerce.product;

/**
 * This class represents country objects 
 * and encapsulates all the properties of a country (code, name, export status).
 * @author ssingh
 *
 */
class Country {
	
	public enum EXPORT_STATUS{OPEN,CLOSED};
	
	private final String counteryCode;
	private String countryName;
	private EXPORT_STATUS exportStatus;
	
	/**
	 * Constructor
	 * @param countryCode Country Code 
	 * @param countryName Name of Country
	 * @param exportStatus Export Status ( OPEN or CLOSED)
	 */
	public Country(String counteryCode, String countryName,
			EXPORT_STATUS exportStatus) {
		super();
		this.counteryCode = counteryCode;
		this.countryName = countryName;
		this.exportStatus = exportStatus;
	}
	
	/**
	 * @return Country Code 
	 */
	public String getCounteryCode() {
		return counteryCode;
	}
	
	/**
	 * @return  Country Name 
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName Country Name 
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return Export Status ( OPEN or CLOSED)
	 */
	public EXPORT_STATUS getExportStatus() {
		return exportStatus;
	}
	/**
	 * @param exportStatus Export Status ( OPEN or CLOSED)
	 */
	public void setExportStatus(EXPORT_STATUS exportStatus) {
		this.exportStatus = exportStatus;
	}
}
