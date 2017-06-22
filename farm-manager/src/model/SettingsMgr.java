package model;

import java.io.File;

public class SettingsMgr {
	
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String passw;
	private boolean init;
	private FileMgr file;
	private String filename;
	
	public SettingsMgr(String filename) {
		this.filename = filename;
		file = new FileMgr(filename,this);
	}
	
	public void save(){
		file.setSettings(this);
		file.saveSettings(new File(filename));
		System.out.println("settings saved");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public FileMgr getFile() {
		return file;
	}

	public void setFile(FileMgr file) {
		this.file = file;
	}
	
	
}
