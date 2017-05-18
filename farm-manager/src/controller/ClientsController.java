package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

public class ClientsController {
	@FXML
	private TableView<Client> clientsTable;
	@FXML
	private TableColumn<Client, String> nomCol;
	@FXML
	private TableColumn<Client, String> prenomCol;
	@FXML
	private TableColumn<Client, String> numeroCol;
	@FXML
	private TableColumn<Client, String> adresseCol;

	ObservableList<Client> clientList;

	public ClientsController() {

	}

	public void initialize() {
		
		nomCol.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		numeroCol.setCellValueFactory(new PropertyValueFactory<Client, String>("numero"));
		adresseCol.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
	}

	public void initClients(ObservableList<Client> clientList) {
		this.clientList = clientList;
		if (clientList.size() >= 0) {
			clientsTable.setItems(this.clientList);
			clientsTable.getColumns().clear();
			clientsTable.getColumns().addAll(nomCol, prenomCol, numeroCol, adresseCol);
		}
	}
}
