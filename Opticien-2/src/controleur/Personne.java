package controleur;

public class Personne {
	protected int idpersonne;
	protected String nom, prenom, adresse, email;
	
	public Personne(int idpersonne, String nom, String prenom, String adresse, String email)
	{
		this.idpersonne = idpersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		
	}
	
	public Personne(String nom, String prenom, String adresse, String email)
	{
		this.idpersonne = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	


}
