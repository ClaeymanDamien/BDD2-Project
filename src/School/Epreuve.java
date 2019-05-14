package School;

import java.util.ArrayList;
import java.util.Scanner;

public class Epreuve {
	private String idEpreuve;
	private int idCours;
	private int type;
	private MyDate date;
	private ArrayList<Note> notes;
	private int etat;
	private Scanner sc;
	
	static final int TYPE_DE = 1;
	static final int TYPE_TP = 2;
	static final int TYPE_PROJET = 3;
	public static final int ETAT_BULLETIN_EDITE = 1;
	public static final int ETAT_BULLETIN_NON_EDITE = 0;
	
	
	
	
	
	
	public Epreuve(String idEpreuve, int idCours, int type, MyDate date, int etat) {
		this.idEpreuve = idEpreuve;
		this.idCours = idCours;
		this.type = type;
		this.date = date;
		this.etat = etat;
	}
	
	public Epreuve(Scanner sc) {
		this.sc = sc;
	}
	
	@Override
	public String toString() {
		String output;
		output = "L'épreuve: "+ idEpreuve+"\n";
		output += "Type: ";
		if(type == 1)
			output += "DE";
		else if (type==2)
			output += "TP";
		else if (type == 3)
			output += "Projet";
		output += "\n";
		output += "Date: "+date;
		
		return output;
	}
	
	public void createEpreuve() {
		int jour;
		int mois;
		int annee;
		
		System.out.println("Nom de l'épreuve: ");
		idEpreuve = sc.nextLine();
		System.out.println("Id du cours: ");
		idCours = sc.nextInt();
		System.out.println("Type: 1 = DE, 2 = TP, 3 = Projet");
		type = sc.nextInt();
		System.out.println("Date: ");
		System.out.println("-Jour: ");
		jour = sc.nextInt();
		System.out.println("-Mois: ");
		mois = sc.nextInt();
		System.out.println("-Annee: ");
		annee = sc.nextInt();
		sc.nextLine();
		date = new MyDate(jour, mois, annee);
		etat = 0;
	}
	
	public String getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(String idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public int getIdCours() {
		return idCours;
	}
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public MyDate getDate() {
		return date;
	}
	public void setDate(MyDate date) {
		this.date = date;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	
	public int getEtat() {
		return etat;
	}
	
	public void setEtat(int etat) {
		this.etat = etat;
	}
}
