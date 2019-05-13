package School;

import java.util.ArrayList;

public class Epreuve {
	private String idEpreuve;
	private int idCours;
	private int type;
	private MyDate date;
	private ArrayList<Note> notes;
	private int etat;
	
	
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
