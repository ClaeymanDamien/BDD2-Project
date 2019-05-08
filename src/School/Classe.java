package School;

public class Classe {
	private int id;
	private Promotion promotion;
	
	public Classe(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public int getId() {
		return id;
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
	
}
