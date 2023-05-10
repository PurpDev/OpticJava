package controleur;

public class TableauTech {
	
	private String nom, prenom, adresse, email;
	private String diplome;
	private int idpersonne;
	
	public TableauTech( int idpersonne, String nom, String prenom, String adresse, String email, String diplome)
	{
		this.idpersonne = idpersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.diplome = diplome;
	}
	
	public TableauTech(String nom, String prenom, String adresse, String email, String diplome)
	{
		this.idpersonne = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.diplome = diplome;
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

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	

}
