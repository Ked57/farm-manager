package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Tracteur{
	private int idMach;
	private int id;
	private String marque;
	private String modele;
	private float capacite;
	private String etat;
	private Boolean choosed;
	private BooleanProperty choosedProperty;
	
	public Tracteur(int idMach,int id,String marque, String modele, float capacite, int etat){
		this.idMach = idMach;
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.capacite = capacite;
		if(etat <= 0)
			this.etat = "Non disponible";
		else this.etat = "Disponible";
		this.choosed = false;
		this.choosedProperty = new SimpleBooleanProperty();
	}
	

	public int getIdMach() {
		return idMach;
	}


	public void setIdMach(int idMach) {
		this.idMach = idMach;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	
	public Boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(Boolean choosed) {
		this.choosed = choosed;
		this.choosedProperty.setValue(this.choosed);
	}
	
	public BooleanProperty choosedProperty(){
		return this.choosedProperty;
	}
}
