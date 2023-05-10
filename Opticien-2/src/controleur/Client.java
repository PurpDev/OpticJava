package controleur;

public class Client extends Personne{
	
	/*j ai changer int age en string age*/
	
	private String age;
	private String telephone;
	
	public Client(int idpersonne, String nom, String prenom, String adresse, String email, String age, String telephone )
	{
		super(idpersonne, nom, prenom, adresse, email);
		this.age = age;
		this.telephone = telephone;
		
	}
	
	public Client(String nom, String prenom, String adresse, String email, String age, String telephone )
	{
		super(nom, prenom, adresse, email);
		this.age = age;
		this.telephone = telephone;
		
	}
	

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

	

}
