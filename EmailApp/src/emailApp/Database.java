package emailApp;

import java.sql.*;

public class Database {
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private String contact;
	private int id;
	private String companySuffix = "xyzcompany.com";
	String url="jdbc:mysql://localhost:3306/";
	String user="root";
	String sqlPassword="";
	java.sql.Driver d;
	Connection con;
	
	public Database(String firstName, String lastName, String department, String contact, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.department = department;
		this.contact = contact;
		
		id = connectDb();
		id = id + 101;
		String uniNo = Integer.toString(id);
		
		// Combine elements to generate email
		email = firstName.toLowerCase() + lastName.toLowerCase() + uniNo + "@" + department.toLowerCase() + "." + companySuffix;
		
		insertData();
	}
	
	// Connect database
	private int connectDb() {
		try {
			d=new com.mysql.cj.jdbc.Driver();
			con = DriverManager.getConnection(url, user, sqlPassword);
			
			Statement stat = con.createStatement();
			
			// Create and select our database
			stat.execute("CREATE DATABASE IF NOT EXISTS emailapp");
			stat.execute("USE emailapp");
			
			// Create table
//			stat.execute("DROP TABLE IF EXISTS email");
			stat.execute("CREATE TABLE IF NOT EXISTS email (" + 
					"id BIGINT NOT NULL AUTO_INCREMENT," +
					"firstname VARCHAR(25) NOT NULL," + 
					"lastname VARCHAR(25) NOT NULL," +
					"department VARCHAR(25) NOT NULL," +
					"contact VARCHAR(10) NOT NULL," +
					"emailid VARCHAR(50) NOT NULL," +
					"password VARCHAR(3000) NOT NULL," +
					"PRIMARY KEY(id)" +
					")");
			
			// Get count
			String countQuery = "SELECT COUNT(id) FROM email";
			ResultSet rs = stat.executeQuery(countQuery);
			rs.next();
			return rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// Insert Data
	private void insertData() {
		try {			
			String insertQuery = "INSERT INTO email (firstname, lastname, department, contact, emailid, password) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, this.firstName);
			preparedStatement.setString(2, this.lastName);
			preparedStatement.setString(3, this.department);
			preparedStatement.setString(4, this.contact);
			preparedStatement.setString(5, this.email);
			preparedStatement.setString(6, this.password);
			preparedStatement.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getEmail() {
		return this.email;
	}
}
