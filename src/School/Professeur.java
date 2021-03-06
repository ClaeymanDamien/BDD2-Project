package School;

import java.util.List;
import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Professeur {
	private ManagerDB managerDB;
	private Scanner sc;
	
	private int id;
	private Coordonnees coordonnees;
	private int idCoordonnees;
	private Authentification authentification;
	
	
	
	/*
	 * Constructeur
	 */
	public Professeur(Scanner sc) {
		this.sc = sc;
		coordonnees = new Coordonnees(sc);
		authentification = new Authentification(sc);
	}
	
	public Professeur(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public Professeur(Scanner sc, ConnexionJ db, int id, Coordonnees coordonnees, int idCoordonnees) {
		this.sc = sc;
		coordonnees = new Coordonnees(sc);
		managerDB = new ManagerDB(db.getDb());
		this.id = id;
		this.coordonnees = coordonnees;
		this.idCoordonnees = idCoordonnees;
	}
	
	public Professeur() {
		coordonnees = new Coordonnees();
	}
	
	public Professeur(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String email) {
		this.coordonnees = new Coordonnees(nom, prenom, adresse, codePostal, ville, tel, email);
	}
	
	public Professeur(int idProfesseur ,String nom, String prenom, String adresse, String codePostal, String ville, String tel, String email) {
		this.id = idProfesseur;
		this.coordonnees = new Coordonnees(nom, prenom, adresse, codePostal, ville, tel, email);
	}

	public Professeur(int id, Coordonnees coordonnees) {
		this.id = id;
		this.coordonnees = coordonnees;
	}
	
	
	public Professeur(int id, int idCoordonnees) {
		this.id = id;
		this.idCoordonnees = idCoordonnees;
	}

	public Professeur(int id, Coordonnees coordonnees, int idCoordonnees) {
		this.id = id;
		this.coordonnees = coordonnees;
		this.idCoordonnees = idCoordonnees;
	}

	/*
	 * Action
	 */
	
	public void createProfesseur() {
		System.out.println("Formulaire professeur: ");
		System.out.println();
		coordonnees.createCoordonnees();
		authentification.askMdp();
	}
	
	public void studentsMarks() {
		Epreuve epreuve = null;
		String idEpreuve = null;
		List<Student> students = null;
		Note note = null;
		int idClasse = -1;
		
		Boolean success = false;
		do {
			System.out.println("Choisir le nom de l'épreuve: ");
			idEpreuve = sc.nextLine();
			epreuve = managerDB.selectEpreuve(idEpreuve);
			
			if(epreuve != null) {
				System.out.println("Choisir le numéro de la classe");
				idClasse = sc.nextInt();
				sc.nextLine();
				
				if(managerDB.selectIdProfDispenceCours(idClasse, epreuve.getIdCours()) == id) {
					success = true;
				}
				else {
					System.out.println("Vous ne vous occupez pas de ce cours");
				}
			}else {
				System.out.println("L'épreuve n'existe pas");
			}

	
		}while(!success);
		
		if(epreuve.getEtat() != Epreuve.ETAT_BULLETIN_EDITE) {
			students = managerDB.selectAllEleveByClasse(idClasse);
			if(!managerDB.searchIfMarksEntered(students.get(0).getId(), epreuve.getIdEpreuve())) {
				for(Student student : students) {
					System.out.println("Elève: "
							+student.getCoordonnees().getPrenom() 
							+" "
							+student.getCoordonnees().getNom()
							+"\nEntrer sa note: "
						);
					
					note = new Note(
							student.getId(), 
							epreuve.getIdEpreuve(), 
							sc.nextDouble()
						);
					
					managerDB.insertNote(note);
				}
				sc.nextLine();
				managerDB.updateEtatEpreuve(epreuve.getIdEpreuve(), 1);
				System.out.println("Toute les notes on étaient relevées");
			}
			else {
				System.out.println("Les notes ont déjà été pour cette matière");
			}
			
		}
		else {
			System.out.println("Le bulletin a été édité");
		}
		
	}
	
	public void seeStudentMarks() {
		int idStudent;
		Student student;
		System.out.println("Choisir id de l'élève: ");
		idStudent = sc.nextInt();
		
		if(managerDB.checkIfTeachingToStudent(idStudent, id)) {
			student = managerDB.selectStudent(idStudent);
			student.setManagerDB(managerDB);
			student.printMarksTranscript();
		}
		else {
			System.out.println("Vous n'êtes pas le professeur de cette étudiant");
		}
		
	}
	
	public void seeStudentBulletin() {
		int idStudent;
		Student student;
		System.out.println("Choisir id de l'élève: ");
		idStudent = sc.nextInt();
		
		if(managerDB.checkIfTeachingToStudent(idStudent, id)) {
			student = managerDB.selectStudent(idStudent);
			student.setManagerDB(managerDB);
			student.generateBulletin();
		}
		else {
			System.out.println("Vous n'êtes pas le professeur de cette étudiant");
		}
		
	}
	
	public void updateNote() {
		String idEpreuve;
		int idStudent;
		Double note;
		Epreuve epreuve = null;
		
		System.out.println("Nom de l'épreuve: ");
		idEpreuve = sc.nextLine();
		epreuve = managerDB.selectEpreuve(idEpreuve);
		
		if(epreuve != null) {
			if(epreuve.getEtat() != Epreuve.ETAT_BULLETIN_EDITE) {
				System.out.println("ID de l'élève: ");
				idStudent = sc.nextInt();
				if(managerDB.checkIfTeachingToStudent(idStudent, id)) {
					System.out.println("Note à modifier: ");
					note = sc.nextDouble();
					
					managerDB.updateNote(idEpreuve, idStudent, note);
					System.out.println("Note modifié");
				}
				else {
					System.out.println("Vous n'ête pas le professeur de cette élève");
				}
			}
			else {
				System.out.println("Impossible: Le bulletin a déjà était édité");
			}
		}
		else {
			System.out.println("L'épreuve n'existe pas");
		}
		
		
	}
	
	
	
	@Override
	public String toString() {
		String output = "Professeur: \n";
		output += coordonnees;
		
		return output;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	
	public int getIdCoordonnees() {
		return idCoordonnees;
	}
	
	public void setIdCoordonnees(int idCoordonnees) {
		this.idCoordonnees = idCoordonnees;
	}
	
	public void setManagerDB(ManagerDB managerDB) {
		this.managerDB = managerDB;
	}
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public Authentification getAuthentification() {
		return authentification;
	}
	
	public void setAuthentification(Authentification authentification) {
		this.authentification = authentification;
	}
	
}
