package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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
    public void start(Stage Stage) throws IOException, ClassNotFoundException, SQLException {
    	//Initialisation du fichier de configuration
    	settings = new SettingsMgr("bin/model/settings.ini");
    	//Initialisation de la base de données
    	db = new DbMgr(settings.getHost(),settings.getUser(),settings.getPassw(),settings.getPort(),settings.getDbName());
    	//On charge le rootLayout et on récupère le controller
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RootLayout.fxml"));
    	rootLayout = loader.load();
    	MenuController menu = loader.getController();
		menu.setDb(db);
 
    	Scene scene = new Scene(rootLayout,800,600);
    	
        primaryStage = Stage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Farm-manager");
        primaryStage.show();
    }

  
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
    	
        launch(args);
        
    }
}