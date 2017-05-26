package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Botteleuse;
import model.Client;
import model.DataMgr;
import model.Moissonneuse;
import model.Tracteur;

public class MachinesController {
	@FXML
	private TableView<Moissonneuse> moissoneusesTable;
	@FXML
	private TableView<Tracteur> tracteursTable;
	@FXML
	private TableView<Botteleuse> botteleusesTable;
	//Moissoneuse
	@FXML private TableColumn<Moissonneuse,String> marqueCol;
	@FXML private TableColumn<Moissonneuse,String> modeleCol;
	@FXML private TableColumn<Moissonneuse,String> largCoupeCol;
	@FXML private TableColumn<Moissonneuse,String> consoHeureRouteCol;
	@FXML private TableColumn<Moissonneuse,String> consoHeureFoncCol;
	@FXML private TableColumn<Moissonneuse,String> capaResCol;
	@FXML private TableColumn<Moissonneuse,String> tailleTremCol;
	@FXML private TableColumn<Moissonneuse,String> largRouteCol;
	@FXML private TableColumn<Moissonneuse,String> hauteurCol;
	@FXML private TableColumn<Moissonneuse,String> poidsCol;
	@FXML private TableColumn<Moissonneuse,String> etatCol;
	//Tracteur
	@FXML private TableColumn<Tracteur,String> marqueTractCol;
	@FXML private TableColumn<Tracteur,String> modeleTractCol;
	@FXML private TableColumn<Tracteur,Float> capaTractCol;
	@FXML private TableColumn<Tracteur,String> etatTractCol;
	//Botteleuse
	@FXML private TableColumn<Botteleuse,String> marqueBotCol;
	@FXML private TableColumn<Botteleuse,String> modeleBotCol;
	@FXML private TableColumn<Botteleuse,String> typeBotCol;
	@FXML private TableColumn<Botteleuse,String> etatBotCol;
	
	private ObservableList<Moissonneuse> moissonneusesList;
	private ObservableList<Tracteur> tracteursList;
	private ObservableList<Botteleuse> botteleusesList;
	
	private DataMgr data;
	
	public MachinesController() {
		
	}
	
	public void initialize(){
		marqueCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("marque"));
		modeleCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		largCoupeCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("largCoupe"));
		consoHeureRouteCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("consoRoute"));
		consoHeureFoncCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("consoFonct"));
		capaResCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("capaRes"));
		tailleTremCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("tailleTrem"));
		largRouteCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("largRoute"));
		hauteurCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("hauteur"));
		poidsCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("poids"));
		etatCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("etat"));
		
		marqueTractCol.setCellValueFactory(new PropertyValueFactory<Tracteur,String>("marque"));
		modeleTractCol.setCellValueFactory(new PropertyValueFactory<Tracteur,String>("modele"));
		capaTractCol.setCellValueFactory(new PropertyValueFactory<Tracteur,Float>("capacite"));
		etatTractCol.setCellValueFactory(new PropertyValueFactory<Tracteur,String>("etat"));
		
		marqueBotCol.setCellValueFactory(new PropertyValueFactory<Botteleuse,String>("marque"));
		modeleBotCol.setCellValueFactory(new PropertyValueFactory<Botteleuse,String>("modele"));
		typeBotCol.setCellValueFactory(new PropertyValueFactory<Botteleuse,String>("type"));
		etatBotCol.setCellValueFactory(new PropertyValueFactory<Botteleuse,String>("etat"));
	}
	public void setData(DataMgr data){
		this.data = data;
	}
	
	public void setMoissonneuses(){
		this.moissonneusesList = data.getMoissonneuses();
		moissoneusesTable.setItems(this.moissonneusesList);
		moissoneusesTable.getColumns().clear();
		moissoneusesTable.getColumns().addAll(marqueCol,modeleCol,largCoupeCol,consoHeureRouteCol,consoHeureFoncCol,capaResCol,tailleTremCol,largRouteCol,hauteurCol,poidsCol,etatCol);
	}
	
	public void setTracteurs(){
		this.tracteursList = data.getTracteurs();
		tracteursTable.setItems(this.tracteursList);
		tracteursTable.getColumns().clear();
		tracteursTable.getColumns().addAll(marqueTractCol,modeleTractCol,capaTractCol,etatTractCol);
	}
	
	public void setBotteleuses(){
		this.botteleusesList = data.getBotteleuses();
		botteleusesTable.setItems(this.botteleusesList);
		botteleusesTable.getColumns().clear();
		botteleusesTable.getColumns().addAll(marqueBotCol,modeleBotCol,typeBotCol,etatBotCol);
	}
}
