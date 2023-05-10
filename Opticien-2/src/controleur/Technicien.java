package controleur;


public class Technicien extends Personne {
	
	private String diplome;
	
	public Technicien(int idpersonne, String nom, String prenom, String adresse, String email, String diplome)
	{
		super(idpersonne, nom, prenom, adresse, email);
		this.diplome = diplome;
	}
	
	public Technicien(String nom, String prenom, String adresse, String email, String diplome)
	{
		super(nom, prenom, adresse, email);
		this.diplome = diplome;
	}
	
	
	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	
	
	


}
