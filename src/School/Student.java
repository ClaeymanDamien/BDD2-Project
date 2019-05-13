package School;

import java.util.List;
import java.util.Scanner;

import ManagerBDD.ManagerDB;


public class Student {
	private int id;
	private int idCoordonnees;
	private int idClasse;
	private int idTuteur;
	private Tuteur tuteur;
	private Coordonnees coordonnees;
	private Scanner sc;
	private List<Note> notes;
	private ManagerDB managerDB;
	private Authentification authentification;

	/*
	 * Constructeur 
	 */
	
	public Student(int idCoordonnees, int idClasse, int idTuteur) {
		this.idCoordonnees = idCoordonnees;
		this.idClasse = idClasse;
		this.idTuteur = idTuteur;
	}
	
	public Student(int id, int idCoordonnees, int idClasse, int idTuteur) {
		this.id = id;
		this.idCoordonnees = idCoordonnees;
		this.idClasse = idClasse;
		this.idTuteur = idTuteur;
	}

	

	public Student(int idClasse, int idTuteur, String nom, String prenom, String adresse, String codePostal, String ville, String tel, String email) {
		this.idClasse = idClasse;
		this.idTuteur = idTuteur;
		this.coordonnees = new Coordonnees(nom, prenom, adresse, codePostal, ville, tel, email);
	}
	

	public Student(Tuteur tuteur, Coordonnees coordonnees) {
		this.tuteur = tuteur;
		this.coordonnees = coordonnees;
	}

	public Student(int idClasse, Tuteur tuteur, Coordonnees coordonnees) {
		this.idClasse = idClasse;
		this.tuteur = tuteur;
		this.coordonnees = coordonnees;
	}
	
	public Student() {
		tuteur = new Tuteur();
		coordonnees = new Coordonnees();
	}
	
	public Student(Scanner sc) {
		tuteur = new Tuteur(sc);
		coordonnees = new Coordonnees(sc);
		authentification = new Authentification(sc);
		this.sc = sc;
	}

	@Override
	public String toString() {
		String output="Etudiant: "+ id +"\n";
		output +="Dans la classe: "+ idClasse +"\n";
		output += coordonnees+"\n";
		output += tuteur;
		
		return output;
	}
	
	public void createStudent() {
		
		System.out.println("Formulaire étudiant: \n");
		coordonnees.createCoordonnees();
		authentification.askMdp();
		
		tuteur.createTuteur();
		
		System.out.println("Dans quelle classe voulez vous le mettre: ");
		idClasse = sc.nextInt();
		sc.nextLine();
		
	}
	
	public void printMarksTranscript() {
		loadMarks();
		String type ="*";
		Double coef = 0.;
		System.out.println("\nRelevé de notes: ");
		System.out.println("**************************************************************");
		
		for(Note note : notes) {
			System.out.print(note.getCours().getNom());
			
			if(note.getEpreuve().getType() == Epreuve.TYPE_DE) {
				type = "DE";
				coef = note.getCours().getPourcentageDE();
			}
			else if (note.getEpreuve().getType() == Epreuve.TYPE_TP) {
				type = "TP";
				coef = note.getCours().getPourcentageTP();
			}	
			else if (note.getEpreuve().getType() == Epreuve.TYPE_PROJET) {
				type = "PJ";
				coef = note.getCours().getPourcentageProjet();
			}
				
			System.out.print(" - "+ type +"("+ coef +") : ");
			System.out.println(note.getNote());
		}
		System.out.println("");
		
	}
	
	public void loadMarks() {
		notes = managerDB.selectStudentNotes(id);
	}
	
	public void generateBulletin() {
		if(managerDB.checkIfStudentMarksAreValidated(id)) {
			System.out.println("\n\t\t\tBulletin 2019");
			System.out.println("**************************************************************");
			System.out.println("\n");
			System.out.println("Elève: ");
			System.out.println(coordonnees.getPrenom() +" "+ coordonnees.getNom());
			System.out.println("Numéro d'étudiant: "+ id);
			System.out.println("Dans la classe: "+ idClasse);
			System.out.println("\n\t\t\t\t\t"+coordonnees.getAdresse());
			System.out.println("\t\t\t\t\t"+coordonnees.getVille());
			System.out.println("\t\t\t\t\t"+coordonnees.getCodePostal());
			printMarksTranscript();
		}
		else {
			System.out.println("Les notes n'ont pas encore été validé");
		}
	}
	
	/*
	 * Getters / Setters
	 */

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
	
	public void setCoordonneesSc(Scanner sc) {
		this.coordonnees.setSc(sc);
	}
	
	public Tuteur getTuteur() {
		return tuteur;
	}
	
	public void setTuteur(Tuteur tuteur) {
		this.tuteur = tuteur;
	}
	
	public int getIdCoordonnees() {
		return idCoordonnees;
	}
	
	public void setIdCoordonnees(int idCoordonnees) {
		this.idCoordonnees = idCoordonnees;
	}

	public int getIdClasse() {
		return idClasse;
	}
	
	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}
	
	public int getIdTuteur() {
		return idTuteur;
	}
	
	public void setIdTuteur(int idTuteur) {
		this.idTuteur = idTuteur;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
	public void setNotes(List<Note> notes) {
		this.notes = notes;
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
