package controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import model.Botteleuse;
import model.DbMgr;
import model.Moissonneuse;
import model.Tracteur;

public class SelecMachinesController {

	@FXML private VBox moissVBox;
	@FXML private VBox tractVBox;
	@FXML private VBox bottVBox;

	private DbMgr db;

	private ObservableList<JFXCheckBox> moissCheckBoxList;
	private ObservableList<JFXCheckBox> tractCheckBoxList;
	private ObservableList<JFXCheckBox> bottCheckBoxList;

	private ObservableList<Moissonneuse> moissList;
	private ObservableList<Tracteur> tractList;
	private ObservableList<Botteleuse> bottList;

	public SelecMachinesController() {

	}

	public void initialize(){
		
	}

	public void initSelecMachines(DbMgr db) throws ClassNotFoundException, SQLException {
		this.db = db;
		
		
		
		moissList = db.getMoissonneuseList();
		tractList = db.getTracteurList();
		bottList = db.getBotteleuseList();
		//initialisation des ObservableList
		moissCheckBoxList = FXCollections.observableArrayList();
		tractCheckBoxList = FXCollections.observableArrayList();
		bottCheckBoxList = FXCollections.observableArrayList();
		
		for (int i = 0; i < moissList.size(); ++i) {
			if (moissList.get(i).getEtat().equals("Disponible"))
				moissCheckBoxList.add(new JFXCheckBox(moissList.get(i).getModele()));
		}

		for (int i = 0; i < tractList.size(); ++i) {
			if (tractList.get(i).getEtat().equals("Disponible"))
				tractCheckBoxList.add(new JFXCheckBox(tractList.get(i).getModele()));
		}

		for (int i = 0; i < bottList.size(); ++i) {
			if (bottList.get(i).getEtat().equals("Disponible"))
				bottCheckBoxList.add(new JFXCheckBox(bottList.get(i).getModele()));
		}
		moissVBox.getChildren().addAll(moissCheckBoxList);
		tractVBox.getChildren().addAll(tractCheckBoxList);
		bottVBox.getChildren().addAll(bottCheckBoxList);
		System.out.println(moissVBox.getChildren().toString());
	}
}
