package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Champs;
import model.Client;

public class PlanningController {

	@FXML
	ChoiceBox<String> bottelage;
	@FXML
	ChoiceBox<String> clients;
	@FXML
	ChoiceBox<String> champs;
	@FXML
	ChoiceBox<String> CH;
	
	

	public void initialize() {
		// remplissage de la choicebox bottelage
		ObservableList<String> listBott = FXCollections.observableArrayList("Ronde", "Carré");
		bottelage.setValue("Ronde");
		bottelage.setItems(listBott);
		

		ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Après-Midi");
		CH.setValue("Matin");
		CH.setItems(listCr);
		
		
	}

	// remplissage de la choiceBox de clients
	public void setClients(ObservableList<Client> clientList) {
		ObservableList<String> clientStrings = FXCollections.observableArrayList();
		for (int i = 0; i < clientList.size(); ++i) {
			clientStrings.add(clientList.get(i).getNom() + " " + clientList.get(i).getPrenom());
		}
		clients.setItems(clientStrings);
		clients.setValue(clientList.get(0).getNom() + " " + clientList.get(0).getPrenom());
	}

	public void setChamps(ObservableList<Champs> champsList) {
		if (champsList.size() > 0) {
			ObservableList<String> adresseStrings = FXCollections.observableArrayList();
			for (int i = 0; i < champsList.size(); ++i) {
				adresseStrings.add(champsList.get(i).getAdresse());
			}
			champs.setItems(adresseStrings);
			champs.setValue(champsList.get(0).getAdresse());
		} else {
			ObservableList<String> adresseStrings = FXCollections.observableArrayList();
			adresseStrings.add("Aucun champs");
			champs.setItems(adresseStrings);
			champs.setValue("Aucun champs");
		}
	}
	
	public void selecMachinesOnAction() throws IOException{
		BorderPane dialog = FXMLLoader.load(getClass().getResource("/view/SelecMachines.fxml"));
		Scene scene = new Scene(dialog,400,400);
    	Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sélection des machines");
        stage.show();
	}

}
