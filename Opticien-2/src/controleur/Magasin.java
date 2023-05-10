package controleur;

public class Magasin {
	private int idmagasin;
	protected String nom, adresse, email, telephone;
	
	public Magasin(int idmagasin, String nom, String adresse, String email, String telephone)
	{
		this.idmagasin = idmagasin;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;

	}
	
	public Magasin(String nom, String adresse, String email, String telephone)
	{
		this.idmagasin = idmagasin;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;


	}

	public int getIdmagasin() {
		return idmagasin;
	}

	public void setIdmagasin(int idmagasin) {
		this.idmagasin = idmagasin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

	
	

}
