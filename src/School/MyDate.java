package School;

public class MyDate {
	
    private int jour;
    private int mois;
    private int annee;

    public MyDate(int pJour, int pMois, int Pannee)
    {
        jour = pJour;
        mois = pMois;
        annee = Pannee;
    }
    

    public void afficher_date()
    {
        System.out.println(this.jour+"/"+this.mois+"/"+this.annee);
    }
    
    @Override
    public String toString() {
    	return this.jour+"/"+this.mois+"/"+this.annee;
    }
    
    public int getAnnee() {
		return annee;
	}
    
    public int getJour() {
		return jour;
	}
    
    public int getMois() {
		return mois;
	}
    
    public void setAnnee(int annee) {
		this.annee = annee;
	}
    
    public void setJour(int jour) {
		this.jour = jour;
	}
    
    public void setMois(int mois) {
		this.mois = mois;
	}
}
