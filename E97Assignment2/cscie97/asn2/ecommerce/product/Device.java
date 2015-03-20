package cscie97.asn2.ecommerce.product;

/**
 * This class represents device objects 
 * and encapsulates all the properties of a device (id, name, manufacturer name).
 * @author ssingh
 *
 */
class Device {
	private final String deviceId;
	private String deviceName;
	private String mfgName;
	
	/**
	 * Constructor
	 * @param deviceId Device Id
	 * @param deviceName Device Name
	 * @param mfgName manufacturer name
	 */
	public Device(String deviceId, String deviceName, String mfgName) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.mfgName = mfgName;
	}
	/**
	 * @return Device Id
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @return Device Name
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * @param deviceName Device Name
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * @return manufacturer name
	 */
	public String getMfgName() {
		return mfgName;
	}
	/**
	 * @param mfgName manufacturer name
	 */
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	
}
