package emailApp;

public class Email {
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private String contact;
	private int defaultPasswordLength = 10;
	
	// Constructor to receive first and last name
	public Email(String firstName, String lastName, String depChoice, String contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = depChoice;
		this.contact = contact;
		
		// Call a method that returns a random password
		this.password = randomPassword(defaultPasswordLength);
		
		Database db = new Database(this.firstName, this.lastName, this.department, this.contact, this.password);
		this.email = db.getEmail();
	}
	
		
	// Generate a random password
	private String randomPassword(int length) {
		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
		char[] password = new char[length];
		for(int i=0;i<length;i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
		}
		String newPassword = new String(password);
		EncryptionDecryption ed = new EncryptionDecryption(newPassword, 1);
		String encryptedPassword = ed.getEncryptedPassword();
		return encryptedPassword;
	}
	
	public String showInfo() {
		String info =  "DISPLAY NAME: " + firstName + " " + lastName + "\nCOMPANY EMAIL: " + email + "\nPASSWORD: " + password;
		return info;
	}
}
