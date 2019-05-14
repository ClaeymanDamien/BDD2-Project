package test;

import java.util.Scanner;

import ManagerBDD.ConnexionJ;
import ManagerBDD.ManagerDB;


import School.*;


public class Main {
	public static void main(String[] args) {
		
		ConnexionJ connexionJ = new ConnexionJ();
		ManagerDB managerDB = new ManagerDB(connexionJ.getDb());
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu(sc, managerDB, connexionJ);
		int end;

		do {
			menu.login();
			menu.executeMenu();
			System.out.println("Connection: 1 / Quitter le programme: 2");
			end = sc.nextInt();
			sc.nextLine();
		} while(end == 1);
		
		System.out.println("Au revoir !");
		sc.close();
	}
}
