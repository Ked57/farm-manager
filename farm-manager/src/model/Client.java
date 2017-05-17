package model;


public class Client {
	
	private String nom;
	private String prenom;
	private String numero;
	private String adresse;
	
	public Client(String nom, String prenom, String numero,String adresse){
		
		this.nom = nom;
		
		this.prenom = prenom;
		
		this.numero = numero;
		
		this.adresse = adresse;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
