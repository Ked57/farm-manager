package model;

public class Botteleuse{
	private int id;
	private String marque;
	private String modele;
	private String type; // pour plus tard
	private String etat;
	
	public Botteleuse(int id,String marque, String modele, int etat){
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
