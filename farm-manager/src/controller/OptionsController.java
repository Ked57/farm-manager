package controller;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import model.SettingsMgr;

public class OptionsController {
	
	private SettingsMgr settings;
	@FXML
	private JFXTextField hostText;
	@FXML
	private JFXTextField userText;
	@FXML
	private JFXTextField passwText;
	@FXML
	private JFXTextField portText;
	@FXML
	private JFXTextField dbText;
	
	public OptionsController(){
		
	}
	
	public void initialize(){
		
	}
	
	public void init(SettingsMgr settings){
		this.settings = settings;
		hostText.setText(settings.getHost());
		userText.setText(settings.getUser());
		passwText.setText(settings.getPassw());
		portText.setText(settings.getPort());
		dbText.setText(settings.getDbName());
	}
	
	@FXML
	public void onValiderClick(){
		settings.setHost(hostText.getText());
		settings.setUser(userText.getText());
		settings.setPassw(passwText.getText());
		settings.setPort(portText.getText());
		settings.setDbName(dbText.getText());
		settings.save();
	}
}
