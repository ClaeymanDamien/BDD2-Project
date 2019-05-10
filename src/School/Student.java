package School;

import java.util.Scanner;

public class Student {
	private int id;
	private int idCoordonnees;
	private int idClasse;
	private int idTuteur;
	private Tuteur tuteur;
	private Coordonnees coordonnees;
	private Scanner sc;

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
		
		tuteur.createTuteur();
		
		System.out.println("Dans quelle classe voulez vous le mettre: ");
		idClasse = sc.nextInt();
		sc.nextLine();
		
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
}
