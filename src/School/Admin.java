package School;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import ManagerBDD.ManagerDB;

public class Admin {

	private ManagerDB managerDB;
	private Scanner sc;
	
	static final int ADMIN_ID = 123456;
	static final String ADMIN_MDP = "myefrei";
	
	private static final String ERROR_EXIST = "Une des information n'existe pas";
	
	public Admin(Scanner sc, ManagerDB managerDB) {
		this.sc = sc;
		this.managerDB = managerDB;
	}

	public void createNewPromotion()
	{
		String name;
		Promotion promotion;
		
		System.out.println("Nom de la promotion: ");
		name = sc.nextLine();
		
		promotion = new Promotion(name);
		managerDB.insertPromotion(promotion);
		
		System.out.println("Promotion créée");
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
			
			System.out.println("Rééssayer? : Press y sinon n ");
		}while(sc.nextLine().equals("y"));
		
	}
	
	public void createNewProfesseur() {
		int retry;
		do {
			Professeur professeur = new Professeur(sc);
			professeur.createProfesseur();
			if(managerDB.insertProfesseur(professeur))
				System.out.println("Professeur ajouté");
			else
				System.out.println("La classe n'existe pas");
			
			System.out.println("Rééssayer? : Press 1 sinon 2");
			retry = sc.nextInt();
			sc.nextLine();
		}while(retry == 1);
	}
	
	public Professeur selectProfesseur() {
		System.out.println("Id du professeur: ");
		Professeur professeur = managerDB.selectProfesseur(sc.nextInt());
		sc.nextLine();
		return professeur;
	}
	
	public Cours selectCours() {
		System.out.println("Id du cours: ");
		Cours cours = managerDB.selectCours(sc.nextInt());
		sc.nextLine();
		return cours;
	}
	
	public Epreuve selectEpreuve() {
		System.out.println("Id de l'épreuve: ");
		Epreuve epreuve = managerDB.selectEpreuve(sc.nextLine());

		return epreuve;
	}
	
	public void createNewStudent() {
		int retry;
		do {
			Student student = new Student(sc);
			student.createStudent();
			if(managerDB.insertStudent(student))
				System.out.println("Etudiant ajouté");
			else
				System.out.println("La classe n'existe pas");
			
			System.out.println("Rééssayer? : Press 1 sinon 2");
			retry = sc.nextInt();
			sc.nextLine();
		}while(retry == 1);
	}
	
	public void createEpreuve() {
		int retry;
		do {
			Epreuve epreuve = new Epreuve(sc);
			epreuve.createEpreuve();
			
			if(managerDB.insertEpreuve(epreuve))
				System.out.println("Epreuve ajouté");
			else
				System.out.println("La classe n'existe pas");
			
			System.out.println("Rééssayer? : Press 1 sinon 2");
			retry = sc.nextInt();
			sc.nextLine();
		}while(retry == 1);
	}
	
	public Student selectStudent() {
		System.out.println("Id de l'étudiant: ");
		Student student = managerDB.selectStudent(sc.nextInt());
		sc.nextLine();
		return student;
	}
	public void updateStudent() {
		int retry;
		do {
			
			Student currentStudent = selectStudent();
			Student student = new Student(sc);
			student.setId(currentStudent.getId());
			student.getCoordonnees().setIdCoordonnees(currentStudent.getIdCoordonnees());
			student.getTuteur().setId(currentStudent.getIdTuteur());
			student.getTuteur().getCoordonnees().setIdCoordonnees(currentStudent.getTuteur().getIdCoordonnees());
			
			student.createStudent();
			
			managerDB.updateStudent(student);
			
			System.out.println("Etudiant modifié");
			
			
			System.out.println("Rééssayer? : Press 1 sinon 2");
			retry = sc.nextInt();
			sc.nextLine();
		}while(retry == 1);
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
				System.out.println("La classe où il y a l'étudiant, n'existe pas");	
			
			
			System.out.println("Voulez-vous continuer à en ajouter? : Press 1 sinon 2");
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
			
			
			System.out.println("Voulez-vous continuer à en ajouter? : Press 1 sinon 2");
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
	
	public void updateNote() {
		String idEpreuve;
		int idStudent;
		Double note;
		
		System.out.println("Nom de l'épreuve: ");
		idEpreuve = sc.nextLine();
		System.out.println("ID de l'élève: ");
		idStudent = sc.nextInt();
		System.out.println("Note à modifier: ");
		note = sc.nextDouble();
		
		
		managerDB.updateNote(idEpreuve, idStudent, note);
		
	}
	
	public void validationDesNotes() {
		List<String> idEpreuves = new ArrayList<String>();
		System.out.println("Choisir la promotion où valider les notes");
		
		idEpreuves = managerDB.selectEpreuvesPromo(sc.nextLine());
		
		for(String idEpreuve : idEpreuves) {
			managerDB.updateEtatEpreuve(idEpreuve, Epreuve.ETAT_BULLETIN_EDITE);
		}
		System.out.println("Notes validées");
	}
	
}
