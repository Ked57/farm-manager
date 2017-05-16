package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty numero;
	private StringProperty adresse;
	
	public Client(String nom, String prenom, String numero,String adresse){
		
		this.nom = new SimpleStringProperty(nom);
		
		this.prenom = new SimpleStringProperty(prenom);
		
		this.numero = new SimpleStringProperty(numero);
		
		this.adresse = new SimpleStringProperty(adresse);
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	
	public String getNumero() {
		return numero.get();
	}

	public void setNumero(String numero) {
		this.numero.set(numero);
	}
	
	public String getAdresse() {
		return adresse.get();
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	
}
