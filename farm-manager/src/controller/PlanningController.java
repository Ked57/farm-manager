package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import model.Client;
import model.DbMgr;

public class PlanningController {

	@FXML
	ChoiceBox bottelage;
	@FXML
	ChoiceBox clients;
	@FXML
	ChoiceBox champs;
	@FXML
	ChoiceBox CH;

	public void initialize() {
		//remplissage de la choicebox bottelage
		ObservableList<String> listBott = FXCollections.observableArrayList("Ronde", "Carré");
		bottelage.setValue("Ronde");
		bottelage.setItems(listBott);
		
		ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Après-Midi");
		CH.setValue("Midi");
		CH.setItems(listCr);

		// remplissage de la choiceBox de clients
		
//		ObservableList<String> listCli = FXCollections.observableArrayList();
//		for (Client cli : ClientsController.clientList) {
//			listCli.add(cli.getNom());
//
//		}
//		clients.setItems(listCli);
		
		
	}

}
