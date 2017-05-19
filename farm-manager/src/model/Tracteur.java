package model;

public class Tracteur{
	
	private String marque;
	private String modele;
	private float capacite;
	private String etat;
	
	public Tracteur(String marque, String modele, float capacite, int etat){
		this.marque = marque;
		this.modele = modele;
		this.capacite = capacite;
		if(etat <= 0)
			this.etat = "Non disponible";
		else this.etat = "Disponible";
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public float getCapacite() {
		return capacite;
	}

	public void setCapacite(float capacite) {
		this.capacite = capacite;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
}
