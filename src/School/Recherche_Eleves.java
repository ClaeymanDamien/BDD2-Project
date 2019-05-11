package School;

import ManagerBDD.ConnexionJ;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Recherche_Eleves {

    int id_classe;
    int id_promo;

    public Recherche_Eleves(int pId_classe, int pId_promo)
    {
        id_classe = pId_classe;
        id_promo = pId_classe;
    }

    public void recherche_eleves_classe()
    {
        ArrayList<String> liste_id_eleve_classe = new ArrayList<String>();

        ConnexionJ b = new ConnexionJ();

        try
        {
            Statement stmt = b.getDb().createStatement();

            String SQL = "SELECT * FROM etudiant WHERE idClasse='"+ id_classe +"'";

            ResultSet rs = stmt.executeQuery(SQL);

            //Ajout des id des eleves appartenant à la classe séléctionée à la liste
            while(rs.next()){
                liste_id_eleve_classe.add(rs.getObject(1).toString());
            }

            System.out.print("Liste contenant les ID des élèves de la classe "+id_classe+" : ");
            System.out.println(liste_id_eleve_classe);
        }

        catch (Exception e)
        {
            System.out.println("Erreur de connexion.");
        }
    }

    public void recherche_eleves_promotion()
    {
        ArrayList<String> liste_id_eleve_promo = new ArrayList<String>();
        ArrayList<String> liste_id_classe = new ArrayList<String>();

        ConnexionJ b = new ConnexionJ();

        try
        {
            Statement stmt = b.getDb().createStatement();

            String SQL = "SELECT * FROM classe WHERE idPromotion='"+ id_promo +"'";

            ResultSet rs = stmt.executeQuery(SQL);

            //Ajout des id des classes appartenant à la promotion séléctionée à la liste
            while(rs.next()){
                liste_id_classe.add(rs.getObject(1).toString());
            }

            //Ajout de l'id des eleves appartenant à la promotion
            for(int i =0; i<liste_id_classe.size(); i ++)
            {
                String SQL2 = "SELECT * FROM etudiant WHERE idClasse='"+ liste_id_classe.get(i) +"'";
                ResultSet rs2 = stmt.executeQuery(SQL2);

                while(rs2.next()){
                    liste_id_eleve_promo.add(rs2.getObject(1).toString());
                }
            }

            System.out.print("Liste contenant les ID des élèves de la promotion "+id_promo+" : ");
            System.out.println(liste_id_eleve_promo);
        }

        catch (Exception e)
        {
            System.out.println("Erreur de connexion.");
        }
    }
}