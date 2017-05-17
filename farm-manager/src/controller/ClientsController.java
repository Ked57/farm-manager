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
	
	private TableColumn<Client,String> nomCol;	
	private TableColumn<Client,String> prenomCol;
	private TableColumn<Client,String> numeroCol;
	private TableColumn<Client,String> adresseCol;
	
	ObservableList<Client> clientList;
	
	
	public ClientsController() {
		 
	}
	
	public void initialize() {
		nomCol = new TableColumn<>("Nom");
		nomCol.setPrefWidth(300);
		nomCol.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
		
		prenomCol = new TableColumn<>("Prénom");
		prenomCol.setPrefWidth(300);
		prenomCol.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
		
		numeroCol = new TableColumn<>("Numéro de Téléphone");
		numeroCol.setPrefWidth(300);
		numeroCol.setCellValueFactory(new PropertyValueFactory<Client,String>("numero"));
		
		adresseCol = new TableColumn<>("adresseCol");
		adresseCol.setPrefWidth(300);
		adresseCol.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
	}
	
	public void initClients(ObservableList<Client> clientList){
		this.clientList = clientList;
		clientsTable.setItems(this.clientList);
		clientsTable.getColumns().clear();
		clientsTable.getColumns().addAll(nomCol,prenomCol,numeroCol,adresseCol);
	}
}
