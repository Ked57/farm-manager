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
import model.DataMgr;
import model.DbMgr;
import model.Moissonneuse;
import model.Tracteur;

public class SelecMachinesController {

	@FXML private TableView<Moissonneuse> moissTable;
	
	@FXML private TableColumn<Moissonneuse,String> moissModeleColumn;
	@FXML private TableColumn<Moissonneuse,Boolean> moissChoixColumn;

	private ObservableList<Moissonneuse> moissList;
	
	private ObservableList<Moissonneuse> moissDispList;
	
	private DataMgr data;

	public SelecMachinesController() {

	}

	public void initialize(){
		moissModeleColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		moissChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(moissChoixColumn));
		moissChoixColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,Boolean>("choosed"));
		moissTable.setEditable(true);		
	}

	public void initSelecMachines(DataMgr data, String date, int fourchette,int idRec) throws ClassNotFoundException, SQLException {
		this.data = data;
		moissDispList = data.getMoissonneuses(date,fourchette,idRec);
		ObservableList<Moissonneuse> choices = data.getMoissonneuses(idRec);
		for(int i = 0; i < moissDispList.size(); ++i){
			for(int j = 0; j < choices.size(); ++j){
				if(moissDispList.get(i).getId() == choices.get(j).getId()){
					moissDispList.get(i).setChoosed(true);
					System.out.println(moissDispList.get(i).isChoosed()+"");
				}
					
			}
		}
		moissTable.setItems(moissDispList);
		moissTable.getColumns().clear();
		moissTable.getColumns().addAll(moissModeleColumn,moissChoixColumn);

	}
}
