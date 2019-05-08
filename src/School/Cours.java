package School;

public class Cours {
	private int id;
	private int idProfesseur;
	private String nom;
	private String description;
	private String annee;
	private float coefficient;
	private float pourcentageDE;
	private float pourcentageTP;
	private float pourcentageProjet;
	
	
	
	
	public Cours(int id, int idProfesseur, String nom, String description, String annee, float coefficient,
			float pourcentageDE, float pourcentageTP, float pourcentageProjet) {
		this.id = id;
		this.idProfesseur = idProfesseur;
		this.nom = nom;
		this.description = description;
		this.annee = annee;
		this.coefficient = coefficient;
		this.pourcentageDE = pourcentageDE;
		this.pourcentageTP = pourcentageTP;
		this.pourcentageProjet = pourcentageProjet;
	}
	
	
	
	public Cours(int idProfesseur, String nom, String description, String annee, float coefficient, float pourcentageDE,
			float pourcentageTP, float pourcentageProjet) {
		this.idProfesseur = idProfesseur;
		this.nom = nom;
		this.description = description;
		this.annee = annee;
		this.coefficient = coefficient;
		this.pourcentageDE = pourcentageDE;
		this.pourcentageTP = pourcentageTP;
		this.pourcentageProjet = pourcentageProjet;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProfesseur() {
		return idProfesseur;
	}
	public void setIdProfesseur(int idProfesseur) {
		this.idProfesseur = idProfesseur;
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
	public float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
	public float getPourcentageDE() {
		return pourcentageDE;
	}
	public void setPourcentageDE(float pourcentageDE) {
		this.pourcentageDE = pourcentageDE;
	}
	public float getPourcentageTP() {
		return pourcentageTP;
	}
	public void setPourcentageTP(float pourcentageTP) {
		this.pourcentageTP = pourcentageTP;
	}
	public float getPourcentageProjet() {
		return pourcentageProjet;
	}
	public void setPourcentageProjet(float pourcentageProjet) {
		this.pourcentageProjet = pourcentageProjet;
	}
}
