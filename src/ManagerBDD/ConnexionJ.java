package ManagerBDD;

import java.sql.*;
import java.util.Calendar;
public class ConnexionJ {



	public static void main(String[] args) {
		Connection connection = connecterDB();
	}
	public static Connection  connecterDB(){
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        String url="jdbc:mysql://localhost/school?serverTimezone="+Calendar.getInstance().getTimeZone().getID();
	        String user="root";
	        String password="";
	       Connection cnx=DriverManager.getConnection(url,user,password);
	        System.out.println("Connexion bien établié");
	        return cnx;
	        
	    }catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	}
}