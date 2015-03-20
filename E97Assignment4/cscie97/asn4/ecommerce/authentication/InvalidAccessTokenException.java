package cscie97.asn4.ecommerce.authentication;

import java.util.Date;

/**
 * Represents exception class when user tries to invoke a method but token 
 * is invalid. Token is either expired or user has logged out or user has 
 * passed an invalid token.
 * @author ssingh
 *
 */
public class InvalidAccessTokenException extends Exception {
	private String tokenId;
	private long expiry;
	private String userName;
	private boolean state;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidAccessTokenException(String tokenId, long expiry,
			String userName, boolean state) {
		super("Invalid Access Token : " + tokenId + " , User = " + userName
				+ " , state = " + state  + " , Expired at :" + new Date(expiry));
		this.tokenId = tokenId;
		this.expiry = expiry;
		this.userName = userName;
		this.state = state;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public long getExpiry() {
		return expiry;
	}
	public void setExpiry(long expiry) {
		this.expiry = expiry;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
