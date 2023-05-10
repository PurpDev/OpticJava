package controleur;

public class Lunette {
	
	private int idlunette;
	private String libelle, couleur, genre, marque, disponibilite;
	private int prix, quantite;
	private int idverre, idmonture;
	
	public Lunette(int idlunette, String libelle, String couleur, String genre, String marque,
			int prix, int quantite, String disponibilite, int idverre, int idmonture)
	{
		this.idlunette =idlunette;
		this.libelle = libelle;
		this.couleur = couleur;
		this.genre = genre;
		this.marque = marque;
		this.prix = prix;
		this.quantite = quantite;
		this.disponibilite = disponibilite;
		this.idverre = idverre;
		this.idmonture = idmonture;
		
	}
	
	public Lunette(String libelle, String couleur, String genre, String marque,
			int prix, int quantite, String disponibilite, int idverre, int idmonture)
	{
		this.idlunette =idlunette;
		this.libelle = libelle;
		this.couleur = couleur;
		this.genre = genre;
		this.marque = marque;
		this.prix = prix;
		this.quantite = quantite;
		this.disponibilite = disponibilite;
		this.idverre = idverre;
		this.idmonture = idmonture;
		
	}

	public int getIdlunette() {
		return idlunette;
	}

	public void setIdlunette(int idlunette) {
		this.idlunette = idlunette;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getIdverre() {
		return idverre;
	}

	public void setIdverre(int idverre) {
		this.idverre = idverre;
	}

	public int getIdmonture() {
		return idmonture;
	}

	public void setIdmonture(int idmonture) {
		this.idmonture = idmonture;
	}
	
	

	

}
