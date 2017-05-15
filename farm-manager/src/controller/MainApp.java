package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DbMgr;
import model.SettingsMgr;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane centerLayout;
    private BorderPane rootLayout;
    private DbMgr db;
    private SettingsMgr settings;

    @Override
    public void start(Stage Stage) throws IOException {
    	//Initialisation du fichier de configuration
    	settings = new SettingsMgr("bin/model/settings.ini");
    	//Initialisation de la base de données
    	db = new DbMgr(settings.getHost(),settings.getUser(),settings.getPassw(),settings.getPort(),settings.getDbName());    	
    	
    	rootLayout = FXMLLoader.load(getClass().getResource("/view/RootLayout.fxml"));
		centerLayout = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
		rootLayout.setCenter(centerLayout);
    	Scene scene = new Scene(rootLayout,800,600);

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