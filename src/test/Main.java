package test;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;
import School.Admin;
import School.Coordonnees;
import School.Cours;
import School.Professeur;
import School.Promotion;
import School.Student;
import School.Tuteur;

/*
 * TODO
 * 
 * Faire création Professeur
 * Création d'une insertion de note par le prof (il y a que lui qui peut ajouter des notes de sa matière)
 * Création d'une modification des notes par l'administration
 */
public class Main {
	public static void main(String[] args) {
		
		ConnexionJ connexionJ = new ConnexionJ();
		ManagerDB managerDB = new ManagerDB(connexionJ.getDb());
		Scanner sc = new Scanner(System.in);
		/*Coordonnees coordonnees = new Coordonnees(1,"Damien", "Claeyman", "14B Berthelot", "94800", "Villejuif", "0667876545", "dclmn@hotmail.fr");
		System.out.println(managerDB.updateCoordonnees(coordonnees));
		
		managerDB.insertCoordonnees(coordonnees);*/
		
		/*Coordonnees coordonnees = new Coordonnees(2,"Damien", "Claeyman", "14B Berthelot", "94800", "Villejuif", "0667876545", "dclmn@hotmail.fr");
		Tuteur tuteur = new Tuteur(1, coordonnees, 2);
		 System.out.println(tuteur);*/
		
		System.out.println(managerDB.selectStudent(1));
		Professeur professeur = new Professeur();
		System.out.println(professeur);
		/*Promotion promotion = new Promotion("TropBien");
		managerDB.insertPromotion(promotion);*/
			
		/*Coordonnees coordonnees = new Coordonnees(sc);
	
		coordonnees.createCoordonnees();*/
		//Admin admin = new Admin(sc);
		//admin.createNewPromotion();
		
		//admin.addStudentToClasse();
		//admin.createNewClasse();
		//admin.createNewStudent();
		//admin.addCoursToClasse();
		
		/*Cours cours = new Cours("Physique", "On valide", "2019", 2.0, 0.5, 0.2, 0.3);
		managerDB.insertCours(cours);*/
		
		
		//System.out.println(managerDB.selectCours(1));
		
		
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
