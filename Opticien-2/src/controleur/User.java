package controleur;

public class User {
	private int iduser;
	private String nom, prenom, email, mdp, typerole;
	
	public User(int iduser, String nom, String prenom, String email, String mdp, String typerole)
	{
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.typerole = typerole;
	}
	
	public User(String nom, String prenom, String email, String mdp, String typerole)
	{
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.typerole = typerole;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getTyperole() {
		return typerole;
	}

	public void setTyperole(String typerole) {
		this.typerole = typerole;
	}
	
	

}
