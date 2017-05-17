package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.Machine;
import model.Moissonneuse;

public class MachinesController {
	@FXML
	private TableView<Moissonneuse> moissoneusesTable;
	@FXML
	private TableView<Machine> tracteursTable;
	@FXML
	private TableView<Machine> botteleusesTable;
	//Moissoneuse
	private TableColumn<Moissonneuse,String> marqueCol;
	private TableColumn<Moissonneuse,String> modeleCol;
	private TableColumn<Moissonneuse,String> largCoupeCol;
	private TableColumn<Moissonneuse,String> consoHeureRouteCol;
	private TableColumn<Moissonneuse,String> consoHeureFoncCol;
	private TableColumn<Moissonneuse,String> capaResCol;
	private TableColumn<Moissonneuse,String> tailleTremCol;
	private TableColumn<Moissonneuse,String> largRouteCol;
	private TableColumn<Moissonneuse,String> hauteurCol;
	private TableColumn<Moissonneuse,String> poidsCol;
	private TableColumn<Moissonneuse,String> etatCol;
	
	private ObservableList<Moissonneuse> moissonneusesList;
	
	public MachinesController() {
		marqueCol =  new TableColumn<>("Marque");
		marqueCol.setPrefWidth(300);
		marqueCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("marque"));
		
		modeleCol =  new TableColumn<>("Modele");
		modeleCol.setPrefWidth(300);
		modeleCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("modele"));
		
		largCoupeCol =  new TableColumn<>("largCoupe");
		largCoupeCol.setPrefWidth(300);
		largCoupeCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("Largeur de coupe"));
		
		consoHeureRouteCol =  new TableColumn<>("Conso/h sur route");
		consoHeureRouteCol.setPrefWidth(300);
		consoHeureRouteCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("consoRoute"));
		
		consoHeureFoncCol =  new TableColumn<>("Conso/h en fonctionnement");
		consoHeureFoncCol.setPrefWidth(300);
		consoHeureFoncCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("consoFonct"));
		
		capaResCol =  new TableColumn<>("Capacité du réservoir");
		capaResCol.setPrefWidth(300);
		capaResCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("capaRes"));
		
		tailleTremCol =  new TableColumn<>("Taille du trémis");
		tailleTremCol.setPrefWidth(300);
		tailleTremCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("tailleTrem"));
		
		largRouteCol =  new TableColumn<>("Largeur sur route");
		largRouteCol.setPrefWidth(300);
		largRouteCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("largRoute"));
		
		hauteurCol =  new TableColumn<>("Hauteur");
		hauteurCol.setPrefWidth(300);
		hauteurCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("hauteur"));
		
		poidsCol =  new TableColumn<>("Poids");
		poidsCol.setPrefWidth(300);
		poidsCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("poids"));
		
		etatCol =  new TableColumn<>("Etat");
		etatCol.setPrefWidth(300);
		etatCol.setCellValueFactory(new PropertyValueFactory<Moissonneuse,String>("etat"));
	}
	
	public void setMoissonneuse(ObservableList<Moissonneuse> moissonneusesList){
		this.moissonneusesList = moissonneusesList;
		moissoneusesTable.setItems(this.moissonneusesList);
		moissoneusesTable.getColumns().clear();
		moissoneusesTable.getColumns().clear();
		moissoneusesTable.getColumns().addAll(marqueCol,modeleCol,largCoupeCol,consoHeureRouteCol,consoHeureFoncCol,capaResCol,tailleTremCol,largRouteCol,hauteurCol,poidsCol,etatCol);
	}
}
