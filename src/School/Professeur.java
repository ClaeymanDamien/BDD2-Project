package School;

import java.util.Scanner;

import com.sun.corba.se.pept.transport.Connection;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Professeur {
	private ManagerDB managerDB;
	private Scanner sc;
	
	private int id;
	private Coordonnees coordonnees;
	private int idCoordonnees;
	
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
	
	public Professeur(int idTuteur ,String nom, String prenom, String adresse, String codePostal, String ville, String tel, String email) {
		this.id = idTuteur;
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

	@Override
	public String toString() {
		String output = "Professeur: \n";
		output += coordonnees;
		
		return output;
	}
	
	public void createTuteur() {
		System.out.println("Formulaire professeur: ");
		System.out.println();
		coordonnees.createCoordonnees();
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
