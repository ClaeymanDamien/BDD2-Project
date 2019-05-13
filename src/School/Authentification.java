package School;

import ManagerBDD.ManagerDB;


import java.util.Scanner;


public class Authentification {

	private Scanner sc;
	private ManagerDB managerDB;
	
	private String mdp;
	private int id;
	private int fonction;
	
	
	public Authentification()
    {
      
    }
	
	public Authentification(Scanner sc) {
		this.sc = sc;
	}

	
	public Authentification(Scanner sc, ManagerDB managerDB) {
		this.sc = sc;
		this.managerDB = managerDB;
	}

	public Authentification(int id, String mdp, int fonction) {
		this.mdp = mdp;
		this.id = id;
		this.fonction = fonction;
	}


	public int connection() {
		int retest;
		do {
			//Affichage
	        form();
	   
	        if(managerDB.checkAuthentification(this)) {
	        	return fonction;
	        }
	        else if(mdp.equals(Admin.ADMIN_MDP) && id == Admin.ADMIN_ID && fonction == 3 ) {
				return 3;
			}
	        else {
				System.out.println("Mot de passe où identifiant incorrect");
				System.out.println("\nRéessayer? Press 1 sinon 2");
				retest = sc.nextInt();
				sc.nextLine();
			}
		} while(retest == 1);
 
        return -1;
	}
	
	public void form() {
		System.out.println("Entrer votre identifiant (id): ");
        id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter votre mot de passe: ");
        mdp = sc.nextLine();
        System.out.println("Vous êtes: \n1 = Etudiant\n2 = Professeur\n3 = Administration\n");
        fonction = sc.nextInt();
        sc.nextLine();
	}
	
	public void askMdp() {
		System.out.println("Entrer le mot de passe du compte: ");
		mdp = sc.nextLine();
	}
    

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFonction() {
		return fonction;
	}

	public void setFonction(int fonction) {
		this.fonction = fonction;
	}
	
	public void setManagerDB(ManagerDB managerDB) {
		this.managerDB = managerDB;
	}
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
}