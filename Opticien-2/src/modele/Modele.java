package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Lunette;
import controleur.Magasin;
import controleur.Monture;
import controleur.Rdv;
import controleur.Reservation;
import controleur.TableauBord;
import controleur.TableauCli;
import controleur.TableauRdv;
import controleur.TableauReserv;
import controleur.TableauTech;
//import controleur.TableauTech;
//import controleur.TableauBord;
//import controleur.Technicien;
//import controleur.Client;
import controleur.Technicien;
import controleur.User;
import controleur.Verre;

public class Modele {
	private static Bdd uneBdd = new Bdd("localhost:8889", "opticien_2022_event", "root", "root");
	
	
	
	//********************************GESTION USER*********************************
	
	public static User selectWhereUser(String email, String mdp) {
		
		String requete = "select * from user where email = '"+email+"' and mdp = '"+mdp+"' ;"; 
		User unUser = null;
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			
			while(unResultat.next())
			{
				unUser = new User(
						unResultat.getInt("iduser"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("typerole")


						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return unUser;
	}
	
	//********************************GESTION DES PERSONNES***********************
	
	
	

	//*******************************GESTION CLIENT********************************
	
	
	public static void insertionClient(Client unClient)
	{
		String requete = "call insertClient values('"+ unClient.getNom()+ "','" + unClient.getPrenom()
		+ "','" + unClient.getAdresse() + "','" + unClient.getEmail() 
		+ "','" + unClient.getAge() + "','" + unClient.getTelephone() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static ArrayList<Client> selectAllClients(String mot)
	{
		String requete = "";
		if(mot.equals(""))
		{
			requete = "select * from personneClient";
		}
		else
		{
			requete = "select * from personneClient where nom like '%" +mot+ "%' or telephone like '%"+mot+"%'";
		}
		
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Client unClient = new Client(
						desResultats.getInt("idPersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("age"),
						desResultats.getString("telephone")
						);
				lesClients.add(unClient);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesClients;
	}
	
	
	public static void deleteClient(int idpersonne) 
	{
		String requete = "delete from Client where idclient = "+ idpersonne;
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete "+ requete);
		}
		
		
	}
	
	public static Client selectWhereClient(String nom, String telephone)
	{
		String requete = "select * from personneClient where nom = '" +nom+ "' and telephone = '" +telephone+ "' ";
		Client unClient = null;
		
		try{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				unClient = new Client(
						desResultats.getInt("idPersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("age"),
						desResultats.getString("telephone")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return unClient;
	}
	
	public static void updateClient(Client unClient)
	{
		String requete = "update Client set nom = '"+ unClient.getNom() +"', prenom = '"+ unClient.getNom() 
		+ "', adresse = '"+unClient.getAdresse()+"', email = '"+ unClient.getEmail()
		+ "', age= '"+ unClient.getAge() +"', telephone = '"+ unClient.getTelephone() 
		+ "', where idpersonne =  '"+unClient.getIdpersonne()+"';";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
	}
	
	public static int countClient()
	{
		int nb=0;
		String requete = "select count(*) as nb from Client";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt("nb");
			}
			
			//unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		return nb;
	}
	
	
	//*******************************GESTION TECHNICIEN********************************
	
	public static void insertTechnicien(Technicien unTechnicien) {
		String requete = "call insertTech('"+ unTechnicien.getNom()+ "','" + unTechnicien.getPrenom()
		+ "','" + unTechnicien.getAdresse() + "','" + unTechnicien.getEmail() 
		+ "','" + unTechnicien.getDiplome() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
	}
	
	public static ArrayList<Technicien> selectAllTechniciens(String mot){
		String requete = "";
		if(mot.equals(""))
		{
			requete = "select * from personneTech";
		}
		else
		{
			requete = "select * from personneTech where nom like '%" +mot+ "%' or prenom like '%"+mot+ "%'";
		}
		
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Technicien unTechnicien = new Technicien(
						desResultats.getInt("idpersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("diplome")

						
						);
				lesTechniciens.add(unTechnicien);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesTechniciens;
		
		
	}
	
	public static void deleteTechnicien(int idpersonne)
	{
		String requete = "delete from Technicien where idPersonne =  "+ idpersonne;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	public static Technicien selectWhereTechnicien(String email, String diplome)
	{
		String requete = "select * from personneTech where email = '"+email+"' and diplome = '"+diplome+"';";
		Technicien unTechnicien = null;// on initialise unTechnicien tt simplement
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			if(desResultats.next())
			{
					unTechnicien = new Technicien(// on instancie un Tech
						desResultats.getInt("idPersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("diplome")

						
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return unTechnicien;
	}
	
	public static void updateTechnicien(Technicien unTechnicien)
	{
		String requete = "update Technicien set nom =  '" + unTechnicien.getNom()
		+ "', prenom = '" + unTechnicien.getPrenom() + "', email = '"+ unTechnicien.getEmail() 
		+ "' , adresse = '"+ unTechnicien.getAdresse()
		+ "' , diplome = '"+ unTechnicien.getDiplome() +"' where idpersonne = " + unTechnicien.getIdpersonne();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			unStat.execute(requete); // execute comme php
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}

	}
	
	public static int countTechnicien()
	{
		int nb=0;
		String requete = "select count(*) as nb from Technicien";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt("nb");
			}
			
			//unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		return nb;
	}
	
	//*******************************GESTION MAGASIN********************************
	
	public static void insertMagasin(Magasin unMagasin)
	{
		String requete = "insert into Magasin values(null, '" + unMagasin.getNom() + "','" + unMagasin.getAdresse() 
		+ "','" + unMagasin.getEmail() + "','" + unMagasin.getTelephone() + "' );";
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
	}
	
	public static ArrayList<Magasin> selectAllMagasins(String mot)
	{
		String requete = "";
		if(mot.equals(""))
		{
			requete = "select * from Magasin";
		}
		else
		{
			requete = "select * from Magasin where nom like '%"+mot+"%' or telephone like '%"+mot+"%';";
		}
		
		ArrayList<Magasin> lesMagasins = new ArrayList<Magasin>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
		
			ResultSet desResultats = unStat.executeQuery(requete);
			while(desResultats.next())
			{
				Magasin unMagasin = new Magasin(
						desResultats.getInt("idmagasin"),
						desResultats.getString("nom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("telephone")
						);
				lesMagasins.add(unMagasin);
			}
			unStat.close();
			uneBdd.seDeconnecter();

		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return lesMagasins;
		
	}
	
	public static void deleteMagasin(int idmagasin)
	{
		String requete = "delete from Magasin where idmagasin = "+idmagasin;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.executeQuery(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
	}
	
	public static Magasin selectWhereMagasin(String nom, String telephone)
	{
		String requete = "select * from Magasin where nom = '"+nom+"' and telephone = '"+telephone+"' ;" ;
		
		Magasin unMagasin = null;
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			while(desResultats.next())
			{
				unMagasin = new Magasin(
						desResultats.getInt("idmagasin"),
						desResultats.getString("nom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("telephone")
						);
				
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return unMagasin;
		
	}
	
	public static void updateMagasin(Magasin unMagasin)
	{
		String requete = "update Magasin set motif = '" + unMagasin.getNom() + "', adresse = '"+ unMagasin.getAdresse()
		+ "', email = '"+ unMagasin.getEmail()+ "', telephone = '" + unMagasin.getTelephone() 
		+ "' where idmagasin = '" + unMagasin.getIdmagasin() + "'; ";
		
		try
		{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaConnexion().createStatement();
		unStat.executeQuery(requete);
		unStat.close();
		uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
	}
	
	public static int countMagasin()
	{
		int nb = 0;
		String requete = "select count(*) as nb from Magasin;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
	}
	
	
	
	
	
	//*******************************GESTION VERRE********************************
	
	public static void insertVerre(Verre unVerre)
	{
		String requete = "insert into Verre values (null,'" + unVerre.getVision()
		+ "','" + unVerre.getCouleurV() + "','" + unVerre.getMatiereV() + "','" + unVerre.getLaboratoire() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static ArrayList<Verre> selectAllVerres(String mot)
	{
		String requete = "";
		if(mot.equals(""))
		{
			requete = "select * from Verre";
		}
		else
		{
			requete = "select * from Verre where vision like '%" +mot+ "%' or laboratoire like '%"+mot+"%'";
		}
		
		ArrayList<Verre> lesVerres = new ArrayList<Verre>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Verre unVerre = new Verre(
						desResultats.getInt("idverre"),
						desResultats.getString("vision"),
						desResultats.getString("couleurV"),
						desResultats.getString("matiereV"),
						desResultats.getString("laboratoire")

						);
				lesVerres.add(unVerre);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesVerres;
	}
	
	public static void deleteVerre(int idverre)
	{
		String requete = "delete from Verre where idverre =  "+ idverre;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	public static Verre selectWhereVerre(String vision, String matiereV)
	{
		String requete = "select * from Verre where vision = '"+vision+"' and matiereV = '"+matiereV+"' ;" ;
		
		Verre unVerre = null;
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			while(desResultats.next())
			{
				unVerre = new Verre(
						desResultats.getInt("idverre"),
						desResultats.getString("vision"),
						desResultats.getString("couleurV"),
						desResultats.getString("matiereV"),
						desResultats.getString("laboratoire")
						);
				
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return unVerre;
	}
	
	public static void updateVerre(Verre unVerre)
	{
		String requete = "update Verre set vison = '"+unVerre.getVision()+ "', couleurV = '"+unVerre.getCouleurV()
		+ "', matiereV = '"+unVerre+"' , laboratoire = '"+ unVerre.getLaboratoire()
		+"', where idverre = '"+unVerre.getIdverre()+"';";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static int countVerre()
	{
		int nb = 0;
		String requete = "select count(*) as nb from Verre ;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
	}
	
	
	//*******************************GESTION MONTURE********************************
	
	public static void insertMonture(Monture uneMonture)
	{
		String requete = "insert into Monture values(null, '"+uneMonture.getForme()+"', '"+uneMonture.getMatiereM()+"');";
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
	}
	
	//on va envoyer une liste qui utilise des data de type srt
	public static ArrayList<Monture> selectAllMontures(String mot)
	{
		String requete = "";
		ArrayList<Monture> lesMontures = new ArrayList<Monture>();

		if(requete.equals(""))
		{
			requete ="select * from Monture";
		}
		
		else
		{
			requete = "select * from Monture where forme like  '%" +mot+ "%' or matiereM like '%"+mot+"%'";
		}


		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())
			{
				Monture uneMonture = new Monture(
						desResultats.getInt("idmonture"),
						desResultats.getString("forme"),
						desResultats.getString("matiereM")
						);
				lesMontures.add(uneMonture);
			}
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return lesMontures;	
	}
	
	public static ArrayList<Monture> selectAllMontures()
	{
		String requete = "";
		ArrayList<Monture> lesMontures = new ArrayList<Monture>();

		if(requete.equals(""))
		{
			requete ="select * from Monture";
		}

		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())
			{
				Monture uneMonture = new Monture(
						desResultats.getInt("idmonture"),
						desResultats.getString("forme"),
						desResultats.getString("matiereM")
						);
				lesMontures.add(uneMonture);
			}
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return lesMontures;	
	}
	
	public static void deleteMonture(int idmonture)
	{
		String requete = "delete from Monture where idmonture = "+idmonture;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
	}
	
	
	public static Monture selectWhereMonture(String forme, String matiereM)
	{
		String requete = "select * from Monture where forme = '"+forme+"' and matiereM = '"+matiereM+"'; ";
		Monture uneMonture = null;
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				uneMonture = new Monture(
						desResultats.getInt("idmonture"),
						desResultats.getString("forme"),
						desResultats.getString("matiereM")
						
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);

		}
		
		return uneMonture;
	}
	
	public static void updateMonture(Monture uneMonture)
	{
		String requete = "update Monture set '"+ uneMonture.getForme()+"', '"+uneMonture.getMatiereM()
		+ "' where idmonture = '"+uneMonture.getIdmonture();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
	}
	
	public static int countMonture()
	{
		int nb = 0;
		String requete = "select count(*) as nb from Monture ;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
	}
	
	//*******************************GESTION LUNETTE********************************
	
	public static void insertLunette(Lunette uneLunette)
	{
		String requete = "insert into Lunette values(null, '"+uneLunette.getLibelle()+"', '"+uneLunette.getCouleur()
		+"', '"+uneLunette.getGenre()+"' , '"+uneLunette.getMarque()+"', '"+ uneLunette.getPrix()
		+"', '"+uneLunette.getQuantite()+"', '"+uneLunette.getDisponibilite()+"', '"+uneLunette.getIdverre()
		+"', '"+uneLunette.getIdmonture()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
	}
	
	public static ArrayList<Lunette> selectAllLunettes()
	{
		String requete = "select * from Lunette;";
		ArrayList<Lunette> desLunettes = new ArrayList<Lunette>();
	
		try {
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Lunette uneLunette = new Lunette(
				
				desResultats.getInt("idlunette"),				
				desResultats.getString("libelle"),
				desResultats.getString("couleur"),
				desResultats.getString("genre"),
				desResultats.getString("marque"),
				desResultats.getInt("prix"),
				desResultats.getInt("quantite"),
				desResultats.getString("disponibilite"),
				desResultats.getInt("idverre"),
				desResultats.getInt("idmonture")
				
				);
				
				desLunettes.add(uneLunette);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		
		return desLunettes;
		
	}
	
	
	public static void deleteLunette(int idlunette)
	{
		String requete = "delete from Lunette  where idlunette =  "+ idlunette;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	public static Lunette selectWhereLunette(String libelle, String genre)
	{
		Lunette uneLunette = null;
		String requete = "select * from Lunette where libelle = '"+libelle+"' and genre = '"+genre+"';";
		
		try {
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				uneLunette = new Lunette(
				
				desResultats.getInt("idlunette"),				
				desResultats.getString("libelle"),
				desResultats.getString("couleur"),
				desResultats.getString("genre"),
				desResultats.getString("marque"),
				desResultats.getInt("prix"),
				desResultats.getInt("quantite"),
				desResultats.getString("disponibilite"),
				desResultats.getInt("idverre"),
				desResultats.getInt("idmonture")
				
				);
				
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return uneLunette;
	}
	
	
	
	public static void updateLunette(Lunette uneLunette)
	{
		String requete = " update Lunette set libelle = '"+uneLunette.getLibelle()+"', couleur = '"+uneLunette.getCouleur()
		+"', genre = '"+uneLunette.getGenre()+"', marque = '"+uneLunette.getMarque()+"', prix = '"+uneLunette.getPrix()
		+"', quantite = '"+uneLunette.getQuantite()+"', disponibilite = '"+uneLunette.getDisponibilite()
		+"', idverre = '"+uneLunette.getIdverre()+"', idmonture = '"+uneLunette.getIdmonture()
		+"'  where idlunette = '"+uneLunette.getIdlunette();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
	}
	
	public static int countLunette() {
		int nb = 0;
		String requete = "select count(*) as nb from Lunette ;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
		
	}
	
	//*******************************GESTION RDV********************************
	
	public static void insertRdv(Rdv unRdv)
	{
		String requete = "insert into RDV values (null,'"+ unRdv.getIdrdv()+ "','" + unRdv.getMotif()
		+ "','" + unRdv.getDateRdv() + "','" + unRdv.getHeuredebut() + "','" + unRdv.getHeurefin() 
		+ "','" + unRdv.getIdpersonne() + "','" + unRdv.getIdmagasin() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static ArrayList<Rdv> selectAllRdvs()
	{
		String requete = "select * from RDV;";
		
		ArrayList<Rdv> lesRdvs = new ArrayList<Rdv>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Rdv unRdv = new Rdv(
						desResultats.getInt("idRdv"),
						desResultats.getString("motif"),
						desResultats.getString("dateRdv"),
						desResultats.getString("heuredebut"),
						desResultats.getString("heurefin"),
						desResultats.getInt("idpersonne"),
						desResultats.getInt("idmagasin")

						);
				lesRdvs.add(unRdv);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesRdvs;
	}
	
	public static void deleteRdv(int idrdv)
	{
		String requete = "delete from RDV  where idrdv =  "+ idrdv;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	public static Rdv selectWhereRdv(String motif, String dateRdv)
	{
		String requete = "select * from RDV  where motif = '"+motif+"' and dateRdv = '"+dateRdv+"';";
		Rdv unRdv = null;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
					unRdv = new Rdv(
						desResultats.getInt("idrdv"),
						desResultats.getString("motif"),
						desResultats.getString("dateRdv"),
						desResultats.getString("heuredebut"),
						desResultats.getString("heurefin"),
						desResultats.getInt("idpersonne"),
						desResultats.getInt("idmagasin")

						
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return unRdv;
	}
	
	public static void updateRdv(Rdv unRdv)
	{
		String requete = "update RDV  set motif =  '" + unRdv.getMotif()
		+ "', dateRdv = '" + unRdv.getDateRdv() + "', heuredebut = '"+ unRdv.getHeuredebut() 
		+ "' , heurefin = '"+ unRdv.getHeurefin() + "' , idpersonne = '"+ unRdv.getIdpersonne()
		+ "' , idmagasin = '"+ unRdv.getIdmagasin() +"' where idrdv = " + unRdv.getIdrdv();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			unStat.execute(requete); // execute comme php
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static int countRdv()
	{
		int nb = 0;
		String requete = "select count(*) as nb from RDV ;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
	}
	
	
	
	//*******************************GESTION RESERVATION********************************
	
	public static void insertReservation(Reservation uneReservation)
	{
		String requete = "insert into Reservation values (null, '"+uneReservation.getHoraire()
		+ "', '"+uneReservation.getIdlunette()+"';";
		
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static ArrayList<Reservation> selectAllReservations()
	{
		String requete = "select * from Reservation;";
		ArrayList<Reservation> desReservations = new ArrayList<Reservation>();
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				Reservation uneReservation = new Reservation(
						desResultats.getInt("idreservation"),
						desResultats.getString("horaire"),
						desResultats.getInt("idlunette")

						);
				desReservations.add(uneReservation);
			}
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return desReservations;
	}
	
	
	public static void deleteReservation(int idreservation)
	{
		String requete = "delete from Reservation where idreservation = '"+idreservation;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	//comme type classe Reservation alors on peut utiliser objet uneReservation 
	public static Reservation selectWhereReservation(String horaire, int idlunette)
	{
		Reservation uneReservation = null;
		String requete = "select * from Reservation where horaire = '"+horaire+"' and  idlunette = '"+idlunette+"';";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
					uneReservation = new Reservation(
						desResultats.getInt("idreservation"),
						desResultats.getString("horaire"),
						desResultats.getInt("idlunette")

						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return uneReservation;
	}
	
	public static void updateReservation(Reservation uneReservation)
	{
		String requete = "update Reservation set '"+uneReservation.getHoraire()
		+"', idlunette = '"+uneReservation.getIdlunette()+"' where idreservation = '"+uneReservation.getIdreservation();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			unStat.execute(requete); // execute comme php
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static int countReservation()
	{
		int nb = 0;
		String requete = "select count(*) as nb from Reservation ;";
		
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt(nb);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return nb;
	}


	//******************************TABLEAU DE BORD TECH **************************************
	
	public static void insertTabTech(TableauTech unTech) {
		String requete = "call insertTech('"+ unTech.getNom()+ "','" + unTech.getPrenom()
		+ "','" + unTech.getAdresse() + "','" + unTech.getEmail() 
		+ "','" + unTech.getDiplome() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static ArrayList<TableauTech> selectAllTabTechs(String mot)
	{
		//String requete = "select * from personneTech;"; 
		ArrayList<TableauTech> lesTableauxTechs = new ArrayList<TableauTech>();  
		
		String requete = "";
		if(mot.equals(""))
		{
			requete = "select * from personneTech;";
		}
		else
		{
			requete = "select * from personneTech where nom like '%" +mot+ "%' or email like '%"+mot+ "%'";
		}
		
		try { 
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				TableauTech unTableauTech = new TableauTech(
						desResultats.getInt("idPersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("email"),
						desResultats.getString("adresse"),
						desResultats.getString("diplome")

						);
				lesTableauxTechs.add(unTableauTech);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesTableauxTechs;
	}
	
	
	public static TableauTech selectWhereTabTech(String email, String diplome)
	{
		String requete = "select * from personneTech where email = '"+email+"' and diplome = '"+diplome+"';";
		TableauTech unTech = null;// on initialise unTechnicien tt simplement
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
					unTech = new TableauTech(// on instancie un Tech
						desResultats.getInt("idPersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("diplome")

						
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return unTech;
	}
	
	public static TableauTech selectWhereTabTech(int idpersonne)
	{
		String requete = "select * from personneTech where idpersonne = '"+idpersonne+"' ;";
		TableauTech unTech = null;// on initialise unTechnicien tt simplement
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
					unTech = new TableauTech(// on instancie un Tech
						desResultats.getInt("idpersonnne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getString("diplome")

						
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return unTech;
	}
	
	public static void deleteTabTech(int idpersonne)
	{
		String requete = "delete from personneTech where idpersonne =  "+ idpersonne;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}

	}
	

	public static void updateTabTech(TableauTech unTech)
	{
		String requete = "update personneTech set nom =  '" + unTech.getNom()
		+ "', prenom = '" + unTech.getPrenom() + "', email = '"+ unTech.getEmail() 
		+ "' , adresse = '"+ unTech.getAdresse()
		+ "' , diplome = '"+ unTech.getDiplome() +"' where idpersonne = " + unTech.getIdpersonne();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			unStat.execute(requete); // execute comme php
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static int countTabTech()
	{
		int nb=0;
		String requete = "select count(*) as nb from Technicien";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt("nb");
			}
			
			//unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		return nb;
	}
	
	
	//*******************TAB DE BORD CLIENT************************************
	
	public static void insertTabCli(TableauCli unCli)
	{
		String requete = "call insertClient('"+ unCli.getNom()+ "','" + unCli.getPrenom()
		+ "','" + unCli.getAdresse() + "','" + unCli.getEmail() + "','" + unCli.getAge()
		+ "','" + unCli.getTelephone() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	
	public static ArrayList<TableauCli> selectAllTabClis()
	{
		String requete = "select * from personneClient;"; 
		ArrayList<TableauCli> lesTableauxClis = new ArrayList<TableauCli>();  
		
		try { 
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				TableauCli unTableauCli = new TableauCli(
						desResultats.getInt("idpersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getInt("age"),
						desResultats.getString("telephone")


						);
				lesTableauxClis.add(unTableauCli);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesTableauxClis;
	}
	
	public static void deleteTabCli(int idpersonne)
	{
		String requete = "delete from personneClient where idpersonne =  "+ idpersonne;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de la requete :" + requete);

		}
	}
	
	public static TableauCli selectWhereTabCli(String nom, String telephone)
	{
		String requete = "select * from personneClient where nom = '" +nom+ "' and telephone = '" +telephone+ "' ";
		TableauCli unCli = null;
		
		try{
			
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				unCli = new TableauCli(
						desResultats.getInt("idpersonne"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("email"),
						desResultats.getInt("age"),
						desResultats.getString("telephone")
						);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		
		return unCli;
	}
	
	public static void updateTabCli(TableauCli unCli)
	{
		String requete = "update personneClient set nom =  '" + unCli.getNom()
		+ "', prenom = '" + unCli.getPrenom() + "', adresse = '"+ unCli.getAdresse() 
		+ "' , email = '"+ unCli.getEmail() + "' , age = '"+ unCli.getAge() 
		+ "' , telephone = '"+ unCli.getTelephone() +"' where idpersonne = " + unCli.getIdpersonne();
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			unStat.execute(requete); // execute comme php
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	public static int countTabCli()
	{
		int nb=0;
		String requete = "select count(*) as nb from personneClient";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();//curseur
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next())
			{
				nb = unResultat.getInt("nb");
			}
			
			//unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
		return nb;
	}
	
	//*************************TAB DE BORD RDV**********************************
	
	public static void insertTabRdv(TableauRdv unRdv)
	{
		String requete = "insert into RDV values (null,'" + unRdv.getMotif()
		+ "','" + unRdv.getDateRdv() + "','" + unRdv.getHeuredebut() + "','" + unRdv.getHeurefin() 
		+ "','" + unRdv.getNomCli() + "','" + unRdv.getNomMagasin() + "' );";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur d execution de requete "+ requete);
		}
	}
	
	
	public static ArrayList<TableauRdv> selectAllTabRdv()
	{
		String requete = "select * from rdvClientMagasin;"; 
		
		ArrayList<TableauRdv> lesTabRdvs = new ArrayList<TableauRdv>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				TableauRdv unTabRdv = new TableauRdv(
						desResultats.getString("motif"),
						desResultats.getString("dateRdv"),
						desResultats.getString("heuredebut"),
						desResultats.getString("heurefin"),
						desResultats.getString("nomCli"),
						desResultats.getString("nomMagasin")
						);
				lesTabRdvs.add(unTabRdv);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesTabRdvs;
	}
	
	//*************************TAB DE BORD RESERVATION**********************************
	
	public static ArrayList<TableauReserv> selectAllTabReserv()
	{
		String requete = "select * from reservaLunette;"; 
		
		ArrayList<TableauReserv> lesTabReservs = new ArrayList<TableauReserv>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			
			
			ResultSet desResultats = unStat.executeQuery(requete);
			
			while(desResultats.next())
			{
				TableauReserv unTabReserv = new TableauReserv(
						desResultats.getString("horaire"),
						desResultats.getInt("idlunette")
						);
				lesTabReservs.add(unTabReserv);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
			
		}
		catch(SQLException exp){
			System.out.println("Erreur d execution de la requete :" + requete);
		}
		
		return lesTabReservs;
	}
	
	
	//*************************TAB DE BORD **********************************

	
	public static ArrayList<TableauBord> selectAllTableauBord ()
    {
        String requete = "select from tabTech ; ";
        ArrayList<TableauBord> lesTableauBords = new ArrayList<TableauBord>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            while(desResultats.next())
            {
                TableauBord unTableauBord = new TableauBord (
                        desResultats.getString("nom"),
                        desResultats.getString("prenom"),
                        desResultats.getString("diplome"),
                        desResultats.getString("adresse"),
                        desResultats.getString("telephone"),
                        desResultats.getInt("nbTech")
                        );
                //ajouter l'instance Vol dans l'ArrayList
                lesTableauBords.add(unTableauBord);
            }
            unStat.close();
            uneBdd.seConnecter();
            unStat.close();
            uneBdd.seDeconnecter();
        }
        catch (SQLException exp)
        {
            System.out.println("Erreur d'execution de la requete :" + requete);
        }
        return lesTableauBords;
    }


}
