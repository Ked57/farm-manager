package controller;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ClientsController {
	@FXML
	private TableView<ObservableList<String>> clientsTable;
	
	
	public ClientsController() {
		
	}
	
	public void initialize() {
		
	}
	
	public void initClients(java.sql.ResultSet rs) throws SQLException {
		
	}
}
