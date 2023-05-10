package controleur;

public class TableauReserv {

	private int idreservation;
	private String horaire;
	private int idlunette;

	public TableauReserv(int idreservation, String horaire, int idlunette) {
		
		this.idreservation = idreservation;
		this.horaire = horaire;
		this.idlunette = idlunette;
		
		
	}
	
	public TableauReserv(String horaire, int idlunette) {
		
		this.horaire = horaire;
		this.idlunette = idlunette;
		
		
	}

	public int getIdreservation() {
		return idreservation;
	}

	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	/*public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}*/

	public int getIdlunette() {
		return idlunette;
	}

	public void setIdlunette(int idlunette) {
		this.idlunette = idlunette;
	}
	
	

}
