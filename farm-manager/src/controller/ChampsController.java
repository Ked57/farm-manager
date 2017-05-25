package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Champs;
import model.Client;
import model.DataMgr;

public class ChampsController {
	
	@FXML
	private TableView<Champs> champsTable;
	@FXML
	private TableColumn<Champs,String> proprietaireCol;	
	@FXML
	private TableColumn<Champs,String> adresseCol;
	@FXML
	private TableColumn<Champs,Integer> surfaceCol;
	@FXML
	private TableColumn<Champs,String> typeCultCol;
	
	private ObservableList<Champs> champsListe;
	
	private DataMgr data;
	
	public ChampsController() {
		
	}
	
	public void initialize(){
		
	}
	
	public void initChamps(DataMgr data){
		this.data = data;
		this.champsListe = data.getChamps();
		champsTable.setItems(this.champsListe);
		System.out.println("nb champs "+champsListe.size());
		
		proprietaireCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("proprietaire"));
		adresseCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("adresse"));
		surfaceCol.setCellValueFactory(new PropertyValueFactory<Champs,Integer>("surface"));
		typeCultCol.setCellValueFactory(new PropertyValueFactory<Champs,String>("typeCulture"));
		
		champsTable.getColumns().clear();
		champsTable.getColumns().addAll(proprietaireCol,adresseCol,surfaceCol,typeCultCol);
	}
}
