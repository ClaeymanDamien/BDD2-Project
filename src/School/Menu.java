package School;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;

public class Menu {
	
	private Scanner sc;
	private int choice; 
	private Student student;
	private Professeur professeur;
	private Admin admin;
	private ManagerDB managerDB;
	private Recherche_Eleves recherche_Eleves;
	private ConnexionJ connexionJ;
	private int fonction;

	public Menu(Scanner scanner) {
		this.sc = scanner;
	}
	
	public Menu(Scanner sc, ManagerDB managerDB, ConnexionJ connexionJ) {
		this.sc = sc;
		this.managerDB = managerDB;
		this.connexionJ = connexionJ;
		this.recherche_Eleves = new Recherche_Eleves(sc, this.connexionJ, managerDB);
	}

	public void login() {
		Authentification authentification = new Authentification(sc, managerDB);
		fonction = authentification.connection();
		
		if(fonction == 1) {
			student = managerDB.selectStudent(authentification.getId());
			student.setManagerDB(managerDB);
			student.setSc(sc);
			clear();
			System.out.println("Bonjour "+student.getCoordonnees().getPrenom()+" "+student.getCoordonnees().getNom() + " (Etudiant)");
		}
		else if (fonction ==  2) {
			professeur = managerDB.selectProfesseur(authentification.getId());
			professeur.setSc(sc);
			professeur.setManagerDB(managerDB);
			clear();
			System.out.println("Bonjour "+professeur.getCoordonnees().getPrenom()+" "+professeur.getCoordonnees().getNom() + " (Professeur)");
		}
		else if (fonction == 3) {
			admin = new Admin(sc, managerDB);
			clear();
			System.out.println("Bonjour vous êtes connecté en tant qu'administrateur");
		}
	}
	
	public void executeMenu() {
		if(fonction == 1) {
			studentMenu();
		}
		else if (fonction == 2) {
			professeurMenu();
		}
		else if (fonction == 3) {
			adminMenu();
		}
	}

	public void studentMenu() {
		do {
			System.out.println("1 - Consultation des notes ");
			System.out.println("2 - Consultation du bulletin");
			System.out.println("3 - Voir vos informations personnelles");
			System.out.println("4 - Liste d'une classe");
			System.out.println("5 - Liste d'une promotion");
			System.out.print("Choix: ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
			
			switch (choice) {
				case 1:
					student.printMarksTranscript();
					break;

				case 2:
					student.generateBulletin();
					break;		
				case 3: 
					System.out.println(student);
					break;
				case 4: 
					recherche_Eleves.searchByClasse();
					break;
				case 5:
					recherche_Eleves.searchByPromo();
					break;
				default:
					System.out.println("Choix invalide");
					break;
			}	
				
			System.out.println("Menu = 0, Déconnection = 1 ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
		}while(choice == 0);
	}
	
	public void professeurMenu() {
		do {
			System.out.println("1 - Saisir les notes d'une épreuve");
			System.out.println("2 - Regarder le relevé de notes d'un élève");
			System.out.println("3 - Regarder le bulletin d'un élève");
			System.out.println("4 - Voir vos informations personnelles");
			System.out.println("5 - Liste d'une classe");
			System.out.println("6 - Liste d'une promotion");
			System.out.print("Choix: ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
			
			switch (choice) {
				case 1:
					professeur.studentsMarks();
					break;
				case 2:
					professeur.seeStudentMarks();
					break;
				case 3:
					professeur.seeStudentBulletin();
					break;
				case 4:
					System.out.println(professeur);
					break;
				case 5: 
					recherche_Eleves.searchByClasse();
					break;
				case 6:
					recherche_Eleves.searchByPromo();
					break;
				default:
					System.out.println("Choix invalide");
					break;
			}	
				
			System.out.println("Menu = 0, Déconnection = 1 ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
		}while(choice == 0);
	}
	
	public void adminMenu() {
		
		do {
			System.out.println("1 - Créer une nouvelle promotion");
			System.out.println("2 - Créer une nouvelle classe");
			System.out.println("3 - Créer un nouvel étudiant");
			System.out.println("4 - Afficher les informations d'un étudiant");
			System.out.println("5 - Modifier un étudiant");
			System.out.println("6 - Ajouter un étudiant à une classe");
			System.out.println("7 - Modifier la note d'un élève");
			System.out.println("8 - Valider les notes d'une promo");
			System.out.println("9 - Créer un nouveau cours");
			System.out.println("10 - Ajouter un cours à une classe");
			System.out.println("11 - Ajouter un nouveau professeur");
			System.out.println("12 - Afficher les informations d'un professeur");
			System.out.println("13 - Liste d'une classe");
			System.out.println("14 - Liste d'une promotion");
			System.out.print("Choix: ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
			
			switch (choice) {
			case 1:
				admin.createNewPromotion();
				break;
			case 2:
				admin.createNewClasse();
				break;
			case 3: 
				admin.createNewStudent();
				break;
			case 4:
				System.out.println(admin.selectStudent());
				break;
			case 5:
				admin.updateStudent();
				break;
			case 6:
				admin.addStudentToClasse();
				break;
			case 7:
				admin.updateNote();
				break;
			case 8:
				admin.validationDesNotes();
				break;
			case 9:
				admin.createNewCours();
				break;
			case 10:
				admin.addCoursToClasse();
				break;
			case 11:
				admin.createNewProfesseur();
				break;
			case 12:
				System.out.println(admin.selectProfesseur());
				break;
			case 13: 
				recherche_Eleves.searchByClasse();
				break;
			case 14:
				recherche_Eleves.searchByPromo();
				break;
			default:
				System.out.println("Choix invalide");
				break;
			}
			System.out.println("Menu = 0, Déconnection = 1 ");
			choice = sc.nextInt();
			sc.nextLine();
			clear();
		} while (choice == 0);
	}
	
	public void clear() {
		try {
			final String os = System.getProperty("os.name");

	        if (os.contains("Windows")) {
	            Runtime.getRuntime().exec("cls");
	        }
	        else {
	            Runtime.getRuntime().exec("clear");
	        }
		} 
		catch(Exception e) {
			  for(int i=0;i<40;i++)
			    System.out.println();
		}
	}
	
	public void setScanner(Scanner scanner) {
		this.sc = scanner;
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public void setManagerDB(ManagerDB managerDB) {
		this.managerDB = managerDB;
	}
}
