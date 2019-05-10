package School;

import java.util.Scanner;

public class Coordonnees {
	private int idCoordonnees;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String tel;
	private String email;
	private Scanner sc;
	
	
	/*
	 * Constructeur
	 */
	public Coordonnees(String nom, String prenom, String adresse, String codePostal, String ville, String tel,
			String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
	}

	public Coordonnees(int idCoordonnees, String nom, String prenom, String adresse, String codePostal, String ville,
			String tel, String email) {
		this.idCoordonnees = idCoordonnees;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.tel = tel;
		this.email = email;
	}
	
	public Coordonnees() {

	}
	
	
	
	public Coordonnees(Scanner sc) {
		this.sc = sc;
	}

	public void createCoordonnees() {
		
		System.out.println("Nom: ");
		nom = sc.nextLine();
		System.out.println("Prenom: ");
		prenom = sc.nextLine();
		System.out.println("Adresse: ");
		adresse = sc.nextLine();
		System.out.println("CodePostal: ");
		codePostal = sc.nextLine();
		System.out.println("Ville: ");
		ville = sc.nextLine();
		System.out.println("Télephone: ");
		tel = sc.nextLine();
		System.out.println("Email: ");
		email = sc.nextLine();	
	}
	
	@Override
	public String toString() {
		String output = "==================\n";
		output += "Coordonnées: \n";
		output += "Nom: "+ nom +"\n";
		output += "Prenom: "+ prenom +"\n";
		output += "Adresse: "+adresse +"\n";
		output += "Code postal: "+ codePostal +"\n";
		output += "Ville: "+ ville +"\n";
		output += "Téléphone: "+ tel +"\n";
		output += "Email: "+ email +"\n";
		output += "==================\n";
		return output;
	}
	
	/*
	 * Getter / Setter
	 */

	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public int getIdCoordonnees() {
		return idCoordonnees;
	}

	public void setIdCoordonnees(int idCoordonnees) {
		this.idCoordonnees = idCoordonnees;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
