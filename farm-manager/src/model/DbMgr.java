package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbMgr {

	private String host;
	private String port;
	private String dbName;
	private String user;
	private String passw;
	private java.sql.Connection cn;
	private java.sql.Statement st;
	private java.sql.ResultSet rs;
	private boolean Connected;
	

	public DbMgr(String host, String user, String passw, String port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.user = user;
		this.passw = passw;
		cn = null;
		st = null;
		if (host != null && user != null && passw != null && port != null && dbName != null && !host.equals("default") && !user.equals("default") && !passw.equals("default") && !port.equals("default") && dbName.equals("default")) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, user, passw);
				st = cn.createStatement();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			Connected = true;
		}else Connected = false;
	}
	
	public boolean isConnected() {
		return Connected;
	}

	public void select(String[] fields, String table, String[] conditions) {
		String request = "SELECT ";

		for (int i = 0; i < fields.length; ++i) {
			request += fields[i];
			if (i < fields.length - 1)
				request += ",";
		}

		request += " FROM " + table + " ";

		if (conditions != null) {
			for (int i = 0; i < conditions.length; ++i) {
				request += conditions[i];
				if (i < conditions.length - 1)
					request += ",";
			}
		}
		try {
			rs = st.executeQuery(request);
			while (rs.next()) {
				for (int i = 0; i < fields.length; ++i) {
					System.out.println(rs.getString(fields[i]));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
