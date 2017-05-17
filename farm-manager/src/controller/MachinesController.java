package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Machine;
import model.Moissoneuse;

public class MachinesController {
	@FXML
	private TableView<Machine> moissoneusesTable;
	@FXML
	private TableView<Machine> tracteursTable;
	@FXML
	private TableView<Machine> botteleusesTable;
	
	private TableColumn<Moissoneuse,String> Col;	
}
