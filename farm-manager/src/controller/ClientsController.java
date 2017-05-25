package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.DataMgr;

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

	private ObservableList<Client> clientList;
	private DataMgr data;

	public ClientsController() {

	}

	public void initialize() {
		
		nomCol.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		numeroCol.setCellValueFactory(new PropertyValueFactory<Client, String>("numero"));
		adresseCol.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
	}

	public void initClients(DataMgr data) {
		this.data = data;
		this.clientList = this.data.getClients();
		if (clientList.size() >= 0) {
			clientsTable.setItems(this.clientList);
			clientsTable.getColumns().clear();
			clientsTable.getColumns().addAll(nomCol, prenomCol, numeroCol, adresseCol);
		}
	}
}
