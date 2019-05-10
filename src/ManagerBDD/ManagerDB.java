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
	
	public Boolean updateStudent(Student student) {
		if(!updateClasseStudent(student.getIdClasse(), student.getId()))
			return false;
		if(!updateCoordonnees(student.getCoordonnees()))
			return false;
		if(!updateTuteur(student.getTuteur()))
			return false;
		
		return true;
	}
	
	public Student selectStudent(int id) {
		Student student = null;
		String selectSQL = "SELECT * FROM etudiant WHERE idEleve ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			if(resultSet.next()) {
				student = new Student(
						resultSet.getInt("idEleve"), 
						resultSet.getInt("idCoordonnees"), 
						resultSet.getInt("idClasse"), 
						resultSet.getInt("idTuteur")
						);
				student.setCoordonnees(selectCoordonnees(student.getIdCoordonnees()));
				student.setTuteur(selectTuteur(student.getIdTuteur()));
			}
			
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * CoursManagerDB
	 */
	
	
	public Cours selectCours(int id) {
		Cours cours = null;
		String selectSQL = "SELECT * FROM cours WHERE idCours ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			if(resultSet.next()) {
				cours = new Cours(
						resultSet.getString("Nom"), 
						resultSet.getString("Description"), 
						resultSet.getString("Annee"), 
						resultSet.getDouble("Coefficient"), 
						resultSet.getDouble("PourcentageDE"), 
						resultSet.getDouble("PourcentageTP"),
						resultSet.getDouble("PourcentageProjet")
						);
			}
			
			return cours;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean insertCours(Cours cours) {
		String insertSQL = "INSERT INTO cours"+
				"(Nom, Description, Annee, Coefficient, PourcentageDE, PourcentageTP, PourcentageProjet) "+
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setString(1, cours.getNom());
			preparedStatement.setString(2, cours.getDescription());
			preparedStatement.setString(3, cours.getAnnee());
			preparedStatement.setDouble(4, cours.getCoefficient());
			preparedStatement.setDouble(5, cours.getPourcentageDE());
			preparedStatement.setDouble(6, cours.getPourcentageTP());
			preparedStatement.setDouble(7, cours.getPourcentageProjet());
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
	
	public Boolean insertCoursToClasse(int idClasse, int idCours, int idProfesseur) {
		String insertSQL = "INSERT INTO estdispense"+
				"(idClasse, idCours, idProfesseur) "+
				"VALUES(?, ?, ?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, idClasse);
			preparedStatement.setInt(2, idCours);
			preparedStatement.setInt(3, idProfesseur);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/*
	 * PromotionManagerDB
	 */
	
	
	
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
	
	public boolean updateCoordonnees(Coordonnees coordonnees) {
		String updateSQL = "UPDATE coordonnees SET Nom=?, Prenom=?, Adresse=?, CodePostal=?, Ville=?, Tel=?, Email=? WHERE idCoordonnees=? ";
		try {
			preparedStatement = db.prepareStatement(updateSQL);
			preparedStatement.setString(1, coordonnees.getNom());
			preparedStatement.setString(2, coordonnees.getPrenom());
			preparedStatement.setString(3, coordonnees.getAdresse());
			preparedStatement.setString(4, coordonnees.getCodePostal());
			preparedStatement.setString(5, coordonnees.getVille());
			preparedStatement.setString(6, coordonnees.getTel());
			preparedStatement.setString(7, coordonnees.getEmail());
			preparedStatement.setInt(8, coordonnees.getIdCoordonnees());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Coordonnees selectCoordonnees(int id) {
		Coordonnees coordonnees = null;
		String selectSQL = "SELECT * FROM coordonnees WHERE idCoordonnees ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			if(resultSet.next()) {
				coordonnees = new  Coordonnees(resultSet.getInt("idCoordonnees"), 
						resultSet.getString("Nom"), 
						resultSet.getString("Prenom"), 
						resultSet.getString("Adresse"), 
						resultSet.getString("CodePostal"), 
						resultSet.getString("Ville"), 
						resultSet.getString("Tel"), 
						resultSet.getString("Email"));
			}
			
			return coordonnees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	
	public boolean updateTuteur(Tuteur tuteur) {
		return updateCoordonnees(tuteur.getCoordonnees());
	}
	
	public Tuteur selectTuteur(int id) {
		Tuteur tuteur = null;
		String selectSQL = "SELECT * FROM tuteur WHERE idTuteur ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			if(resultSet.next()) {
				tuteur = new Tuteur(
						resultSet.getInt("idTuteur"), 
						resultSet.getInt("idCoordonnees"));
				tuteur.setCoordonnees(selectCoordonnees(tuteur.getIdCoordonnees()));
			}
			
			return tuteur;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
