package controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Client;

public class ClientsController {
	@FXML
	private TableView<Client> clientsTable;
	@FXML
	private TableColumn<String[],String> nomCol;
	@FXML
	private TableColumn<String[],String> prenomCol;
	@FXML
	private TableColumn<String[],String> numeroCol;
	@FXML
	private TableColumn<String[],String> adresseCol;
	
	ObservableList<Client> clientList;
	
	
	public ClientsController() {
		 
	}
	
	public void initialize() {
		
	}
	
	public void initClients(ObservableList<Client> clientList) throws SQLException {
		this.clientList = clientList;
		clientsTable.setItems(this.clientList);
	}
}
