package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MenuController {
	
	private BorderPane listeChamps;
	private BorderPane listeClients;
	private BorderPane listeMachines;
	private BorderPane planning;
	private BorderPane SelecMachines;
	private BorderPane accueil;
	@FXML
	private BorderPane rootPane;
	
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
	public void listChampsOnAction(){
		rootPane.setCenter(listeChamps);
	}
	@FXML
	public void listClientsOnAction(){
		rootPane.setCenter(listeClients);
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
}
