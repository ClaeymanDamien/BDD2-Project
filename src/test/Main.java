package test;

import java.util.Date;
import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;


import School.*;



/*
 * TODO
 * 
 * 
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
		
		//System.out.println(managerDB.selectStudent(1));
		//Professeur professeur = new Professeur();
		//System.out.println(professeur);*/
		/*Promotion promotion = new Promotion("TropBien");
		managerDB.insertPromotion(promotion);*/
			
		//managerDB.insertProfesseur(new Professeur(new Coordonnees("Damien", "Claeyman", "g", "g", "g", "g", "g")));
		Professeur professeur = managerDB.selectProfesseur(1);
		professeur.setSc(sc);
		professeur.setManagerDB(managerDB);
		professeur.seeStudentMarks();
		
		/*Student student = managerDB.selectStudent(1);
		student.setManagerDB(managerDB);
		student.printMarksTranscript();*/
		
		//System.out.println(managerDB.checkIfTeachingToStudent(1, 1));
		/*
		 * Il faut que je test select all notes
		 */
		//Epreuve epreuve = new Epreuve("lol", 1, 1, new MyDate(20, 03, 2000));
		//managerDB.insertEpreuve(epreuve);
		//System.out.println(managerDB.selectAllEleveByClasse(1));
		
		
		//managerDB.insertNote(new Note(1, "Pec", 13.6));
		
		
		/*Coordonnees coordonnees = new Coordonnees(sc);
	
		coordonnees.createCoordonnees();*/
		/*Admin admin = new Admin(sc);
		//admin.createNewPromotion();
		admin.updateNote();*/
		//admin.addStudentToClasse();
		//admin.createNewClasse();
		//admin.createNewStudent();
		//admin.addCoursToClasse();
		
		/*Cours cours = new Cours("Physique", "On valide", "2019", 2.0, 0.5, 0.2, 0.3);
		managerDB.insertCours(cours);*/
		
		
		//System.out.println(managerDB.selectCours(1));
		
		
		/*Tuteur tuteur = new Tuteur();
		tuteur.createTuteur();*/

		System.out.println("Fait\n");
		Recherche_Eleves recherche_liste = new Recherche_Eleves(2, 1);

		recherche_liste.recherche_eleves_classe();
		recherche_liste.recherche_eleves_promotion();
		System.out.println("\n");
		/*
		Authentication authentication = new Authentication();
		authentication.authentification();
		*/
		sc.close();
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
