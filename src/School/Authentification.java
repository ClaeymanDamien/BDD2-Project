package School;

import ManagerBDD.ConnexionJ;
import java.sql.*;
import java.util.Scanner;

public class Authentification {

    public void authentification()
    {
        Scanner sc = new Scanner(System.in);

        ConnexionJ b = new ConnexionJ();

        String bdd_email = "";
        String bdd_mdp = "";

        //Affichage
        System.out.println("Entrer votre identifiant(email): ");
        String email = sc.next();
        System.out.println("Enter votre mot de passe: ");
        String mdp = sc.next();

        try
        {
            //Requête SQL
            Statement stmt = b.getDb().createStatement();
            String SQL = "SELECT * FROM authentification WHERE email='" + email + "' && mdp='" + mdp+ "'";

            ResultSet rs = stmt.executeQuery(SQL);

            //Vérification des informations saisies
            while (rs.next()) {
                bdd_email = rs.getString("email");
                bdd_mdp = rs.getString("mdp");
            }

            if (email.equals(bdd_email) && mdp.equals(bdd_mdp)) {
                System.out.println("Vous êtes à présent connecté(e)!\n----");
            } else {
                System.out.println("Mot de passe ou identifiant incorrect.\n----");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur de connexion.");
        }

    }
}