package controleur;

public class TableauRdv {
	private String motif, dateRdv, heuredebut, heurefin;
	private String nomCli, nomMagasin;
	
	

	public TableauRdv(String motif, String dateRdv, String heuredebut, String heurefin, String nomCli, String nomMagasin) {
		// TODO Auto-generated constructor stub
		
		this.motif = motif;
		this.dateRdv = dateRdv;
		this.heuredebut = heuredebut;
		this.heurefin = heurefin;
		this.nomCli = nomCli;
		this.nomMagasin = nomMagasin;
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



	public String getNomCli() {
		return nomCli;
	}



	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}



	public String getNomMagasin() {
		return nomMagasin;
	}



	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}
	
	
	

}
