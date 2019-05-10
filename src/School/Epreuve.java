package School;

import java.util.ArrayList;
import java.util.Date;

public class Epreuve {
	private String idEpreuve;
	private int idCours;
	private int type;
	private Date date;
	private ArrayList<Note> notes;
	
	
	private static final int TYPE_DE = 1;
	private static final int TYPE_TP = 2;
	private static final int TYPE_PROJET = 3;
	
	
	
	
	public Epreuve(String idEpreuve, int idCours, int type, Date date) {
		this.idEpreuve = idEpreuve;
		this.idCours = idCours;
		this.type = type;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
}
