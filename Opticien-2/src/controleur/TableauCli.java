package controleur;

public class TableauCli {
	
	private String nom, prenom, adresse, email,telephone;
	private int idpersonne;
	private int age;

	public TableauCli( int idpersonne, String nom, String prenom, String adresse, String email, int age, String telephone) {
		// TODO Auto-generated constructor stub
		this.idpersonne = idpersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.age = age;
		this.telephone = telephone;
	
	}
	
	public TableauCli(String nom, String prenom, String adresse, String email, int age, String telephone) {
		// TODO Auto-generated constructor stub
		this.idpersonne = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.age = age;
		this.telephone = telephone;
	
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/*public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}*/
	
	

}
