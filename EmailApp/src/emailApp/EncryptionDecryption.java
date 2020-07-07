package emailApp;

import java.security.spec.KeySpec;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;	
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionDecryption {

	private static String secretKey = "emailapp123onetwothree";
	private static String salt = "ssshhhhhhhhhhh!!!!";
	private String password;
	private String encryptedPassword;
	private String decryptedPassword;
	
	public EncryptionDecryption(String password, int n) {
		this.password = password;
		if(n==1) {
			this.encryptedPassword = encrypt(this.password);
		} 
		else {
			this.decryptedPassword = decrypt(this.password);
		}
	}
	 
	private static String encrypt(String strToEncrypt) {
	    try {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        String encryptedPassword = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	        return encryptedPassword;
	    } 
	    catch (Exception e) {
	        System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}
	
	private static String decrypt(String strToDecrypt) {
	    try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        String decryptedPassword = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
	        return decryptedPassword;
	    } 
	    catch (Exception e) {
	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	public String getDecryptedPassword() {
		return decryptedPassword;
	}
}
