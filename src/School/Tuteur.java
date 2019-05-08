package School;

import java.util.Scanner;

public class Tuteur {
	private int id;
	private Coordonnees coordonnees;
	private Scanner sc;
	
	public Tuteur(String nom, String prenom, String adresse, String codePostal, String ville, String tel, String email) {
		this.coordonnees = new Coordonnees(nom, prenom, adresse, codePostal, ville, tel, email);
	}

	public Tuteur(int id, Coordonnees coordonnees) {
		this.id = id;
		this.coordonnees = coordonnees;
	}
	
	public Tuteur() {
		coordonnees = new Coordonnees();
	}
	
	
	
	public Tuteur(Scanner sc) {
		this.coordonnees = new Coordonnees(sc);
		this.sc = sc;
	}

	public void createTuteur() {
		System.out.println("Formulaire tuteur: ");
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
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
}
