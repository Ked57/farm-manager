package model;

public class Champs {
	private String proprietaire;
	private String adresse;
	private int surface;
	private String typeCulture;
	
	public Champs(String proprietaire, String adresse, int surface, String typeCulture){
		this.proprietaire = proprietaire;
		this.adresse = adresse;
		this.surface = surface;
		this.typeCulture = typeCulture;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public String getTypeCulture() {
		return typeCulture;
	}

	public void setTypeCulture(String typeCulture) {
		this.typeCulture = typeCulture;
	}
	
	
}
