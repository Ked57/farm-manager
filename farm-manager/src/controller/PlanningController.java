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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Champs;
import model.Client;
import model.DataMgr;
import model.DbMgr;
import model.Moissonneuse;
import model.Recolte;

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
	@FXML
	private TableView<Recolte> tableMatin;
	@FXML
	private TableView<Recolte> tableAM;
	@FXML
	private TableColumn<Recolte, String> adresseMatinCol;
	@FXML
	private TableColumn<Recolte, String> clientMatinCol;
	@FXML
	private TableColumn<Recolte, String> adresseAMCol;
	@FXML
	private TableColumn<Recolte, String> clientAMCol;

	private DataMgr data;
	private SelecMachinesController selecMachinesController;
	private Client currClient;
	private ObservableList<Client> clientList;
	private ObservableList<Recolte> recoltList;

	public void initialize() {
		// remplissage de la choicebox bottelage
		ObservableList<String> listBott = FXCollections.observableArrayList("Rond", "Carré");
		bottelage.setValue("Rond");
		bottelage.setItems(listBott);

		ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Après-Midi");
		CH.setValue("Matin");
		CH.setItems(listCr);

		adresseMatinCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("adresse"));
		clientMatinCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("nomCli"));

		adresseAMCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("adresse"));
		clientAMCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("nomCli"));
	}

	public void init(DataMgr data, SelecMachinesController selecMachinesController)
			throws ClassNotFoundException, SQLException {
		this.data = data;
		this.selecMachinesController = selecMachinesController;
		this.clientList = data.getClients();
		setClients();
		setChamps(data.getChamps(currClient.getId()));

		// Initialisation de la date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate d = LocalDate.now();
		String text = d.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		date.setValue(parsedDate);
		updatePlanning(date.getValue().toString());

		// Events
		clients.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				// Mise a jour du client sélectionné
				for (int i = 0; i < clientList.size(); ++i) {
					// Si le nom et le prenom concordent avec la nouvelle valeur
					if ((clientList.get(i).getNom() + " " + clientList.get(i).getPrenom()).equals(newValue)) {
						currClient = clientList.get(i);
						setChamps(data.getChamps(currClient.getId()));
					}
				}
			}
		});
		date.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> list, LocalDate lastValue, LocalDate newValue) {
				try {
					updatePlanning(newValue.toString());
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
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

	public void selecMachinesOnAction() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelecMachines.fxml"));
		BorderPane dialog = loader.load();
		selecMachinesController = loader.getController();
		Scene scene = new Scene(dialog, 400, 400);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Sélection des machines");
		stage.show();
		selecMachinesController.initSelecMachines(data);
	}

	public void updatePlanning(String day) throws ClassNotFoundException, SQLException {
		recoltList = data.getRecoltes(day);
		ObservableList<Recolte> matin = FXCollections.observableArrayList();
		ObservableList<Recolte> am = FXCollections.observableArrayList(); // am
																			// pour
																			// après
																			// midi

		for (int i = 0; i < recoltList.size(); ++i) {
			if (recoltList.get(i).getFourchette() <= 0) {
				matin.add(recoltList.get(i));
			} else
				am.add(recoltList.get(i));
		}

		tableMatin.setItems(matin);
		tableMatin.getColumns().clear();
		tableMatin.getColumns().addAll(adresseMatinCol, clientMatinCol);

		tableMatin.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			Recolte selectedItem = tableMatin.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				try {
					UpdateLeftPanel(selectedItem);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		tableAM.setItems(am);
		tableAM.getColumns().clear();
		tableAM.getColumns().addAll(adresseAMCol, clientAMCol);
		tableAM.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			Recolte selectedItem = tableAM.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				try {
					UpdateLeftPanel(selectedItem);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public void UpdateLeftPanel(Recolte rec) throws ClassNotFoundException, SQLException {
		clients.setValue(rec.getNomCli() + " " + rec.getPrenomCli());
		setChamps(data.getChamps(rec.getIdCli()));
		if (rec.getFourchette() <= 0)
			CH.setValue("Matin");
		else
			CH.setValue("Après-Midi");
		bottelage.setValue(rec.getTypeBottelage());
	}
}
