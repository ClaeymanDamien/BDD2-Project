#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

#------------------------------------------------------------
# Table: Coordonnees
#------------------------------------------------------------

CREATE TABLE Coordonnees(
        idCoordonnees Int  Auto_increment  NOT NULL ,
        Nom          Varchar (20) NOT NULL ,
        Prenom       Varchar (20) NOT NULL ,
        Adresse      Varchar (255) NOT NULL ,
        CodePostal   Varchar (20) NOT NULL ,
        Ville        Varchar (20) NOT NULL ,
        Tel          Varchar (255) NOT NULL ,
        Email        Varchar (255) NOT NULL

        ,CONSTRAINT Coordonnees_PK PRIMARY KEY (idCoordonnees)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Professeur
#------------------------------------------------------------

CREATE TABLE Professeur(
        idProfesseur Int  Auto_increment  NOT NULL ,
        idCoordonnees Int NOT NULL

	,CONSTRAINT Professeur_PK PRIMARY KEY (idProfesseur)
        ,CONSTRAINT Professeur_Coordonnees0_FK FOREIGN KEY (idCoordonnees) REFERENCES Coordonnees(idCoordonnees)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Cours
#------------------------------------------------------------

CREATE TABLE Cours(
        idCours           Int  Auto_increment  NOT NULL ,
        Nom               Varchar (20) NOT NULL ,
        Description       Varchar (255) NOT NULL ,
        Annee             Varchar (13) NOT NULL ,
        Coefficient       Double NOT NULL ,
        PourcentageDE     Double NOT NULL ,
        PourcentageTP     Double NOT NULL ,
        PourcentageProjet Double NOT NULL
	,CONSTRAINT Cours_PK PRIMARY KEY (idCours)

)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Promotion
#------------------------------------------------------------

CREATE TABLE Promotion(
        idPromotion Varchar (20) NOT NULL
	,CONSTRAINT Promotion_PK PRIMARY KEY (idPromotion)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Classe
#------------------------------------------------------------

CREATE TABLE Classe(
        idCLasse   Int  Auto_increment  NOT NULL ,
        idPromotion Varchar (20) NOT NULL
	,CONSTRAINT Classe_PK PRIMARY KEY (idClasse)

	,CONSTRAINT Classe_Promotion_FK FOREIGN KEY (idPromotion) REFERENCES Promotion(idPromotion)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Epreuve
#------------------------------------------------------------

CREATE TABLE Epreuve(
        IdEpreuve Varchar (50) NOT NULL ,
        idCours Int NOT NULL,
        type Int NOT NULL,
        jour Int NOT NULL,
        mois Int NOT NULL,
        annee Int NOT NULL,
        etat Int NOT NULL
	,CONSTRAINT Epreuve_PK PRIMARY KEY (IdEpreuve)
        ,CONSTRAINT Epreuve_Cours_FK FOREIGN KEY (idCours) REFERENCES Cours(idCours)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Tuteur
#------------------------------------------------------------

CREATE TABLE Tuteur(
        IdTuteur   Int  Auto_increment  NOT NULL ,
        idCoordonnees Int NOT NULL

	,CONSTRAINT Tuteur_PK PRIMARY KEY (IdTuteur)
        ,CONSTRAINT Tuteur_Coordonnees0_FK FOREIGN KEY (idCoordonnees) REFERENCES Coordonnees(idCoordonnees)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Etudiant
#------------------------------------------------------------

CREATE TABLE Etudiant(
        idEleve    Int  Auto_increment  NOT NULL ,
        idClasse   Int ,
        idCoordonnees   Int NOT NULL ,
        IdTuteur   Int NOT NULL
	,CONSTRAINT Etudiant_PK PRIMARY KEY (idEleve)

	,CONSTRAINT Etudiant_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
	,CONSTRAINT Etudiant_Tuteur0_FK FOREIGN KEY (IdTuteur) REFERENCES Tuteur(IdTuteur)
        ,CONSTRAINT Etudiant_Coordonnees1_FK FOREIGN KEY (idCoordonnees) REFERENCES Coordonnees(idCoordonnees)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: estDispense
#------------------------------------------------------------

CREATE TABLE estDispense(
        idClasse Int NOT NULL ,
        idCours  Int NOT NULL,
        idProfesseur Int NOT NULL
	,CONSTRAINT estDispense_PK PRIMARY KEY (idClasse,idCours)

	,CONSTRAINT estDispense_Classe_FK FOREIGN KEY (idClasse) REFERENCES Classe(idClasse)
	,CONSTRAINT estDispense_Cours0_FK FOREIGN KEY (idCours) REFERENCES Cours(idCours)
        ,CONSTRAINT Cours_Professeur1_FK FOREIGN KEY (idProfesseur) REFERENCES Professeur(idProfesseur)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Passe
#------------------------------------------------------------

CREATE TABLE Passe(
        IdEpreuve Varchar (50) NOT NULL ,
        idEleve   Int NOT NULL ,
        Note      Double NOT NULL
	,CONSTRAINT Passe_PK PRIMARY KEY (IdEpreuve,idEleve)

	,CONSTRAINT Passe_Epreuve_FK FOREIGN KEY (IdEpreuve) REFERENCES Epreuve(IdEpreuve)
	,CONSTRAINT Passe_Etudiant0_FK FOREIGN KEY (idEleve) REFERENCES Etudiant(idEleve)
)ENGINE=InnoDB;

#------------------------------------------------------------
# Table: Authentification
#------------------------------------------------------------

CREATE TABLE Authentification(
        id     Varchar (50) NOT NULL ,
        mdp       Varchar (20) NOT NULL ,
        fonction  int NOT NULL
    ,CONSTRAINT Authentification_PK PRIMARY KEY (id, fonction)
)ENGINE=InnoDB;