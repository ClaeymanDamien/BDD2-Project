package School;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Admin {

	ConnexionJ connexionJ;
	ManagerDB managerDB;
	Scanner sc;
	
	public Admin(Scanner sc) {
		this.sc = sc;
		connexionJ = new ConnexionJ();
		managerDB = new ManagerDB(connexionJ.getDb());
	}

	public Admin() {
		connexionJ = new ConnexionJ();
		managerDB = new ManagerDB(connexionJ.getDb());
	}
	
	public void createNewPromotion()
	{
		String name;
		Promotion promotion;
		
		System.out.println("Nom de la promotion: ");
		name = sc.nextLine();
		
		promotion = new Promotion(name);
		managerDB.insertPromotion(promotion);
		
	}
	
	public void createNewClasse() {
		String name;
		Promotion promotion;
		
		System.out.println("Choisir la promotion où ajouter la classe: ");
		name = sc.nextLine();
		promotion = new Promotion(name);
		
		if(managerDB.insertClasse(promotion))
			System.out.println("Classe enregistré");
		else
			System.out.println("La promotion n'existe pas");
	}
	
	public void createStudent() {
		Student student = new Student(sc);
		student.createStudent();
		if(managerDB.insertStudent(student))
			System.out.println("Etudiant ajouté");
		else
			System.out.println("La classe n'existe pas");
	}
	
	public void addStudentToClasse() {
		int idEleve;
		int idClasse;
		
		do {
			
			System.out.println("Choisir l'id de la classe où ajouter l'élève: ");
			idClasse = sc.nextInt();
			System.out.println("Choisir l'élève");
			idEleve = sc.nextInt();
			
			if(!managerDB.updateClasseStudent(idClasse, idEleve))
				System.out.println("La classe ou l'élève n'existe pas");	
			
			
			System.out.println("Voulez-vous continuer à en ajouter? : Press 1 ");
		}while(sc.nextInt() == 1);
	}
	
	
}
