package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import model.Champs;
import model.Client;

public class PlanningController {

	@FXML
	ChoiceBox<String> bottelage;
	@FXML
	ChoiceBox<String> clients;
	@FXML
	ChoiceBox<Champs> champs;
	@FXML
	ChoiceBox<String> CH;

	public void initialize() {
		//remplissage de la choicebox bottelage
		ObservableList<String> listBott = FXCollections.observableArrayList("Ronde", "Carré");
		bottelage.setValue("Ronde");
		bottelage.setItems(listBott);
		
		ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Après-Midi");
		CH.setValue("Matin");
		CH.setItems(listCr);
	}
	
	
	// remplissage de la choiceBox de clients
	public void setClients(ObservableList<Client> clientList){
		ObservableList<String> clientStrings = FXCollections.observableArrayList();
		for(int i = 0; i < clientList.size(); ++i){
			clientStrings.add(clientList.get(i).getNom()+" "+clientList.get(i).getPrenom());
		}
		clients.setItems(clientStrings);
		clients.setValue(clientList.get(0).getNom()+" "+clientList.get(0).getPrenom());
	}	

}
