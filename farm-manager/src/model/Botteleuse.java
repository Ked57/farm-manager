package model;

public class Botteleuse extends Machine{
	
	private String type;
	private boolean etat;
	
	public Botteleuse(String marque, String modele, String type, int etat){
		super(marque,modele);
		this.type = type;
		if(etat <= 0)
			this.etat = false;
		else this.etat = true;
	}
}
