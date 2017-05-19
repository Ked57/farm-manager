package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
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

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Client;

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
	
	
	private GoogleMapView mapComponent;
	private GoogleMap map;
	private DirectionsPane directions;
	private ObservableList<Client> clientList;

	public void initialize() {
		initMap();
	}

	public void initMap() {
		mapComponent = new GoogleMapView();
		mapComponent.addMapInializedListener(this);
		mapComponent.setDisableDoubleClick(true);
		mapPane.setCenter(mapComponent);
	}

	public void initAccueil(ObservableList<Client> clientList) {
		this.clientList = clientList;
		if (clientList.size() >= 0) {
			ObservableList<String> clientStrings = FXCollections.observableArrayList();
			for (int i = 0; i < clientList.size(); ++i) {
				clientStrings.add(clientList.get(i).getNom() + " " + clientList.get(i).getPrenom());
			}
			clientChoice.setItems(clientStrings);
			clientChoice.setValue(clientStrings.get(0));
		}
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
		LatLong center = new LatLong(47.969139, -1.446333);
		mapComponent.addMapReadyListener(() -> {
			// This call will fail unless the map is completely ready.
			checkCenter(center);
		});

		MapOptions options = new MapOptions();
		options.center(center).zoom(10).overviewMapControl(false).panControl(false).rotateControl(false)
				.scaleControl(false).streetViewControl(false).zoomControl(true).mapType(MapTypeIdEnum.TERRAIN);

		map = mapComponent.createMap(options);
		

		map.setHeading(123.2);

	}

	private void checkCenter(LatLong center) {
		// System.out.println("Testing fromLatLngToPoint using: " + center);
		// Point2D p = map.fromLatLngToPoint(center);
		// System.out.println("Testing fromLatLngToPoint result: " + p);
		// System.out.println("Testing fromLatLngToPoint expected: " +
		// mapComponent.getWidth()/2 + ", " + mapComponent.getHeight()/2);
	}

}
