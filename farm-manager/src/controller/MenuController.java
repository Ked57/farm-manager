package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Client;
import model.DbMgr;

public class MenuController {
	//BorderPane
	private BorderPane listeChamps;
	private BorderPane listeClients;
	private BorderPane listeMachines;
	private BorderPane planning;
	private BorderPane selecMachines;
	private BorderPane accueil;
	@FXML
	private BorderPane rootPane;
	
	//Controllers
	private ClientsController clientsController;
	private ChampsController champsController;
	private AccueilController accueilController;
	private MachinesController machinesController;
	private PlanningController planningController;
	private SelecMachinesController selecMachinesController;
	
	private DbMgr db;
	
	public MenuController() {
	}
	
	public void initialize() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListeChamps.fxml"));
		listeChamps = loader.load();
		champsController = loader.getController();
		
		loader = new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
		accueil = loader.load();
		accueilController = loader.getController();
		
    	loader = new FXMLLoader(getClass().getResource("/view/ListeClients.fxml"));	
    	listeClients = loader.load();
    	clientsController = loader.getController();
		
		
    	loader = new FXMLLoader(getClass().getResource("/view/ListeMachines.fxml"));
    	listeMachines = loader.load();
    	machinesController = loader.getController();
    	
    	loader = new FXMLLoader(getClass().getResource("/view/Planning.fxml"));
    	planning = loader.load();
    	planningController = loader.getController();
    	
    	
    	loader = new FXMLLoader(getClass().getResource("/view/SelecMachines.fxml"));
    	selecMachines = loader.load();
    	selecMachinesController = loader.getController();
		
		rootPane.setCenter(accueil);
	}
	@FXML
	public void accueilOnAction(){
		rootPane.setCenter(accueil);
		accueilController.initMap();
	}
	@FXML
	public void listeChampsOnAction() throws SQLException{
		rootPane.setCenter(listeChamps);
		
		champsController.initChamps(db.getChampsList());
	}
	@FXML
	public void listeClientsOnAction() throws SQLException{ 
		rootPane.setCenter(listeClients);
		
		
		clientsController.initClients(db.getClientsList());
	}
	@FXML
	public void listeMachinesOnAction() throws SQLException{
		rootPane.setCenter(listeMachines);
		machinesController.setMoissonneuse(db.getMoissonneuseList());
		
		
	}
	@FXML
	public void planningOnAction() throws SQLException{
		rootPane.setCenter(planning);
		planningController.setClients(db.getClientsList());
		planningController.setChamps(db.getChampsList());
	}
	@FXML
	public void selecMachinesOnAction(){
		rootPane.setCenter(selecMachines);
	}

	public DbMgr getDb() {
		return db;
	}

	public void setDb(DbMgr db) {
		this.db = db;
	}
}
