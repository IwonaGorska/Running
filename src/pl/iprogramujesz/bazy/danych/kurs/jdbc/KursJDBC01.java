package pl.iprogramujesz.bazy.danych.kurs.jdbc;

import java.sql.*;
import java.util.Scanner;

public class KursJDBC01 
{
		Integer choice;
		Double time;
		Double distance;
		Scanner read = new Scanner(System.in); //obiekt do odebrania danych od u¿ytkownika
		
		/*DriverManager.registerDriver(new org.postgresql.Driver());
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
		Statement stmt = conn.createStatement();*/
		
		void NewScore(Statement stmt)
		{
			System.out.print("Podaj czas: ");
			time = read.nextDouble();
			System.out.print("Podaj dystans: ");
			distance = read.nextDouble();
			try {
				//stmt.executeUpdate("INSERT INTO Biegi(Czas, Dystans, SredniaPredkosc, Data) VALUES (time, distance, distance/time, Now())");
				String query = "INSERT INTO Biegi(Czas, Dystans, SredniaPredkosc) VALUES"
						+ "('" + time + "','" + distance + "','" + distance/time + "');";
				stmt.executeUpdate(query);
				
			
			} catch (SQLException e) {
				System.out.println("Blad w NewScore");
				e.printStackTrace();
			}
		}

		void AverageTime(Statement stmt)
		{
			ResultSet rs=null;
			try {
				rs = stmt.executeQuery("SELECT avg(Czas) from Biegi ");
			} catch (SQLException e1) {
				System.out.println("Blad w AverageTime-ExecuteQuery");
				e1.printStackTrace();
			}
			try {
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println(name);
					
					}
			} catch (SQLException e) {
				System.out.println("Blad w AverageTime-SystemOutPrintln");
				e.printStackTrace();
			}
		}
		
		void AverageDistance(Statement stmt)
		{
			ResultSet rs=null;
			try {
				rs = stmt.executeQuery("SELECT avg(Dystans) from Biegi ");
			} catch (SQLException e1) {
				System.out.println("Blad w AverageDistance-ExecuteQuery");
				e1.printStackTrace();
			}
			try {
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println(name);
					
					}
			} catch (SQLException e) {
				System.out.println("Blad w AverageDistance-SystemOutPrintln");
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("AverageDistance-rs.close()");
				e.printStackTrace();
			}
		}
		
		void BestTime(Statement stmt)
		{
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("SELECT min(Czas) from Biegi");
			} catch (SQLException e) {
				System.out.println("Blad w BestTime-ExecuteQuery");
				e.printStackTrace();
			}
			try {
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println(name);
					
					}
			} catch (SQLException e) {
				System.out.println("Blad w BestTime-SystemOutPrintln");
				e.printStackTrace();
			}			
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Blad w BestTime-rs.close()");
				e.printStackTrace();
			}
		}
		
		void BestDistance(Statement stmt) 
		{
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("SELECT max(Dystans) from Biegi");
			} catch (SQLException e) {
				System.out.println("Blad w BestDistance-ExecuteQuery");
				e.printStackTrace();
			}
			try {
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println(name);
					
					}
			} catch (SQLException e) {
				System.out.println("Blad w BestDistance-SystemOutPrintln");
				e.printStackTrace();
			}
			
			
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Blad w BestDistance-rs.close()");
				e.printStackTrace();
			}
		}
	
		void AllScores(Statement stmt) 
		{
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("SELECT * FROM Biegi");
			} catch (SQLException e) {
				System.out.println("Blad w AllScores-ExecuteQuery");
				e.printStackTrace();
			}
			try {
				while(rs.next()) 
				{
					System.out.println(rs.getString("Czas") + " " + rs.getString("Dystans") + " " + rs.getString("SredniaPredkosc") 
					+ " " );
				}
			} catch (SQLException e) {
				System.out.println("Blad w AllScores-rs.next()");
				e.printStackTrace();
			}
			
			
			
			/*try {
				while (rs.next()) {
					String name = rs.getString(1);
					System.out.println(name);
					
					}
			} catch (SQLException e) {
				System.out.println("Blad w BestDistance-SystemOutPrintln");
				e.printStackTrace();
			}*/
			
			
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Blad w AllScores-rs.close()");
				e.printStackTrace();
			}
		}
	

	public static void main(String[] args) 
	{
			
		try {	
			
		    KursJDBC01 One = new KursJDBC01();
		    DriverManager.registerDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
			Statement stmt = conn.createStatement();
		    
			//stmt.executeUpdate("DROP TABLE Biegi");
			//stmt.executeUpdate("CREATE TABLE Biegi (id SERIAL PRIMARY KEY, Czas Real, Dystans Real, SredniaPredkosc Real)");
			
			
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			//Jesli drugi raz wlaczamy program, to juz trzeba zakomentowac ponizszy wers:
			//stmt.executeUpdate("CREATE TABLE Biegi (id SERIAL PRIMARY KEY, Czas Real, Dystans Real, SredniaPredkosc Real,  Data Date)");
			// a  jeœli pierwszy raz, to odkomentuj
			
			
			
			/*stmt.executeUpdate("INSERT INTO programista(imie, nazwisko) VALUES ('Andrzej', 'Kowalski')");
			stmt.executeUpdate("INSERT INTO programista(imie, nazwisko) VALUES ('Jan', 'Nowak')");
			ResultSet rs = stmt.executeQuery("SELECT * FROM programista");
			while(rs.next()) {
				System.out.println(rs.getString("imie") + " " + rs.getString("nazwisko"));
			}*/
			
			do
			{
				//MENU
				System.out.println("Co chcesz zrobic?");
				System.out.println("1- Dodac nowy wynik; 2- Poznac sredni czas, 3- Poznac sredni dystans, "
						+ "4- Poznac najlepszy czas, 5- Poznac najlepszy dystans, 6- Zobaczyc wszystkie wyniki, "
						+ "0-Wyjscie z programu");
				One.choice = One.read.nextInt();
				switch(One.choice)
				{
					case 1: One.NewScore(stmt); break;
					case 2: One.AverageTime(stmt); break;
					case 3: One.AverageDistance(stmt); break;
					case 4: One.BestTime(stmt); break;
					case 5: One.BestDistance(stmt); break;
					case 6: One.AllScores(stmt); break;
				}
			}
			while(One.choice!=0);
						
			//One.rs.close();
			
			
			/*
			One.stmt.close();
			One.conn.close();
			*/
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
