package controleur;

public class TableauBord {
	
	private String nom, prenom, diplome, adresse, telephone;
	private int nbTech;

	public TableauBord(String nom, String prenom, String adresse, String diplome,  String telephone , int nbTech) {
		 this.nom = nom;
	     this.prenom = prenom;
	     this.diplome = diplome;
	     this.adresse = adresse;
	     this.telephone = telephone;
	     this.nbTech = nbTech;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public int getNbTech() {
		return nbTech;
	}

	public void setNbTech(int nbTech) {
		this.nbTech = nbTech;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
	

}
