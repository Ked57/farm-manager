package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Botteleuse{
	private int idMach;
	private int id;
	private String marque;
	private String modele;
	private String type; // pour plus tard
	private String etat;
	private boolean choosed;
	private BooleanProperty choosedProperty;
	
	public Botteleuse(int idMach, int id,String marque, String modele, int etat){
		this.idMach = idMach;
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.type = type;
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
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
		this.choosedProperty.set(choosed);
	}
	public BooleanProperty choosedProperty(){
		return this.choosedProperty;
	}
	
}
