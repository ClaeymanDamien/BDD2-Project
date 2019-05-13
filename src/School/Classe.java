package School;

public class Classe {
	private int id;
	private Promotion promotion;
	private String idPromotion;
	
	@Override
	public String toString() {
		return "Classe: " + id+"\n";
	}
	public Classe(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	public Classe(int id, Promotion promotion) {
		this.id = id;
		this.promotion = promotion;
	}

	public int getId() {
		return id;
	}
	
	public Classe(int id, String idPromotion) {
		super();
		this.id = id;
		this.idPromotion = idPromotion;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}
	
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public String getIdPromotion() {
		return idPromotion;
	}
	
	public void setIdPromotion(String idPromotion) {
		this.idPromotion = idPromotion;
	}
	
}
