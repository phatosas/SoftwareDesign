package cscie97.asn3.ecommerce.collection;

/**
 * @author ssingh
 * Represents exception class for collection API service.
 */
public class CollectionServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String paramName;
	private String paramVal;
	
	/**
	 * @param paramName Name of invalid parameter
	 * @param paramVal Value of invalid parameter
	 */
	public CollectionServiceException(String paramName, String paramVal) {
		super("Collection Parameter Exception: Property Name : [" + paramName + 
				"] and Property value : [" + paramVal + "]");
		this.setParamName(paramName);
		this.setParamVal(paramVal);
	}
	
	/**
	 * @return Parameter Name
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName Parameter Name
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return Parameter Value
	 */
	public String getParamVal() {
		return paramVal;
	}

	/**
	 * @param paramVal Parameter Value
	 */
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
}
