package ManagerBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import School.Authentification;
import School.Classe;
import School.Coordonnees;
import School.Cours;
import School.Epreuve;
import School.MyDate;
import School.Note;
import School.Professeur;
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
			preparedStatement = db.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, student.getIdClasse());
			preparedStatement.setInt(2, idCoordonnees);
			preparedStatement.setInt(3, idTuteur);
			preparedStatement.executeUpdate();
			
			//Sert à récupérer le dernier ID rentré
			ResultSet result = preparedStatement.getGeneratedKeys();
			result.next();
			
			insertAuthentification(new Authentification(result.getInt(1), student.getAuthentification().getMdp() , 1));
			
			return true;
			
				
		} catch (Exception e) {
			insertStudentWithoutClasse(student, idCoordonnees, idTuteur);
			return false;
		}
	}
	
	public void insertStudentWithoutClasse(Student student, int idCoordonnees, int idTuteur) {
		
		String insertSQL = "INSERT INTO etudiant"+
				"(idCoordonnees, idTuteur) "+
				"VALUES(?, ?)";
		
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, idCoordonnees);
			preparedStatement.setInt(2, idTuteur);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Echec de l'insertion");
		}
	}
	
	public Boolean updateClasseStudent(int idClasse, int idEleve) {
		String updateSQL = "UPDATE etudiant SET idClasse ="+idClasse+" WHERE idEleve ="+idEleve;
		try {
			statement = db.prepareStatement(updateSQL);
			statement.execute(updateSQL);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void updateStudent(Student student) {
		updateClasseStudent(student.getIdClasse(), student.getId());	
		updateCoordonnees(student.getCoordonnees());
		updateTuteur(student.getTuteur());
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
			System.out.println("L'étudiant n'existe pas");
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
			System.out.println("Le cours n'existe pas");
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
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
	
	/*
	 * EpreuveManagerDB
	 */
	
	public Boolean insertEpreuve(Epreuve epreuve) {
		
		String insertSQL = "INSERT INTO epreuve"+
				"(idEpreuve, idCours, type, jour, mois, annee, etat) "+
				"VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setString(1, epreuve.getIdEpreuve());
			preparedStatement.setInt(2, epreuve.getIdCours());
			preparedStatement.setInt(3, epreuve.getType());
			preparedStatement.setInt(4, epreuve.getDate().getJour());
			preparedStatement.setInt(5, epreuve.getDate().getMois());
			preparedStatement.setInt(6, epreuve.getDate().getAnnee());
			preparedStatement.setInt(7, 0);
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
	
	public Boolean insertNote(Note note) {
		String insertSQL = "INSERT INTO passe"+
				"(idEpreuve, idEleve, Note)"+
				"VALUES(?, ?, ?)";
		
		try {
			
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setString(1, note.getIdEpreuve());
			preparedStatement.setInt(2, note.getIdStudent());
			preparedStatement.setDouble(3, note.getNote());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
	
	public Boolean updateNote(String idEpreuve, int idStudent, double note) {
		String updateSQL = "UPDATE passe SET Note=? WHERE idEpreuve=? AND idEleve=?";
		try {
			preparedStatement = db.prepareStatement(updateSQL);
			preparedStatement.setDouble(1, note);
			preparedStatement.setString(2, idEpreuve);
			preparedStatement.setInt(3, idStudent);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("L'update a échoué");
			return false;
		}
	}
	
	public List<Note> selectStudentNotes(int idStudent) {
		List<Note> notes = new ArrayList<Note>();
		Epreuve epreuve;
		Note note;
		
		String selectSQL = "SELECT * FROM passe WHERE idEleve = ?";
		
		try {
			
			preparedStatement = db.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idStudent);
			ResultSet result = preparedStatement.executeQuery();

			while(result.next()) {
				note= new Note(
						result.getInt("idEleve"), 
						result.getString("idEpreuve"), 
						result.getDouble("Note") 
					);
				note.setEpreuve(epreuve = selectEpreuve(result.getString("idEpreuve")));
				note.setCours(selectCours(epreuve.getIdCours()));
				notes.add(note);
			}
			
			return notes;
		} catch (Exception e) {
			System.out.println("Notes introuvables");
			return null;
		}
	}
	
	public Boolean searchIfMarksEntered(int idStudent, String IdEpreuve) {
		
		String selectSQL = "SELECT Note FROM passe WHERE idEleve = ? AND idEpreuve = ?";
		
		try {
			
			preparedStatement = db.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idStudent);
			preparedStatement.setString(2, IdEpreuve);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) 
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println("Les notes n'ont pas été trouvé");
			return false;
		}

	}
	public List<String> selectEpreuvesPromo(String promo){
		
		List<String> idEpreuves = new ArrayList<String>();
		List<Classe> classes = null;
		String selectSQL = "SELECT idEpreuve FROM estdispense d, epreuve e WHERE d.idClasse = ? AND d.idCours = e.idCours";
		
		classes = selectClasseByPromotion(promo);
		
		try {
			
			for(Classe classe : classes) {
				preparedStatement = db.prepareStatement(selectSQL);
				preparedStatement.setInt(1, classe.getId());
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					idEpreuves.add(resultSet.getString("idEpreuve"));
				}
			}
			
			return idEpreuves;
		} catch (Exception e) {
			System.out.println("La promotion n'existe pas ou il n'y a pas d'épreuves");
			return null;
		}
	}
	public Epreuve selectEpreuve(String id) {
		Epreuve epreuve = null;
	
		String selectSQL = "SELECT * FROM epreuve WHERE IdEpreuve = ?";
		try {
			
			preparedStatement = db.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				epreuve = new Epreuve(resultSet.getString("idEpreuve"), 
						resultSet.getInt("idCours"), 
						resultSet.getInt("type"), 
						new MyDate(
								resultSet.getInt("jour"), 
								resultSet.getInt("mois"), 
								resultSet.getInt("annee")
								),
						resultSet.getInt("etat")
						);
			}
			
			return epreuve;
		} catch (Exception e) {
			System.out.println("Epreuve introuvable");
			return null;
		}
	}
	
	public Boolean updateEtatEpreuve(String idEpreuve, int etat) {
		String updateSQL = "UPDATE epreuve SET etat = ? WHERE IdEpreuve = ?";
		try {
			preparedStatement = db.prepareStatement(updateSQL);
			preparedStatement.setInt(1, etat);
			preparedStatement.setString(2, idEpreuve);
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("L'update a échoué");
			return false;
		}
	}
	
	public List<Note> selectAllNoteByEpreuve(int id){
		List<Note> notes = new ArrayList<Note>();
		Note note = null;
		String selectSQL = "SELECT * FROM notes WHERE idEpreuve ="+ id;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			while(resultSet.next()) {
				note = new Note(
						resultSet.getInt("idEleve"), 
						resultSet.getString("idEpreuve"), 
						resultSet.getDouble("Note"));
				notes.add(note);
			}
			
			return notes;
		} catch (Exception e) {
			System.out.println("Les notes sont introuvables");
			return null;
		}
	}
	
	public Boolean checkIfStudentMarksAreValidated(int idStudent) {
		String selectNoteEleve = "SELECT Note FROM passe WHERE idEleve = ?";
		String selectEpreuveEtat1 = "SELECT etat FROM passe p, epreuve e  WHERE p.idEleve = ? AND e.idEpreuve = p.idEpreuve AND e.etat = ?";
		try {
			preparedStatement = db.prepareStatement(selectNoteEleve);
			preparedStatement.setInt(1, idStudent);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				preparedStatement = db.prepareStatement(selectEpreuveEtat1);
				preparedStatement.setInt(1, idStudent);
				preparedStatement.setInt(2, Epreuve.ETAT_BULLETIN_NON_EDITE);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next())
					return false;
				else 
					return true;
			}
			else {
				return false;
			}
			

		} catch (Exception e) {
			System.out.println("Les notes sont introuvables");
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
			System.out.println("L'insertion a échoué");
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
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
	
	public Integer selectIdProfDispenceCours(int idClasse, int idCours) {
		
		String selectSQL = "SELECT * FROM estdispense WHERE idClasse = ? AND idCours=?";
		try {
			preparedStatement = db.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, idClasse);
			preparedStatement.setInt(2, idCours);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt("idProfesseur");
			}
			
			return -1;
		} catch (Exception e) {
			System.out.println("La sélection a échoué");
			return -1;
		}
	}
	
	public List<Student> selectAllEleveByClasse(int idClasse){
		List<Student> students = new ArrayList<Student>();
		
		String selectSQL = "SELECT * FROM etudiant WHERE idClasse ="+ idClasse;
		try {
			statement = db.prepareStatement(selectSQL);
			ResultSet result = statement.executeQuery(selectSQL);
			
			while(result.next()) {	
				students.add(selectStudent(result.getInt("idEleve")));
			}
			
			return students;
		} catch (Exception e) {
			System.out.println("La sélection a échoué");
			return null;
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
			System.out.println("L'insertion a échoué");
		}
	}
	
	public List<Classe> selectClasseByPromotion(String idPromo){
		
		List<Classe> classes = new ArrayList<Classe>();
		String selectSQL = "SELECT * FROM classe WHERE idPromotion = ?";
		
		try {
			preparedStatement = db.prepareStatement(selectSQL);
			preparedStatement.setString(1, idPromo);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				classes.add(new Classe(
						resultSet.getInt("idCLasse"), 
						resultSet.getString("idPromotion")
						));
			}
			
			return classes;
		} catch (Exception e) {
			System.out.println("La sélection a échoué");
			return null;
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
			System.out.println("L'insertion a échoué");
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
			System.out.println("L'update a échoué");
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
			System.out.println("La sélection a échoué");
			return null;
		}
	}
	
	/*
	 * ProfesseurManagerDB
	 */
	
	public Boolean insertProfesseur(Professeur professeur) {
		int idCoordonnees = insertCoordonnees(professeur.getCoordonnees());
			
		String insertSQL = "INSERT INTO professeur"+
				"(idCoordonnees) "+
				"VALUES(?)";
		try {
			preparedStatement = db.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, idCoordonnees);
			preparedStatement.executeUpdate();
			
			//Sert à récupérer le dernier ID rentré
			ResultSet result = preparedStatement.getGeneratedKeys();
			result.next();
			
			insertAuthentification(new Authentification(result.getInt(1), professeur.getAuthentification().getMdp() , 2));
			return true;
			
		} catch (Exception e) {
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
	
	public Professeur selectProfesseur(int idProfesseur) {
		Professeur professeur = null;
		String selectSQL = "SELECT * FROM professeur WHERE idProfesseur ="+ idProfesseur;
		try {
			statement = db.prepareStatement(selectSQL);
			resultSet = statement.executeQuery(selectSQL);
			
			if(resultSet.next()) {
				professeur = new Professeur(
						resultSet.getInt("idProfesseur"), 
						resultSet.getInt("idCoordonnees"));
				professeur.setCoordonnees(selectCoordonnees(professeur.getIdCoordonnees()));
			}
			
			return professeur;
		} catch (Exception e) {
			System.out.println("La sélection a échoué");
			return null;
		}
	}
	
	public Boolean checkIfTeachingToStudent(int idStudent, int idProfesseur) {
		
		//Ici je veux check si le prof et l'élève on un cours en commun
		int idClasse;
		String selectIdClasse  = "SELECT idClasse FROM etudiant WHERE idEleve = ?";
		String selectIdProf = "SELECT idProfesseur FROM estdispense WHERE idClasse = ? AND idProfesseur = ?";
		
		try {
			preparedStatement = db.prepareStatement(selectIdClasse);
			preparedStatement.setInt(1, idStudent);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				idClasse = resultSet.getInt("idClasse");
				
				preparedStatement = db.prepareStatement(selectIdProf);
				preparedStatement.setInt(1, idClasse);
				preparedStatement.setInt(2, idProfesseur);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next())
					return true;
				else
					return false;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Le check a échoué");
			return false;
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
			System.out.println("L'insertion a échoué");
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
			System.out.println("La sélection a échoué");
			return null;
		}
	}
	
	/*
	 * Authentification
	 */
	public Boolean checkAuthentification(Authentification authentification) {
		
		String SQL = "SELECT * FROM authentification WHERE id=? AND mdp=? AND fonction=?";
		try
        {
			preparedStatement = db.prepareStatement(SQL);
            preparedStatement.setInt(1, authentification.getId());
            preparedStatement.setString(2, authentification.getMdp());
            preparedStatement.setInt(3, authentification.getFonction());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            	return true;
            else 
            	return false;
        }
        catch (Exception e)
        {
        	System.out.println("Le check a échoué");
            return false;
        }
	}
	
	public Boolean insertAuthentification(Authentification authentification) {
		
		String insertSQL = "INSERT INTO authentification"+
				"(id, mdp, fonction) "+
				"VALUES(?,?,?)";
		try {
			preparedStatement = db.prepareStatement(insertSQL);
			preparedStatement.setInt(1, authentification.getId());
			preparedStatement.setString(2, authentification.getMdp());
			preparedStatement.setInt(3, authentification.getFonction());
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("L'insertion a échoué");
			return false;
		}
	}
}
