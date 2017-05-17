package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Champs;
import model.Client;

public class ChampsController {
	
	@FXML
	private TableView<Champs> champsTable;
	
	private TableColumn<Champs,String> proprietaireCol;	
	private TableColumn<Champs,String> adresseCol;
	private TableColumn<Champs,Integer> surfaceCol;
	private TableColumn<Champs,String> typeCultCol;
	
	private ObservableList<Champs> champsListe;
	
	public ChampsController() {
		
	}
	
	public void initialize(){
		
	}
	
	public void initChamps(ObservableList<Champs> champsListe){
		this.champsListe = champsListe;
		champsTable.setItems(this.champsListe);
		
		proprietaireCol = new TableColumn<>("Propriétaire");
		proprietaireCol.setPrefWidth(300);
		proprietaireCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("proprietaire"));
		
		adresseCol = new TableColumn<>("Adresse");
		adresseCol.setPrefWidth(300);
		adresseCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("adresse"));
		
		surfaceCol = new TableColumn<>("Surface");
		surfaceCol.setPrefWidth(300);
		surfaceCol.setCellValueFactory(new PropertyValueFactory<Champs,Integer>("surface"));
		
		typeCultCol = new TableColumn<>("Type de culture");
		typeCultCol.setPrefWidth(300);
		typeCultCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("typeCulture"));
		
		champsTable.getColumns().addAll(proprietaireCol,adresseCol,surfaceCol,typeCultCol);
	}
}
