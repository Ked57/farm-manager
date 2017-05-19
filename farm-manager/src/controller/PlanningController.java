package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.jfoenix.controls.JFXDatePicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.DbMgr;

public class PlanningController {

	@FXML
	private ChoiceBox<String> bottelage;
	@FXML
	private ChoiceBox<String> clients;
	@FXML
	private ChoiceBox<String> champs;
	@FXML
	private ChoiceBox<String> CH;
	@FXML
	private JFXDatePicker date;
	
	private DbMgr db;
	private SelecMachinesController selecMachinesController;
	private Client currClient;
	private ObservableList<Client> clientList;
	

	public void initialize() {
		// remplissage de la choicebox bottelage
				ObservableList<String> listBott = FXCollections.observableArrayList("Ronde", "Carr�");
				bottelage.setValue("Ronde");
				bottelage.setItems(listBott);
				

				ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Apr�s-Midi");
				CH.setValue("Matin");
				CH.setItems(listCr);
		
	}
	
	public void init(DbMgr db,SelecMachinesController selecMachinesController) throws ClassNotFoundException, SQLException{
		this.db = db;
		this.selecMachinesController = selecMachinesController;
		this.clientList = db.getClientsList();
		setClients();
		setChamps(db.getChampsList(currClient.getId()));
		
		//Initialisation de la date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate d = LocalDate.now();
		String text = d.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		date.setValue(parsedDate);
		
		
		//Events
		clients.valueProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> list,String lastValue, String newValue){
				//Mise a jour du client s�lectionn�
				for(int i = 0; i < clientList.size(); ++i){
					//Si le nom et le prenom concordent avec la nouvelle valeur
					if((clientList.get(i).getNom()+ " "+clientList.get(i).getPrenom()).equals(newValue)){
						currClient = clientList.get(i);
						try {
							setChamps(db.getChampsList(currClient.getId()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

	// remplissage de la choiceBox de clients
	public void setClients() {

		ObservableList<String> clientStrings = FXCollections.observableArrayList();
		for (int i = 0; i < clientList.size(); ++i) {
			clientStrings.add(clientList.get(i).getNom() + " " + clientList.get(i).getPrenom());
		}
		clients.setItems(clientStrings);
		clients.setValue(clientList.get(0).getNom() + " " + clientList.get(0).getPrenom());
		currClient = clientList.get(0);
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
	
	public void selecMachinesOnAction() throws IOException, ClassNotFoundException, SQLException{
		BorderPane dialog = FXMLLoader.load(getClass().getResource("/view/SelecMachines.fxml"));
		Scene scene = new Scene(dialog,400,400);
    	Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("S�lection des machines");
        stage.show();
        selecMachinesController.initSelecMachines(db);
	}

}
