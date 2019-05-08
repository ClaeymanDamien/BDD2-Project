package test;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;
import School.Admin;
import School.Coordonnees;
import School.Cours;
import School.Promotion;
import School.Student;
import School.Tuteur;

/*
 * TODO
 * 
 * Faire création Professeur
 */
public class Main {
	public static void main(String[] args) {
		
		ConnexionJ connexionJ = new ConnexionJ();
		ManagerDB managerDB = new ManagerDB(connexionJ.getDb());
		Scanner sc = new Scanner(System.in);
		/*Coordonnees coordonnees = new Coordonnees("Damien", "Claeyman", "14B Berthelot", "94800", "Villejuif", "0667876545", "dclmn@hotmail.fr");
		managerDB.insertCoordonnees(coordonnees);*/
		
		
		/*Tuteur tuteur = new Tuteur("Damien", "Claeyman", "14B Berthelot", "94800", "Villejuif", "0667876545", "dclmn@hotmail.fr");
		managerDB.insertTuteur(tuteur);
		*/
		
		/*Promotion promotion = new Promotion("TropBien");
		managerDB.insertPromotion(promotion);*/
			
		/*Coordonnees coordonnees = new Coordonnees(sc);
	
		coordonnees.createCoordonnees();*/
		Admin admin = new Admin(sc);
		//admin.createNewPromotion();
		
		//admin.addStudentToClasse();
		admin.createNewClasse();
		//admin.createNewStudent();
		//admin.addCoursToClasse();
		
		/*Cours cours = new Cours("Physique", "On valide", "2019", 2.0, 0.5, 0.2, 0.3);
		managerDB.insertCours(cours);*/
		
		
		
		
		/*Tuteur tuteur = new Tuteur();
		tuteur.createTuteur();*/
		
		sc.close();
		System.out.println("Fait");
	}
	
	/*public static void main(String[] args) {
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
	
}*/
}
