package ManagerBDD;

import java.sql.*;
import java.util.Calendar;

public class ConnexionJ {

	private Connection db;
	
	static final String DB_NAME = "schoolDB";
	
	public ConnexionJ() {
		String url;
		String user;
		String password;
		
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        url="jdbc:mysql://localhost/"+DB_NAME+"?serverTimezone="+Calendar.getInstance().getTimeZone().getID();
	        user="root";
	        password="";
	        
	        this.db = DriverManager.getConnection(url,user,password);
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

	public Connection getDb() {
		return db;
	}
	
}