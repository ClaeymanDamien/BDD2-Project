package School;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Admin {

	private ConnexionJ connexionJ;
	private ManagerDB managerDB;
	private Scanner sc;
	
	private static final String ERROR_EXIST = "Une des information n'existe pas";
	
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
		
		do {
			System.out.println("Choisir la promotion où ajouter la classe: ");
			name = sc.nextLine();
			promotion = new Promotion(name);
			
			if(managerDB.insertClasse(promotion))
				System.out.println("Classe enregistré");
			else
				System.out.println("La promotion n'existe pas");
			
			System.out.println("Rééssayer? : Press y ");
		}while(sc.nextLine().equals("y"));
		
		
	}
	
	public void createNewStudent() {
		do {
			Student student = new Student(sc);
			student.createStudent();
			if(managerDB.insertStudent(student))
				System.out.println("Etudiant ajouté");
			else
				System.out.println("La classe n'existe pas");
			
			System.out.println("Rééssayer? : Press 1 ");
		}while(sc.nextInt() == 1);
		sc.nextLine();
	}
	
	public void updateStudent() {
		
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
		sc.nextLine();
	}
	
	public void addCoursToClasse() {
		int idCours;
		int idClasse;
		int idProfesseur;
		
		do {
			
			System.out.println("Choisir l'id de la classe où ajouter cours: ");
			idClasse = sc.nextInt();
			
			System.out.println("Choix du cours: ");
			idCours = sc.nextInt();
			
			System.out.println("Choix du professeur: ");
			idProfesseur = sc.nextInt();
			
			if(!managerDB.insertCoursToClasse(idClasse, idCours, idProfesseur))
				System.out.println(ERROR_EXIST);	
			
			
			System.out.println("Voulez-vous continuer à en ajouter? : Press 1 ");
		}while(sc.nextInt() == 1);
		sc.nextLine();
	}
	
	public void createNewCours() {
		Cours cours = new Cours(sc);
		cours.fillCours();
		if(managerDB.insertCours(cours))
			System.out.println("Cours créé");
		else
			System.out.println("Une information est invalide");
	}
	
	
}
