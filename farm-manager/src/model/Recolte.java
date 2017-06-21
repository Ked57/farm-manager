package model;

public class Recolte {
	private int id;
	private String date;
	private int fourchette; // Matin ou apres midi
	private float tMax; //Taille maximale de(s) la/les machine(s) souhaitée(s) pour la récolte
	private float quantite; //Quantité recoltée
	private float cout; //Cout de la récolte
	private int idCli;
	private String nomCli;
	private String prenomCli;
	private int idCh;
	private String adresse;
	private int id_typeBottelage;
	private String typeBottelage;
	private int idTransport;
	private String nomTransport;
	
	public Recolte(int id,String date, int fourchette, float tMax, float quantite, float cout, int idCli, String nomCli,
			String prenomCli, int idCh, String adresse, int id_typeBottelage, String typeBottelage, String nomTransport,int idTransport) {
		super();
		this.id = id;
		this.date = date;
		this.fourchette = fourchette;
		this.tMax = tMax;
		this.quantite = quantite;		
		this.cout = cout;
		this.idCli = idCli;
		this.nomCli = nomCli;
		this.prenomCli = prenomCli;
		this.idCh = idCh;
		this.adresse = adresse;
		this.id_typeBottelage = id_typeBottelage;
		this.typeBottelage = typeBottelage;
		this.nomTransport = nomTransport;
		this.idTransport = idTransport;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float gettMax() {
		return tMax;
	}
	public void settMax(float tMax) {
		this.tMax = tMax;
	}
	public int getFourchette() {
		return fourchette;
	}
	public String getNomComplet(){
		return nomCli+" "+prenomCli;
	}
	public void setFourchette(int fourchette) {
		this.fourchette = fourchette;
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
	public int getId_typeBottelage() {
		return id_typeBottelage;
	}
	public void setId_typeBottelage(int id_typeBottelage) {
		this.id_typeBottelage = id_typeBottelage;
	}
	public int getIdTransport() {
		return idTransport;
	}
	public void setIdTransport(int idTransport) {
		this.idTransport = idTransport;
	}
}
