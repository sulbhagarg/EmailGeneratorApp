package emailApp;

import java.sql.*;

public class Database {
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private String contact;
	String url="jdbc:mysql://localhost:3306/";
	String user="root";
	String sqlPassword="";
	java.sql.Driver d;
	Connection con;
	
	public Database() {
		connectDb();
	}
	
	public void dataBase(String firstName, String lastName, String department, String contact, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.department = department;
		this.contact = contact;
		this.email= email;

		insertData();
	}
	
	// Connect database
	private void connectDb() {
		try {
			d = new com.mysql.cj.jdbc.Driver();
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
			
			stat.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
			preparedStatement.close();
//			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		try {
			Statement stat = con.createStatement();
			String countQuery = "SELECT COUNT(id) FROM email";
			ResultSet rs = stat.executeQuery(countQuery);
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			return count;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
