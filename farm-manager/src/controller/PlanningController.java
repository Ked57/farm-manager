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
import javafx.util.converter.LocalDateStringConverter;
import model.Bottelage;
import model.Champs;
import model.Client;
import model.DataMgr;
import model.DbMgr;
import model.Moissonneuse;
import model.Recolte;
import model.Transport;

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
	private ChoiceBox<String> transport;
	@FXML
	private JFXDatePicker date;
	@FXML
	private JFXDatePicker dateChanger;
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

	private Recolte selectedItem;

	private DataMgr data;
	private SelecMachinesController selecMachinesController;
	private Client currClient;
	private ObservableList<Client> clientList;
	private ObservableList<Champs> currChampsList;
	private ObservableList<Recolte> recoltList;
	private LocalDate parsedDate;

	public void initialize() {		

		ObservableList<String> listCr = FXCollections.observableArrayList("Matin", "Après-Midi");
		CH.setValue("Matin");
		CH.setItems(listCr);

		adresseMatinCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("adresse"));
		adresseMatinCol.setPrefWidth(150);
		clientMatinCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("nomCli"));

		adresseAMCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("adresse"));
		adresseAMCol.setPrefWidth(150);
		clientAMCol.setCellValueFactory(new PropertyValueFactory<Recolte, String>("nomCli"));
	}

	public void init(DataMgr data, SelecMachinesController selecMachinesController)
			throws ClassNotFoundException, SQLException {
		this.data = data;
		this.selecMachinesController = selecMachinesController;
		this.selectedItem = null;
		this.clientList = data.getClients();
		setClients();
		setChamps(data.getChamps(currClient.getId()));
		
		//Les ChoiceBox de bottelages et de transports sont créées à partir de la base de données
		ObservableList<Bottelage> tempBott = data.getBottelages();
		ObservableList<String> listBott = FXCollections.observableArrayList();
		for(Bottelage bott : tempBott){
			listBott.add(bott.getNom());
		}
		bottelage.setValue(listBott.get(0));
		bottelage.setItems(listBott);
		
		ObservableList<Transport> tempTransp = data.getTransports();
		ObservableList<String> listTransp = FXCollections.observableArrayList();
		for(Transport transp : tempTransp){
			listTransp.add(transp.getNom());
		}
		transport.setValue(listTransp.get(0));
		transport.setItems(listTransp);

		// Initialisation de la date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate d = LocalDate.now();
		String text = d.format(formatter);
		parsedDate = LocalDate.parse(text, formatter);
		date.setValue(parsedDate);
		updatePlanning(date.getValue().toString());
		recoltList = data.getRecoltes(date.getValue().toString());
		if (recoltList.size() > 0) {
			selectedItem = recoltList.get(0);
			updateLeftPanel(selectedItem);
			currChampsList = data.getChamps(selectedItem.getIdCli());
			setChamps(currChampsList);
		} else
			selectedItem = new Recolte(0, "", 0, 0.1f, 0.1f, 0.1f, 0, "", "", 0, "", 0, "", "",1);

		tableMatin.getColumns().clear();
		tableMatin.getColumns().addAll(adresseMatinCol, clientMatinCol);

		tableMatin.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (!tableMatin.getSelectionModel().isEmpty()
					&& !selectedItem.equals(tableMatin.getSelectionModel().getSelectedItem())) {
				selectedItem = tableMatin.getSelectionModel().getSelectedItem();
				try {
					updateLeftPanel(selectedItem);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		tableAM.getColumns().clear();
		tableAM.getColumns().addAll(adresseAMCol, clientAMCol);
		tableAM.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

			if (!tableAM.getSelectionModel().isEmpty()
					&& selectedItem != tableAM.getSelectionModel().getSelectedItem()) {
				selectedItem = tableAM.getSelectionModel().getSelectedItem();
				try {
					updateLeftPanel(selectedItem);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Events
		clients.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				// Mise a jour du client sélectionné
				for (int i = 0; i < clientList.size(); ++i) {
					// Si le nom et le prenom concordent avec la nouvelle valeur
					if ((clientList.get(i).getNom() + " " + clientList.get(i).getPrenom()).equals(newValue)) {
						currClient = clientList.get(i);
						currChampsList = data.getChamps(currClient.getId());
						setChamps(currChampsList);
						// Si le nom est différent de celui déjà enregistré pour
						// la récolte
						if (selectedItem != null && !selectedItem.getNomComplet().equals(newValue)) {
							data.update("Recolte", "Id_Rec", selectedItem.getId(), "Id_Cli", currClient.getId());
							data.update("Recolte", "Id_Rec", selectedItem.getId(), "Id_Ch",
									currChampsList.get(0).getId());
							System.out.println("updated clients");
							champs.setValue(currChampsList.get(0).getAdresse());

							try {
								data.syncRecoltes();
								recoltList = data.getRecoltes();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							updatePlanning(selectedItem.getDate());
						}
						break;
					}
				}
			}
		});
		champs.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				if (currChampsList != null && selectedItem != null) {
					for (int i = 0; i < currChampsList.size(); ++i) {
						if (currChampsList.get(i).getAdresse().equals(newValue)) {
							data.update("Recolte", "Id_Rec", selectedItem.getId(), "Id_Ch",
									currChampsList.get(i).getId());
							try {
								data.syncRecoltes();
								recoltList = data.getRecoltes();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							updatePlanning(selectedItem.getDate());
							break;
						}
					}
				}

			}
		});
		date.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> list, LocalDate lastValue, LocalDate newValue) {
				updatePlanning(newValue.toString());
			}
		});
		dateChanger.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> list, LocalDate lastValue, LocalDate newValue) {
				if (selectedItem != null && !selectedItem.getDate().equals(newValue.toString())) {
					selectedItem.setDate(newValue.toString());
					data.update("Recolte", "Id_Rec", selectedItem.getId(), "Date_Rec", selectedItem.getDate());
					try {
						data.syncRecoltes();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					date.setValue(LocalDate.parse(selectedItem.getDate()));
					updatePlanning(selectedItem.getDate());
				}
			}
		});
		CH.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				int fourchette;
				if (newValue.equals("Matin"))
					fourchette = 0;
				else
					fourchette = 1;
				if (selectedItem != null && selectedItem.getFourchette() != fourchette) {
					data.update("Recolte", "Id_Rec", selectedItem.getId(), "Fourchette_Rec", fourchette);
					try {
						data.syncRecoltes();
						recoltList = data.getRecoltes();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					updatePlanning(selectedItem.getDate());
				}
			}
		});
		bottelage.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				if (selectedItem != null && selectedItem.getTypeBottelage() != newValue) {
					ObservableList<Bottelage> bottelages = data.getBottelages();
					for (int i = 0; i < bottelages.size(); ++i) {
						if (bottelages.get(i).getNom().equals(newValue)) {
							data.update("Recolte", "Id_Rec", selectedItem.getId(), "Id_TypeBot",
									bottelages.get(i).getId());
							try {
								data.syncRecoltes();
								recoltList = data.getRecoltes();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							updatePlanning(selectedItem.getDate());
						}
					}
				}
			}
		});
		transport.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				if (selectedItem != null && selectedItem.getNomTransport() != newValue) {
					ObservableList<Transport> transports = data.getTransports();
					for(int i = 0; i < transports.size(); ++i){
						if (transports.get(i).getNom().equals(newValue)) {
							data.update("Recolte", "Id_Rec", selectedItem.getId(), "Id_Trans", transports.get(i).getId());
							try {
								data.syncRecoltes();
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							recoltList = data.getRecoltes();
							updatePlanning(selectedItem.getDate());
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
			if (selectedItem != null) {
				champs.setValue(selectedItem.getAdresse());
			} else
				champs.setValue(champsList.get(0).getAdresse());
		} else {
			ObservableList<String> adresseStrings = FXCollections.observableArrayList();
			adresseStrings.add("Aucun champs");
			champs.setItems(adresseStrings);
			champs.setValue("Aucun champs");
		}
	}

	public void selecMachinesOnAction() throws IOException, ClassNotFoundException, SQLException {
		if (selectedItem != null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelecMachines.fxml"));
			BorderPane dialog = loader.load();
			selecMachinesController = loader.getController();
			Scene scene = new Scene(dialog, 400, 400);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Sélection des machines");
			stage.show();
			selecMachinesController.initSelecMachines(data, selectedItem.getDate(), selectedItem.getFourchette(),selectedItem.getId());
		}
	}

	public void updatePlanning(String day) {
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

		tableAM.setItems(am);

	}

	public void updateLeftPanel(Recolte rec) throws ClassNotFoundException, SQLException {
		clients.setValue(rec.getNomComplet());
		setChamps(data.getChamps(rec.getIdCli()));
		if (rec.getFourchette() <= 0)
			CH.setValue("Matin");
		else
			CH.setValue("Après-Midi");
		bottelage.setValue(rec.getTypeBottelage());

		LocalDate date = LocalDate.parse(rec.getDate());
		dateChanger.setValue(date);
		transport.setValue(rec.getNomTransport());
		System.out.println(rec.getNomTransport());
	}

	public void onCommandeButtonClick() throws ClassNotFoundException, SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate d = LocalDate.now();
		String text = d.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);

		Recolte rec = new Recolte(0, parsedDate.toString(), 0, 0.0f, 0.0f, 0.0f, 1, clientList.get(1).getNom(),
				clientList.get(1).getPrenom(), data.getChamps(1).get(1).getId(), data.getChamps(1).get(1).getAdresse(),
				1, "Rond", "Client",1);
		data.addRecolte(parsedDate.toString(), 1, 1, 1, 1);
		data.syncRecoltes();
		recoltList = data.getRecoltes();
		updatePlanning(parsedDate.toString());
		selectedItem = rec;
	}

	public void onSuppAction() throws ClassNotFoundException, SQLException {
		if (selectedItem != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate d = LocalDate.now();
			String text = d.format(formatter);
			LocalDate parsedDate = LocalDate.parse(text, formatter);

			data.deleteRecolte(selectedItem.getId());
			data.syncRecoltes();
			recoltList = data.getRecoltes();
			updatePlanning(parsedDate.toString());
		}
	}
}
