package model;

import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;

import javafx.collections.ObservableList;

public class Champs {
	private int id;
	private int idCli;
	private String proprietaire;
	private String adresse;
	private int surface;
	private String typeCulture;
	private Point center;
	private ObservableList<Point> points;
	private Polygon poly;
	
	public Champs(int id,int idCli, String proprietaire, String adresse, int surface, String typeCulture, Point center,ObservableList<Point> points){
		this.id = id;
		this.idCli = idCli;
		this.proprietaire = proprietaire;
		this.adresse = adresse;
		this.surface = surface;
		this.typeCulture = typeCulture;
		this.center = center;
		this.points = points;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdCli() {
		return idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public String getTypeCulture() {
		return typeCulture;
	}

	public void setTypeCulture(String typeCulture) {
		this.typeCulture = typeCulture;
	}

	public ObservableList<Point> getPoints() {
		return points;
	}

	public void setPoints(ObservableList<Point> points) {
		this.points = points;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Polygon getPoly() {
		return poly;
	}

	public void setPoly(Polygon poly) {
		this.poly = poly;
	}


	
}
