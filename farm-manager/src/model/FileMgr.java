package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileMgr {

	private FileInputStream in;
	private FileOutputStream out;
	private SettingsMgr settings;
	private boolean noInitFile;

	public FileMgr(String filename, SettingsMgr settings) {
		File file = new File(filename);
		try {
			file.createNewFile();// Au cas ou le fichier n'existe pas
			if (file.length() <= 0){
				createSettingsFile(file);
				noInitFile = true;
			}else noInitFile = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.settings = settings;
		if(!noInitFile)
			initSettings();
	}

	public void initSettings() {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				set(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set(String line) {
		String[] args = line.split(" = ");
		switch (args[0]) {
		case "host":
			settings.setHost(args[1]);
			break;
		case "user":
			settings.setUser(args[1]);
			break;
		case "passw":
			settings.setPassw(args[1]);
			break;
		case "port":
			settings.setPort(args[1]);
			break;
		case "dbName":
			settings.setDbName(args[1]);
			break;
		}
	}

	public void createSettingsFile(File file) {
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String content = "host = default\nuser = default\npassw = default\nport = default\ndbName = default";
		byte[] contentInBytes = content.getBytes();
		try {
			out.write(contentInBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveSettings(File file){
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String content = "host = "+settings.getHost()+"\nuser = "+settings.getUser()+"\npassw = "+settings.getPassw()
						+"\nport = "+settings.getPort()+"\ndbName = "+settings.getDbName();
		byte[] contentInBytes = content.getBytes();
		try {
			out.write(contentInBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSettings(SettingsMgr settings) {
		this.settings = settings;
	}
	
}
