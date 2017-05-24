package controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Botteleuse;
import model.Champs;
import model.DbMgr;
import model.Moissonneuse;
import model.Tracteur;

public class SelecMachinesController {

	@FXML private TableView<Moissonneuse> moissTable;
	
	@FXML private TableColumn<Moissonneuse,String> moissModeleColumn;
	@FXML private TableColumn<Moissonneuse,Boolean> moissChoixColumn;

	private ObservableList<Moissonneuse> moissList;
	
	private ObservableList<Moissonneuse> moissDispList;

	public SelecMachinesController() {

	}

	public void initialize(){
		moissModeleColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		moissChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(moissChoixColumn));
		moissChoixColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,Boolean>("choosed"));
		moissTable.setEditable(true);
	}

	public void initSelecMachines(ObservableList<Moissonneuse> moissList) throws ClassNotFoundException, SQLException {		
		moissDispList = moissList;
		moissTable.setItems(moissDispList);
		moissTable.getColumns().clear();
		moissTable.getColumns().addAll(moissModeleColumn,moissChoixColumn);

	}
}
