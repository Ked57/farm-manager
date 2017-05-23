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
import model.DbMgr;
import model.Moissonneuse;
import model.Tracteur;

public class SelecMachinesController {

	@FXML private TableView<Moissonneuse> moissTable;
	
	@FXML private TableColumn<Moissonneuse,String> moissModeleColumn;
	@FXML private TableColumn<Moissonneuse,Boolean> moissChoixColumn;

	private DbMgr db;

	private ObservableList<Moissonneuse> moissList;
	
	private ObservableList<Integer> moissDispList;

	public SelecMachinesController() {

	}

	public void initialize(){
		
	}

	public void initSelecMachines(DbMgr db, String today) throws ClassNotFoundException, SQLException {
		this.db = db;
		
		moissDispList = db.getMoissonneuseForDay(today);
		moissList = db.getMoissonneuseList(moissDispList);
		moissModeleColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		moissChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(moissChoixColumn));
		moissChoixColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,Boolean>( "choosed" ) );
		moissTable.setItems(moissList);
		System.out.println(moissList.size());
		moissTable.getColumns().clear();
		moissTable.getColumns().addAll(moissModeleColumn);
	}
}
