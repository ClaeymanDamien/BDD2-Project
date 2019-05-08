package ManagerBDD;

import java.sql.*;
import java.util.Calendar;

public class ConnexionJ {

	private Connection db;
	
	public ConnexionJ() {
		String url;
		String user;
		String password;
		
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        url="jdbc:mysql://localhost/test2?serverTimezone="+Calendar.getInstance().getTimeZone().getID();
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