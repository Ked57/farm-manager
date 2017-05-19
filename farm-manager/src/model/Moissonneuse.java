package model;

public class Moissonneuse{
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
	
	public Moissonneuse(String marque, String modele,float largCoupe, float consoRoute, float consoFonct, int capaRes, int tailleTrem, int largRoute,
			float hauteur, float poids, int etat) {
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

	
}
