package cscie97.asn4.ecommerce.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class encapsulates login and password of a user. Password is 
 * stored in MD5 encrypted form. It authenticates user’s credential by 
 * encrypting the password and matching it with stored encrypted password. 
 * @author ssingh
 *
 */
class Credential {
	final String userName;
	final String password;
	
	public Credential(String userName, String password) {
		super();
		this.userName = userName;
		this.password = encrypt(password);
	}
	
	/**
	 * Encrypts the password and checks it with stored encrypted password. 
	 * Returns true if it matches otherwise returns false
	 * @param userName login name
	 * @param password password
	 * @return Returns true if it matches otherwise returns false
	 */
	boolean checkCredential(String userName,String password){
			boolean ret = (this.password.compareTo(encrypt(password)) == 0);
			return ret;
	}
	/**
	 * Returns MD5 encrypted password
	 * @param passwd password
	 * @return Returns MD5 encrypted password
	 */
	private String encrypt(String passwd){	 
		try {
			MessageDigest md;
			 md = MessageDigest.getInstance("MD5");
		     md.update(passwd.getBytes());
		     byte byteData[] = md.digest();
		     StringBuffer sb = new StringBuffer();
		     for (int i = 0; i < byteData.length; i++) {
		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		     }
		     return sb.toString();
		}
	     catch (NoSuchAlgorithmException e) {
				//throw your own exception
				e.printStackTrace();
		}
		return null;
	}
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
