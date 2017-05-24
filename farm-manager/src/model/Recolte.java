package model;

public class Recolte {
	private int id;
	private String fourchette; // Matin ou apres midi
	private float tMax; //Taille maximale de(s) la/les machine(s) souhaitée(s) pour la récolte
	private float quantite; //Quantité recoltée
	private float cout; //Cout de la récolte
	private int idCli;
	private String nomCli;
	private String prenomCli;
	private int idCh;
	private String adresse;
	private String typeBottelage;
	private String nomTransport;
	public Recolte(int id, int fourchette, float tMax, float quantite, float cout, int idCli, String nomCli,
			String prenomCli, int idCh, String adresse, String typeBottelage, String nomTransport) {
		super();
		this.id = id;
		if(fourchette >= 1)
			this.fourchette = "Après midi";
		else this.fourchette = "Matin";
		this.tMax = tMax;
		this.quantite = quantite;		
		this.cout = cout;
		this.idCli = idCli;
		this.nomCli = nomCli;
		this.prenomCli = prenomCli;
		this.idCh = idCh;
		this.adresse = adresse;
		this.typeBottelage = typeBottelage;
		this.nomTransport = nomTransport;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFourchette() {
		return fourchette;
	}
	public void setFourchette(String fourchette) {
		this.fourchette = fourchette;
	}
	public float getTMax() {
		return tMax;
	}
	public void setTMax(float tMax) {
		this.tMax = tMax;
	}
	public float getQuantite() {
		return quantite;
	}
	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public int getIdCli() {
		return idCli;
	}
	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}
	public String getNomCli() {
		return nomCli;
	}
	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}
	public String getPrenomCli() {
		return prenomCli;
	}
	public void setPrenomCli(String prenomCli) {
		this.prenomCli = prenomCli;
	}
	public int getIdCh() {
		return idCh;
	}
	public void setIdCh(int idCh) {
		this.idCh = idCh;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTypeBottelage() {
		return typeBottelage;
	}
	public void setTypeBottelage(String typeBottelage) {
		this.typeBottelage = typeBottelage;
	}
	public String getNomTransport() {
		return nomTransport;
	}
	public void setNomTransport(String nomTransport) {
		this.nomTransport = nomTransport;
	}
	
}
