package controleur;

public class Rdv {

	private int idrdv;
	private String motif, dateRdv, heuredebut, heurefin;
	private int idpersonne, idmagasin;
	
	public Rdv(int idrdv, String motif, String dateRdv, String heuredebut, String heurefin, int idpersonne, int idmagasin)
	{
		this.idrdv = idrdv;
		this.motif = motif;
		this.dateRdv = dateRdv;
		this.heuredebut = heuredebut;
		this.heurefin = heurefin;
		this.idpersonne = idpersonne;
		this.idmagasin = idmagasin;
	}
	
	public Rdv(String motif,  String dateRdv, String heuredebut, String heurefin, int idpersonne, int idmagasin)
	{
		this.idrdv = idrdv;
		this.motif = motif;
		this.dateRdv = dateRdv;
		this.heuredebut = heuredebut;
		this.heurefin = heurefin;
		this.idpersonne = idpersonne;
		this.idmagasin = idmagasin;
	}

	public int getIdrdv() {
		return idrdv;
	}

	public void setIdrdv(int idrdv) {
		this.idrdv = idrdv;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(String dateRdv) {
		this.dateRdv = dateRdv;
	}

	public String getHeuredebut() {
		return heuredebut;
	}

	public void setHeuredebut(String heuredebut) {
		this.heuredebut = heuredebut;
	}

	public String getHeurefin() {
		return heurefin;
	}

	public void setHeurefin(String heurefin) {
		this.heurefin = heurefin;
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public int getIdmagasin() {
		return idmagasin;
	}

	public void setIdmagasin(int idmagasin) {
		this.idmagasin = idmagasin;
	}
	
	

	
	
}
