package cscie97.asn4.ecommerce.authentication;


/**
 * This class represents access token which are assigned to logged in users. 
 * Access token has a GUID, expiration time and a Boolean state. Expiration 
 * time is two hours from the time user logged in and beyond that access 
 * token becomes invalid. Access also becomes invalid when user logs out. 
 * Each access token is associated with a user.
 * @author ssingh
 *
 */
class AccessToken implements Visitable{
	final String GUID;
	final User user;
	long expirationTime;
	boolean state;
	public AccessToken(String gUID,User user, boolean state) {
		super();
		GUID = gUID;
		this.user = user;
		this.expirationTime = System.currentTimeMillis( ) + 2*60*60*1000;
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public long getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getGUID() {
		return GUID;
	}
	/**
	 * @return Returns false if access token is expired or state is invalid, 
	 * otherwise returns true
	 */
	boolean isValid(){
		return (state && System.currentTimeMillis( ) < this.expirationTime);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
