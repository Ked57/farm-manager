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
import model.DataMgr;
import model.DbMgr;
import model.SettingsMgr;

public class MenuController {
	//BorderPane
	private BorderPane listeChamps;
	private BorderPane listeClients;
	private BorderPane listeMachines;
	private BorderPane planning;
	private BorderPane selecMachines;
	private BorderPane accueil;
	private BorderPane options;
	@FXML
	private BorderPane rootPane;
	
	//Controllers
	private ClientsController clientsController;
	private ChampsController champsController;
	private AccueilController accueilController;
	private MachinesController machinesController;
	private PlanningController planningController;
	private SelecMachinesController selecMachinesController;
	private OptionsController optionsController;
	
	private DataMgr data;
	private SettingsMgr settings;
	
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
    	
    	loader = new FXMLLoader(getClass().getResource("/view/Options.fxml"));
    	options = loader.load();
    	optionsController = loader.getController();
		
    	
		rootPane.setCenter(accueil);
	}
	@FXML
	public void accueilOnAction() throws SQLException, ClassNotFoundException{
		rootPane.setCenter(accueil);
		accueilController.initAccueil(data);
	}
	@FXML
	public void listeChampsOnAction() throws SQLException, ClassNotFoundException{
		rootPane.setCenter(listeChamps);
		
		champsController.initChamps(data);
	}
	@FXML
	public void listeClientsOnAction() throws SQLException, ClassNotFoundException{ 
		rootPane.setCenter(listeClients);
		
		
		clientsController.initClients(data);
	}
	@FXML
	public void listeMachinesOnAction() throws SQLException, ClassNotFoundException{
		rootPane.setCenter(listeMachines);
		machinesController.setData(data);
		machinesController.setMoissonneuses();
		machinesController.setTracteurs();
		machinesController.setBotteleuses();		
	}
	@FXML
	public void planningOnAction() throws SQLException, ClassNotFoundException{
		rootPane.setCenter(planning);
		planningController.init(data,selecMachinesController);
	}
	@FXML
	public void optionsOnAction(){
		rootPane.setCenter(options);
		optionsController.init(settings);
	}

	public void init(DataMgr data, SettingsMgr settings){
		this.data = data;
		this.settings = settings;
	}
	
}
