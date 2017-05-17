package model;

import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public DbMgr(String host, String user, String passw, String port, String dbName)
			throws ClassNotFoundException, SQLException {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.user = user;
		this.passw = passw;
		cn = null;
		st = null;
		if (host != null && user != null && passw != null && port != null && dbName != null && !host.equals("default")
				&& !user.equals("default") && !passw.equals("default") && !port.equals("default")
				&& !dbName.equals("default")) {
			System.out.println("connected");
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, user, passw);
			st = cn.createStatement();

			Connected = true;
		} else
			Connected = false;

		System.out
				.println("host:" + host + " user:" + user + " passw:" + passw + " port:" + port + " dbName:" + dbName);
	}

	public ObservableList<Client> getClientsList() throws SQLException {
		if (Connected) {
			String request = "SELECT * FROM Client";
			rs = st.executeQuery(request);
		}
		ObservableList<Client> clientList = FXCollections.observableArrayList();
		while(rs.next()){
			clientList.add(new Client(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return clientList;
	}
	
	public ObservableList<Champs> getChampsList() throws SQLException {
		if (Connected) {
			String request = "SELECT Nom_Cli,Prenom_Cli,Adr_Ch,Surf_Ch,Nom_TypCult FROM Champ JOIN Client ON Champ.Id_Cli=Client.Id_Cli JOIN TypeCulture ON Champ.Id_TypCult=TypeCulture.Id_TypCult;";
			rs = st.executeQuery(request);
		}
		ObservableList<Champs> champsList = FXCollections.observableArrayList();
		while(rs.next()){
			champsList.add(new Champs(rs.getString(1)+rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
		}
		return champsList;
	}
	
	public ObservableList<Moissonneuse> getMoissonneuseList() throws SQLException {
		if(Connected){
			String request = "SELECT Nom_Marq,Nom_ModMoi,LarCou_Moi,ConsRoute_ModMoi,ConsFonc_ModMoi,CapaRes_ModMoi,TaTrem_ModMoi,LargRou_ModMoi,Haut_ModMoi,Poids_ModMoi,Etat_Moi"
					+ " FROM Moissonneuse JOIN ModeleMoissonneuse ON Moissonneuse.Id_ModMoi=ModeleMoissonneuse.Id_ModMoi JOIN Marque ON ModeleMoissonneuse.Id_Marq=Marque.Id_Marq;";
			rs = st.executeQuery(request);
		}
		ObservableList<Moissonneuse> moissonneuseList = FXCollections.observableArrayList();
		while(rs.next()){
			moissonneuseList.add(new Moissonneuse(rs.getString(0),rs.getString(1),rs.getFloat(2),rs.getFloat(3),rs.getFloat(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),
					rs.getFloat(8),rs.getFloat(9),rs.getInt(10)));
		}
		return moissonneuseList;
	}

	public boolean isConnected() {
		return Connected;
	}
}
