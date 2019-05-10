package School;

import java.util.Scanner;

public class Cours {
	private int id;
	private String nom;
	private String description;
	private String annee;
	private double coefficient;
	private double pourcentageDE;
	private double pourcentageTP;
	private double pourcentageProjet;
	private Scanner sc;

	public Cours(int id, String nom, String description, String annee, double coefficient,
			double pourcentageDE, double pourcentageTP, double pourcentageProjet) {
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.annee = annee;
		this.coefficient = coefficient;
		this.pourcentageDE = pourcentageDE;
		this.pourcentageTP = pourcentageTP;
		this.pourcentageProjet = pourcentageProjet;
	}
	
	public Cours(String nom, String description, String annee, double coefficient, double pourcentageDE,
			double pourcentageTP, double pourcentageProjet) {
		this.nom = nom;
		this.description = description;
		this.annee = annee;
		this.coefficient = coefficient;
		this.pourcentageDE = pourcentageDE;
		this.pourcentageTP = pourcentageTP;
		this.pourcentageProjet = pourcentageProjet;
	}
	
	public Cours(Scanner sc) {
		this.sc = sc;
	}


	public void fillCours() {
		System.out.println("Formulaire cours: ");
		System.out.println();

		System.out.println("Nom du cours: ");
		nom = sc.nextLine();
		System.out.println("Description ");
		description = sc.nextLine();
		System.out.println("Annee: ");
		annee = sc.nextLine();
		System.out.println("Coefficient: ");
		coefficient = sc.nextDouble();
		System.out.println("Pourcentage du DE: ");
		pourcentageDE = sc.nextDouble();
		System.out.println("Pourcentage du TP: ");
		pourcentageTP = sc.nextDouble();
		System.out.println("Pourcentage du Projet: ");
		pourcentageProjet = sc.nextDouble();
	}
	
	@Override
	public String toString() {
		String output = "Matière: " + nom + "\n";
		output += "===================\n" + description + "\n===================\n";
		output += "Coefficient: " + coefficient +"\n";
		output += "Evaluation: \n";
		output += "DE: " + pourcentageDE + "\n";
		output += "TP: " + pourcentageTP + "\n";
		output += "Projet: " + pourcentageProjet + "\n";
		return output;
	}
	
	/*
	 * Setter / Getter
	 */
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String prenom) {
		this.description = prenom;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public double getPourcentageDE() {
		return pourcentageDE;
	}
	public void setPourcentageDE(double pourcentageDE) {
		this.pourcentageDE = pourcentageDE;
	}
	public double getPourcentageTP() {
		return pourcentageTP;
	}
	public void setPourcentageTP(double pourcentageTP) {
		this.pourcentageTP = pourcentageTP;
	}
	public double getPourcentageProjet() {
		return pourcentageProjet;
	}
	public void setPourcentageProjet(double pourcentageProjet) {
		this.pourcentageProjet = pourcentageProjet;
	}
}
