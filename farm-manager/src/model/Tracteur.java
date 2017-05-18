package model;

public class Tracteur extends Machine{
	
	private float capacite;
	private boolean etat;
	
	public Tracteur(String marque, String modele, float capacite, int etat){
		super(marque,modele);
		this.capacite = capacite;
		if(etat <= 0)
			this.etat = false;
		else this.etat = true;
	}
}
