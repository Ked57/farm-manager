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
	
	@FXML private TableView<Tracteur> tractTable;
	
	@FXML private TableColumn<Tracteur,String> tractModeleColumn;
	@FXML private TableColumn<Tracteur,Boolean> tractChoixColumn;
	
	@FXML private TableView<Botteleuse> bottTable;
	
	@FXML private TableColumn<Botteleuse,String> bottModeleColumn;
	@FXML private TableColumn<Botteleuse,Boolean> bottChoixColumn;

	private ObservableList<Moissonneuse> moissDispList;
	
	private ObservableList<Tracteur> tractDispList;
	
	private ObservableList<Botteleuse> bottDispList;
	
	private DataMgr data;

	public SelecMachinesController() {

	}

	public void initialize(){
		moissModeleColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		moissChoixColumn.setCellValueFactory(new PropertyValueFactory<Moissonneuse,Boolean>("choosed"));
		moissChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(moissChoixColumn));
		moissTable.setEditable(true);	
		
		tractModeleColumn.setCellValueFactory(new PropertyValueFactory<Tracteur,String>("modele"));
		tractChoixColumn.setCellValueFactory(new PropertyValueFactory<Tracteur,Boolean>("choosed"));
		tractChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(tractChoixColumn));
		tractTable.setEditable(true);
		
		bottModeleColumn.setCellValueFactory(new PropertyValueFactory<Botteleuse,String>("modele"));
		bottChoixColumn.setCellValueFactory(new PropertyValueFactory<Botteleuse,Boolean>("choosed"));
		bottChoixColumn.setCellFactory(CheckBoxTableCell.forTableColumn(bottChoixColumn));
		bottTable.setEditable(true);
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
		
		
		tractDispList = data.getTracteurs(date,fourchette,idRec);
		ObservableList<Tracteur> choicestract = data.getTracteurs(idRec);
		System.out.println(tractDispList.size());
		for(int i = 0; i < tractDispList.size(); ++i){
			for(int j = 0; j < choicestract.size(); ++j){
				if(tractDispList.get(i).getId() != 0 && choicestract.get(j).getId() != 0 && tractDispList.get(i).getId() == choicestract.get(j).getId()){
					tractDispList.get(i).setChoosed(true);
				}
			}
		}

        // listeners pour les boolean properties
        for (Tracteur tract : tractDispList) {
        	tract.choosedProperty().addListener((obs, previousState, newState) ->{
                System.out.printf("Changement d'état du choix, previous: "+previousState+", new: "+newState+"\n");
                tract.setChoosed(newState);
                try {
					data.updateTractForRecolte(tract, idRec,tract.isChoosed());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
        }
        tractTable.setItems(tractDispList);
        tractTable.getColumns().clear();
        tractTable.getColumns().addAll(tractModeleColumn,tractChoixColumn);
        
        bottDispList = data.getBotteleuses(date,fourchette,idRec);
		ObservableList<Botteleuse> choicesbott = data.getBotteleuses(idRec);
		System.out.println(bottDispList.size());
		for(int i = 0; i < bottDispList.size(); ++i){
			for(int j = 0; j < choicesbott.size(); ++j){
				if(bottDispList.get(i).getId() != 0 && choicesbott.get(j).getId() != 0 && bottDispList.get(i).getId() == choicesbott.get(j).getId()){
					bottDispList.get(i).setChoosed(true);
				}
			}
		}

        // listeners pour les boolean properties
        for (Botteleuse bott : bottDispList) {
        	bott.choosedProperty().addListener((obs, previousState, newState) ->{
                System.out.printf("Changement d'état du choix, previous: "+previousState+", new: "+newState+"\n");
                bott.setChoosed(newState);
                try {
					data.updateBottForRecolte(bott, idRec,bott.isChoosed());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
        }
        bottTable.setItems(bottDispList);
        bottTable.getColumns().clear();
        bottTable.getColumns().addAll(bottModeleColumn,bottChoixColumn);
		
	}
}
