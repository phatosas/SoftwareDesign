package cscie97.asn4.ecommerce.product;

/**
 * The class ContentParameterException indicates the exceptional cases
 * when invalid parameters for Content , Country or Devices are 
 * encountered. 
 * @author ssingh
 *
 */
public class ContentParameterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paramName;
	private String paramVal;
	
	/**
	 * @param paramName Name of invalid parameter
	 * @param paramVal Value of invalid parameter
	 */
	public ContentParameterException(String paramName, String paramVal) {
		super("Content Parameter Exception: Property Name : [" + paramName + 
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
