package model;

import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;

import javafx.collections.ObservableList;

public class Champs {
	private int id;
	private String proprietaire;
	private String adresse;
	private int surface;
	private String typeCulture;
	private ObservableList<Point> points;
	private Polygon poly;
	
	public Champs(int id, String proprietaire, String adresse, int surface, String typeCulture,ObservableList<Point> points){
		this.id = id;
		this.proprietaire = proprietaire;
		this.adresse = adresse;
		this.surface = surface;
		this.typeCulture = typeCulture;
		this.points = points;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Polygon getPoly() {
		return poly;
	}

	public void setPoly(Polygon poly) {
		this.poly = poly;
	}


	
}
