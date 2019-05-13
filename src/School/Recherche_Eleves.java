package School;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Recherche_Eleves {

    private int id_classe;
    private String id_promo;
    private Scanner sc;
    private ManagerDB managerDB;
    ConnexionJ db;

    public Recherche_Eleves(Scanner sc, ConnexionJ db, ManagerDB managerDB)
    {
        this.sc = sc;
        this.db = db;
        this.managerDB = managerDB;
    }

    public void searchByPromo() {
    	System.out.println("Nom de la promo: ");
    	id_promo = sc.nextLine();
    	System.out.println("\n\n");
    	
    	recherche_eleves_promotion();	
    }
    
    public void searchByClasse() {
    	System.out.println("Nom de la classe: ");
    	id_classe = sc.nextInt();
    	sc.nextLine();
    	System.out.println("\n\n");
    	
    	recherche_eleves_classe();
    }
    
    public void recherche_eleves_classe()
    {
        ArrayList<Student> liste_id_eleve_classe = new ArrayList<Student>();
        Boolean find = false;
        db = new ConnexionJ();

        try
        {
            Statement stmt = db.getDb().createStatement();

            String SQL = "SELECT idEleve FROM etudiant WHERE idClasse='"+ id_classe +"'";

            ResultSet rs = stmt.executeQuery(SQL);

            //Ajout des id des eleves appartenant à la classe séléctionée à la liste
            while(rs.next()){
                liste_id_eleve_classe.add(managerDB.selectStudent(rs.getInt("idEleve")));
                find = true;
            }

            if(find) {
            	System.out.println("Liste contenant les ID des élèves de la classe "+id_classe+" : \n");
                
                System.out.println("Id: \t\tNom: \t\tPrénom:\t\t");
                for(Student student : liste_id_eleve_classe) {
                	System.out.println(student.getId() + "\t\t"+student.getCoordonnees().getNom()+"\t\t"+student.getCoordonnees().getPrenom());
                }
            }
            else {
				System.out.println("Il n'y a pas d'élève dans cette classe");
			}
            
        }

        catch (Exception e)
        {
            System.out.println("Erreur de connexion.");
        }
    }

    public void recherche_eleves_promotion()
    {
        ArrayList<Student> liste_eleve_promo = new ArrayList<Student>();
        ArrayList<String> liste_id_classe = new ArrayList<String>();
        Boolean find = false;
        
        db = new ConnexionJ();

        try
        {
            Statement stmt = db.getDb().createStatement();

            String SQL = "SELECT * FROM classe WHERE idPromotion='"+ id_promo +"'";

            ResultSet rs = stmt.executeQuery(SQL);

            //Ajout des id des classes appartenant à la promotion séléctionée à la liste
            while(rs.next()){
                liste_id_classe.add(rs.getObject(1).toString());
                find = true;
            }

            if(find) {
            	find = false;
            	//Ajout de l'id des eleves appartenant à la promotion
                for(int i =0; i<liste_id_classe.size(); i ++)
                {
                    String SQL2 = "SELECT * FROM etudiant WHERE idClasse='"+ liste_id_classe.get(i) +"'";
                    ResultSet rs2 = stmt.executeQuery(SQL2);

                    while(rs2.next()){
                        liste_eleve_promo.add(managerDB.selectStudent(rs2.getInt("idEleve")));
                    }
                    find = true;
                }
                if(find) {
                	System.out.println("Liste contenant les ID des élèves de la promotion "+id_promo+" : \n");
                    System.out.println("Id: \t\tNom: \t\tPrénom:\t\t");
                    for(Student student : liste_eleve_promo) {
                    	System.out.println(student.getId() + "\t\t"+student.getCoordonnees().getNom()+"\t\t"+student.getCoordonnees().getPrenom());
                    }
                }
                else {
					System.out.println("Il n'y a pas d'élève dans cette promotion");
				}
                
            }
            else {
				System.out.println("La promotion n'existe pas où ne contient pas de classe");
			}
            
        }

        catch (Exception e)
        {
            System.out.println("Erreur de connexion.");
        }
    }
}