package School;

import java.util.Scanner;
import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Professeur {
	private ManagerDB managerDB;
	private Scanner sc;
	
	private int id;
	private Coordonnees coordonnees;
	private int idCoordonnees;
	
	/*
	 * Constructeur
	 */
	public Professeur(Scanner sc) {
		this.sc = sc;
		coordonnees = new Coordonnees(sc);
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
	}
	
	public void studentsMarks() {
		
		do {
			System.out.println("Choisir le nom de l'épreuve: ");
			sc.nextLine();
		}while(true);
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
	
	
}
