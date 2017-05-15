package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.DbMgr;

public class MenuController {
	//BorderPane
	private BorderPane listeChamps;
	private BorderPane listeClients;
	private BorderPane listeMachines;
	private BorderPane planning;
	private BorderPane SelecMachines;
	private BorderPane accueil;
	@FXML
	private BorderPane rootPane;
	
	//Controllers
	private ClientsController clients;
	
	private DbMgr db;
	
	public MenuController() {
	}
	
	public void initialize() throws IOException{
		
		listeChamps = FXMLLoader.load(getClass().getResource("/view/ListeChamps.fxml"));
		accueil = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
		listeClients = FXMLLoader.load(getClass().getResource("/view/ListeClients.fxml"));
		listeMachines = FXMLLoader.load(getClass().getResource("/view/ListeMachines.fxml"));
		planning = FXMLLoader.load(getClass().getResource("/view/Planning.fxml"));
		SelecMachines = FXMLLoader.load(getClass().getResource("/view/SelecMachines.fxml"));
	}
	@FXML
	public void accueilOnAction(){
		rootPane.setCenter(accueil);
	}
	@FXML
	public void listeChampsOnAction(){
		rootPane.setCenter(listeChamps);
	}
	@FXML
	public void listeClientsOnAction() throws SQLException{ 
		rootPane.setCenter(listeClients);
		clients = new ClientsController();
		
		java.sql.ResultSet rs = db.getClientsList();
		clients.initClients(rs);
	}
	@FXML
	public void listeMachinesOnAction(){
		rootPane.setCenter(listeMachines);
	}
	@FXML
	public void planningOnAction(){
		rootPane.setCenter(planning);
	}
	@FXML
	public void selecMachinesOnAction(){
		rootPane.setCenter(SelecMachines);
	}

	public DbMgr getDb() {
		return db;
	}

	public void setDb(DbMgr db) {
		this.db = db;
	}
}
