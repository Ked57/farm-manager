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
    private DbMgr db;
    private SettingsMgr settings;

    @Override
    public void start(Stage Stage) {
    	//Initialisation du fichier de configuration
    	settings = new SettingsMgr("bin/model/settings.ini");
    	//Initialisation de la base de données
    	db = new DbMgr(settings.getHost(),settings.getUser(),settings.getPassw(),settings.getPort(),settings.getDbName());
    	/* EXEMPLE DE SELECT */
    	String[] fields = {"Id_Cli","Nom_Cli","Prenom_Cli","Num_Cli","Adr_Cli"};
    	if(db.isConnected())
    		db.select(fields,"Client",null);
    	
    	
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