package controller;

import java.sql.SQLException;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationResult;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationStatus;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Champs;
import model.Client;
import model.DbMgr;
import netscape.javascript.JSObject;

public class AccueilController implements MapComponentInitializedListener, ElevationServiceCallback,
		GeocodingServiceCallback, DirectionsServiceCallback {

	@FXML
	private BorderPane mapPane;
	@FXML
	private ChoiceBox<String> clientChoice;
	@FXML
	private Label proprietaire;
	@FXML
	private Label culture;
	@FXML
	private Label surface;
	@FXML
	private Label nbHeures;
	@FXML
	private Label nbTracteurs;
	@FXML
	private Label nbTonnes;

	private MapOptions options;
	private LatLong center;
	private GoogleMapView mapComponent;
	private GoogleMap map;
	private DirectionsPane directions;

	private ObservableList<Client> clientList;
	private ObservableList<Champs> champsList;
	private Client clientChoosed;
	private DbMgr db;

	private boolean initialized;

	public void initialize() {
		initialized = false;
		initMap();
	}

	public void initMap() {
		mapComponent = new GoogleMapView();
		mapComponent.addMapInializedListener(this);
		mapComponent.setDisableDoubleClick(true);
		mapPane.setCenter(mapComponent);
	}

	public void initAccueil(ObservableList<Client> clientList, DbMgr db) throws ClassNotFoundException, SQLException {
		this.db = db;
		// Récupération de la liste des clients
		this.clientList = clientList;
		if (clientList.size() >= 0) {
			ObservableList<String> clientStrings = FXCollections.observableArrayList();
			for (int i = 0; i < clientList.size(); ++i) {
				clientStrings.add(clientList.get(i).getNom() + " " + clientList.get(i).getPrenom());
			}
			// Initilisation
			clientChoice.setItems(clientStrings);
			clientChoice.setValue(clientStrings.get(0));
			clientChoosed = clientList.get(0);
			champsList = db.getChampsList(clientChoosed.getId());
			setClientProperties();

		}
		// Events
		clientChoice.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> list, String lastValue, String newValue) {
				// Mise a jour du client sélectionné
				for (int i = 0; i < clientList.size(); ++i) {
					// Si le nom et le prenom concordent avec la nouvelle valeur
					if ((clientList.get(i).getNom() + " " + clientList.get(i).getPrenom()).equals(newValue)) {
						clientChoosed = clientList.get(i);
						setClientProperties();
						try {
							clearShapes();
							initChamps();
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
	}

	@Override
	public void directionsReceived(DirectionsResult arg0, DirectionStatus arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void geocodedResultsReceived(GeocodingResult[] arg0, GeocoderStatus arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void elevationsReceived(ElevationResult[] arg0, ElevationStatus arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mapInitialized() {
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(3000);
				System.out.println("Calling showDirections from Java");
				Platform.runLater(() -> mapComponent.getMap().hideDirectionsPane());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		t.start();
		// Once the map has been loaded by the Webview, initialize the map
		// details.
		center = new LatLong(47.969139, -1.446333);
		mapComponent.addMapReadyListener(() -> {
			// This call will fail unless the map is completely ready.
			checkCenter(center);
		});

		options = new MapOptions();
		options.center(center).zoom(11).overviewMapControl(false).panControl(false).rotateControl(false)
				.scaleControl(false).streetViewControl(false).zoomControl(true).mapType(MapTypeIdEnum.TERRAIN);

		map = mapComponent.createMap(options);

		map.setHeading(123.2);

		initialized = true;
		
		try {
			initChamps();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkCenter(LatLong center) {
		// System.out.println("Testing fromLatLngToPoint using: " + center);
		// Point2D p = map.fromLatLngToPoint(center);
		// System.out.println("Testing fromLatLngToPoint result: " + p);
		// System.out.println("Testing fromLatLngToPoint expected: " +
		// mapComponent.getWidth()/2 + ", " + mapComponent.getHeight()/2);
	}

	private void setClientProperties() {
		proprietaire.setText(clientChoosed.getNom() + " " + clientChoosed.getPrenom());
	}

	private void setChampsProperties(Champs champs) {
		culture.setText(champs.getTypeCulture());
		surface.setText("" + champs.getSurface());
	}

	private void initChamps() throws ClassNotFoundException, SQLException {
		if (initialized) {
			champsList = db.getChampsList(clientChoosed.getId());
			for (int i = 0; i < champsList.size(); ++i) {

				Champs currChamps = champsList.get(i);
				LatLong[] pAry = new LatLong[currChamps.getPoints().size()];
				for (int j = 0; j < currChamps.getPoints().size(); ++j) {
					pAry[j] = new LatLong(currChamps.getPoints().get(j).getLatitude(),
							currChamps.getPoints().get(j).getLongitude());
				}

				MVCArray pmvc = new MVCArray(pAry);

				currChamps.setPoly(new Polygon(new PolygonOptions().paths(pmvc).strokeColor("blue")
						.fillColor("lightBlue").fillOpacity(0.1).strokeWeight(2).editable(false)));

				map.addMapShape(currChamps.getPoly());

				// Cr�ation d'une popup
				InfoWindowOptions infoOptions = new InfoWindowOptions();
				infoOptions.content("Propriétaire: " + currChamps.getProprietaire() + "<br />" + "Adresse: "
						+ currChamps.getAdresse() + "<br />" + "Surface: " + currChamps.getSurface() + "<br />"
						+ "Type de culture: " + currChamps.getTypeCulture()).position(center);
				infoOptions.position(new LatLong(currChamps.getCenter().getLatitude(),currChamps.getCenter().getLongitude()));

				InfoWindow window = new InfoWindow(infoOptions);
				// Onclick polygon
				map.addUIEventHandler(currChamps.getPoly(), UIEventType.click, (JSObject obj) -> {
					setChampsProperties(currChamps);
					window.open(map);
				});
			}
		}
	}

	public void clearShapes() {
		if (initialized) {
			for (int i = 0; i < champsList.size(); ++i) {
				if (!champsList.get(i).getPoints().isEmpty()){
					Polygon poly = champsList.get(i).getPoly();
					if(poly != null)
						map.removeMapShape(poly);
				}

			}
		}
	}

}
