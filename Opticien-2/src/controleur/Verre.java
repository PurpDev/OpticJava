package controleur;

public class Verre {
	
	
	private int idverre;
	private String vision, couleurV, matiereV, laboratoire;
	
	public Verre(int idverre, String vision, String couleurV, String matiereV, String laboratoire)
	{
		this.idverre = idverre;
		this.vision = vision;
		this.couleurV = couleurV;
		this.matiereV = matiereV;
		this.laboratoire = laboratoire;
	}
	
	public Verre(String vision, String couleur, String matiereV, String laboratoire)
	{
		this.idverre = idverre;
		this.vision = vision;
		this.couleurV = couleurV;
		this.matiereV = matiereV;
		this.laboratoire = laboratoire;
	}

	public int getIdverre() {
		return idverre;
	}

	public void setIdverre(int idverre) {
		this.idverre = idverre;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getCouleurV() {
		return couleurV;
	}

	public void setCouleur(String couleurV) {
		this.couleurV = couleurV;
	}

	public String getMatiereV() {
		return matiereV;
	}

	public void setMatiereV(String matiereV) {
		this.matiereV = matiereV;
	}

	public String getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(String laboratoire) {
		this.laboratoire = laboratoire;
	}
	
	
	
	
	
	
	

}
