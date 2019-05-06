package ManagerBDD;

import java.sql.*;
import java.util.Calendar;
public class ConnexionJ {



	public static void main(String[] args) {
		Connection db;
		Statement st;
		ResultSet rst;
		
		
		try {
			db = connexionDB();
			st = db.createStatement();
			rst = st.executeQuery("SELECT * FROM promotion");
			
			while (rst.next()) {
				System.out.println(rst.getString("idPrmotion"));
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	public static Connection  connexionDB(){
		Connection db = null;
		String url;
		String user;
		String password;
		
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        url="jdbc:mysql://localhost/school?serverTimezone="+Calendar.getInstance().getTimeZone().getID();
	        user="root";
	        password="";
	        
	        db = DriverManager.getConnection(url,user,password);
	        
	        return db;
	        
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}
}