package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableBooleanValue;

public class Moissonneuse{
	private int id;
	private String marque;
	private String modele;
	private float largCoupe;
	private float consoRoute;
	private float consoFonct;
	private int capaRes;
	private int tailleTrem;
	private int largRoute;
	private float hauteur;
	private float poids;
	private String etat;
	private boolean choosed;
	private BooleanProperty choosedProperty;
	
	public Moissonneuse(int id,String marque, String modele,float largCoupe, float consoRoute, float consoFonct, int capaRes, int tailleTrem, int largRoute,
			float hauteur, float poids, int etat) {
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.largCoupe = largCoupe;
		this.consoRoute = consoRoute;
		this.consoFonct = consoFonct;
		this.capaRes = capaRes;
		this.tailleTrem = tailleTrem;
		this.largRoute = largRoute;
		this.hauteur = hauteur;
		this.poids = poids;
		if(etat <= 0)
			this.etat = "Non disponible";
		else this.etat = "Disponible";
		this.choosed = false;
		this.choosedProperty = new SimpleBooleanProperty();
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

	public float getLargCoupe() {
		return largCoupe;
	}
	public void setLargCoupe(float largCoupe) {
		this.largCoupe = largCoupe;
	}
	public float getConsoRoute() {
		return consoRoute;
	}
	public void setConsoRoute(float consoRoute) {
		this.consoRoute = consoRoute;
	}
	public float getConsoFonct() {
		return consoFonct;
	}
	public void setConsoFonct(float consoFonct) {
		this.consoFonct = consoFonct;
	}
	public int getCapaRes() {
		return capaRes;
	}
	public void setCapaRes(int capaRes) {
		this.capaRes = capaRes;
	}
	public int getTailleTrem() {
		return tailleTrem;
	}
	public void setTailleTrem(int tailleTrem) {
		this.tailleTrem = tailleTrem;
	}
	public int getLargRoute() {
		return largRoute;
	}
	public void setLargRoute(int largRoute) {
		this.largRoute = largRoute;
	}
	public float getHauteur() {
		return hauteur;
	}
	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
		this.poids = poids;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
