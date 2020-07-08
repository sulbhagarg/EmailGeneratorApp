package emailApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendPassword {
	private String password;
	private String contact;
	private String company;
	public SendPassword(String password, String contact, String company) {
		this.password = password;
		this.contact = contact;
		this.company = company;
	}
	
	public Boolean sendPassword() {
		EncryptionDecryption ed = new EncryptionDecryption(password, 2);
		String decryptedPassword = ed.getDecryptedPassword();
		try {
			String apiKey = "apiKey=" + "vDeVECPbj6o-Uak9FncUHeHLDWLV3Sbx3JmQ5OKplN";
			String message = "&message=" + URLEncoder.encode("Your password for " + company + " is: " + decryptedPassword, "UTF-8");
			String number = "&numbers=" + contact;
			String apiUrl = "https://api.textlocal.in/send/?" + apiKey + message + number;
			
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line = "";
			StringBuilder sb = new StringBuilder();
			
			while( (line = reader.readLine()) != null ) {
				sb.append(line).append("\n");
			}
			
			System.out.println(sb.toString());
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
