package School;

public class Note {

	private int idStudent;
	private String idEpreuve;
	private Double note;
	

	public Note(int idStudent, Double note) {
		this.idStudent = idStudent;
		this.note = note;
	}
	
	public Note(int idStudent, String idEpreuve, Double note) {
		this.idStudent = idStudent;
		this.idEpreuve = idEpreuve;
		this.note = note;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(String idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
}
