package ManagerBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import School.Coordonnees;
import School.Cours;
import School.Promotion;
import School.Student;
import School.Tuteur;

public class ManagerDB {

	private Statement statement;
	private PreparedStatement preparedStatement;
	private Connection db; 
	private ResultSet resultSet;
	
	public ManagerDB(Connection db) {
		try {
			this.db = db;
			statement = db.createStatement();
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (db != null) {
			db.close();
		}
	}
	/*
	 * StudentManagerDB
	 */
	
	public Boolean insertStudent(Student student) {
		
		int idCoordonnees = insertCoordonnees(student.getCoordonnees());
		int idTuteur = insertTuteur(student.getTuteur());
		
		String insertSQL = "INSERT INTO etudiant"+
				"(idClasse, idCoordonnees, idTuteur) "+
				"VALUES(?, ?, ?)";
		
		try {
			
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, student.getIdClasse());
			preparedStatement.setInt(2, idCoordonnees);
			preparedStatement.setInt(3, idTuteur);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void insertStudentWithoutClasse(Student student) {
		
		int idCoordonnees = insertCoordonnees(student.getCoordonnees());
		
		String insertSQL = "INSERT INTO etudiant"+
				"(idCoordonnees, idTuteur) "+
				"VALUES(?, ?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, idCoordonnees);
			preparedStatement.setInt(2, student.getIdTuteur());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Boolean updateClasseStudent(int idClasse, int idEleve) {
		String updateSQL = "UPDATE etudiant SET idClasse ="+idClasse+" WHERE idEleve ="+idEleve;
		try {
			statement = db.prepareStatement(updateSQL);
			statement.execute(updateSQL);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * CoursManagerDB
	 */
	
	public Boolean insertCours(Cours cours) {
		String insertSQL = "INSERT INTO cours"+
				"(idProfesseur, Nom, Description, Annee, Coefficient, PourcentageDE, PourcentageTP, PourcentageProjet) "+
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, cours.getIdProfesseur());
			preparedStatement.setString(2, cours.getNom());
			preparedStatement.setString(3, cours.getDescription());
			preparedStatement.setString(4, cours.getAnnee());
			preparedStatement.setFloat(5, cours.getCoefficient());
			preparedStatement.setFloat(6, cours.getPourcentageDE());
			preparedStatement.setFloat(7, cours.getPourcentageTP());
			preparedStatement.setFloat(8, cours.getPourcentageProjet());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/*
	 * ClasseManagerDB
	 */
	
	public Boolean insertClasse(Promotion promotion) {
		String insertSQL = "INSERT INTO classe"+
				"(idPromotion) "+
				"VALUES(?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setString(1, promotion.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * PromotionManagerDB
	 */
	
	/*public Boolean ifPromotionExist(String name) {
		resultSet = selectPromotion(name);
		System.out.println(resultSet);
		if(resultSet == null)
			return false;
		else
			return true;
	}
	
	public ResultSet selectPromotion(String id) {
		String selectSQL = "SELECT * FROM promotion WHERE idPromotion ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			return statement.executeQuery(selectSQL);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public void insertPromotion(Promotion promotion) {
		String insertSQL = "INSERT INTO promotion"+
				"(idPromotion) "+
				"VALUES(?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setString(1, promotion.getId());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * CoordonneesManagerDB
	 */
	public int insertCoordonnees(Coordonnees coordonnees) {
		
		String insertSQL = "INSERT INTO coordonnees"+
				"(Nom, Prenom, Adresse, CodePostal, Ville, Tel, Email) "+
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, coordonnees.getNom());
			preparedStatement.setString(2, coordonnees.getPrenom());
			preparedStatement.setString(3, coordonnees.getAdresse());
			preparedStatement.setString(4, coordonnees.getCodePostal());
			preparedStatement.setString(5, coordonnees.getVille());
			preparedStatement.setString(6, coordonnees.getTel());
			preparedStatement.setString(7, coordonnees.getEmail());
			
			preparedStatement.executeUpdate();
			
			//Sert à récupérer le dernier ID rentré
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	/*
	 * TuteurManagerDB
	 */
	
	public int insertTuteur(Tuteur tuteur) {
		int idCoordonnees = insertCoordonnees(tuteur.getCoordonnees());
		
		String insertSQL = "INSERT INTO tuteur"+
				"(idCoordonnees) "+
				"VALUES(?)";
		try {
			preparedStatement = db.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idCoordonnees);
			preparedStatement.executeUpdate();
			
			//Sert à récupérer le dernier ID rentré
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
}
