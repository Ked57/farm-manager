package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private BorderPane centerLayout;

    @Override
    public void start(Stage Stage) {
    	try {
			centerLayout = FXMLLoader.load(getClass().getResource("../view/Accueil.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Scene scene = new Scene(centerLayout,800,600);

        primaryStage = Stage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Farm-simulateur");
        primaryStage.show();
    }

  
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}