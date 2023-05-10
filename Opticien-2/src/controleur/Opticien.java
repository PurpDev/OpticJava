package controleur;

import modele.Modele;
import vue.VueConnexion;
import vue.VueGenerale;

public class Opticien {
	
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void creerVueGenerale(User unUser)
	{
		uneVueGenerale = new VueGenerale(unUser);
	}
	
	
	public static void detruireVueGenerale()
	{
		uneVueGenerale.dispose();
	}
	
	public static void rendreVisibleVueConnexion(boolean action)
	{
		uneVueConnexion.setVisible(action);
	}
	
	
	public static User selectWhereUser(String email, String mdp)
	{
		User unUser = Modele.selectWhereUser(email, mdp);
		return unUser;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		uneVueConnexion = new VueConnexion();

	}

}
