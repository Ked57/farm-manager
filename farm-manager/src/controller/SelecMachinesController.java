package controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Botteleuse;
import model.Champs;
import model.DataMgr;
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
		moissChoixColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,Boolean>("choosed"));
		moissChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(moissChoixColumn));
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
				}
			}
		}

        // listeners pour les boolean properties
        for (Moissonneuse moiss : moissDispList) {
        	moiss.choosedProperty().addListener((obs, previousState, newState) ->{
                System.out.printf("Changement d'état du choix, previous: "+previousState+", new: "+newState+"\n");
                moiss.setChoosed(newState);
                try {
					data.updateMoissForRecolte(moiss, idRec,moiss.isChoosed());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
        }
		moissTable.setItems(moissDispList);
		moissTable.getColumns().clear();
		moissTable.getColumns().addAll(moissModeleColumn,moissChoixColumn);
		
		

	}
}
