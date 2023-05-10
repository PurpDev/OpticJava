package controleur;

public class Monture {

	private int idmonture;
	private String forme, matiereM;
	
	public Monture(int idmonture, String forme, String matiereM)
	{
		this.idmonture = idmonture;
		this.forme = forme;
		this.matiereM = matiereM;
	}
	
	public Monture(String forme, String matiereM)
	{
		this.idmonture = idmonture;
		this.forme = forme;
		this.matiereM = matiereM;
	}

	public int getIdmonture() {
		return idmonture;
	}

	public void setIdmonture(int idmonture) {
		this.idmonture = idmonture;
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}

	public String getMatiereM() {
		return matiereM;
	}

	public void setMatiereM(String matiereM) {
		this.matiereM = matiereM;
	}
	

	
}

