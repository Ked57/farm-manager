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
		connect();
	}

	public void connect() throws ClassNotFoundException, SQLException {
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

	public void checkConnected() throws ClassNotFoundException, SQLException {
		if (cn.isClosed())
			connect();
	}

	/* ====== CLIENT ====== */
	public ObservableList<Client> getClientsList() throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT * FROM Client";
			rs = st.executeQuery(request);
		}
		ObservableList<Client> clientList = FXCollections.observableArrayList();
		while (rs.next()) {
			clientList
					.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		return clientList;
	}

	/* ====== CHAMPS ====== */
	public ObservableList<Champs> getChampsList() throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Id_Ch,Nom_Cli,Prenom_Cli,Adr_Ch,Surf_Ch,Nom_TypCult FROM Champ JOIN Client ON Champ.Id_Cli=Client.Id_Cli JOIN TypeCulture ON Champ.Id_TypCult=TypeCulture.Id_TypCult;";
			rs = st.executeQuery(request);
		}
		ObservableList<Champs> champsList = FXCollections.observableArrayList();
		while (rs.next()) {
			champsList.add(new Champs(rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getString(4),
					rs.getInt(5), rs.getString(6), getPointsListForAChamps(rs.getInt(1))));
		}
		return champsList;
	}

	public ObservableList<Champs> getChampsList(int clientId) throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Id_Ch,Nom_Cli,Prenom_Cli,Adr_Ch,Surf_Ch,Nom_TypCult FROM Champ JOIN Client ON Champ.Id_Cli=Client.Id_Cli JOIN TypeCulture ON Champ.Id_TypCult=TypeCulture.Id_TypCult"
					+ " WHERE Client.Id_Cli=" + clientId + ";";
			rs = st.executeQuery(request);
		}
		ObservableList<Champs> champsList = FXCollections.observableArrayList();
		while (rs.next()) {
			champsList.add(new Champs(rs.getInt(1), rs.getString(2) + " " + rs.getString(3), rs.getString(4),
					rs.getInt(5), rs.getString(6), getPointsListForAChamps(rs.getInt(1))));
		}
		return champsList;
	}

	public ObservableList<Point> getPointsListForAChamps(int champsId) throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Id_Point,Lat_Point,Long_Point FROM Point WHERE Id_Ch = " + champsId;
			rs = st.executeQuery(request);
		}
		ObservableList<Point> pointsList = FXCollections.observableArrayList();
		while (rs.next()) {
			pointsList.add(new Point(rs.getInt(1), rs.getFloat(2), rs.getFloat(3)));
		}
		return pointsList;
	}

	/* ====== MOISSONNEUSE ====== */
	public ObservableList<Moissonneuse> getMoissonneuseList() throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Id_Moi,Nom_Marq,Nom_ModMoi,LarCou_Moi,ConsRoute_ModMoi,ConsFonc_ModMoi,CapaRes_ModMoi,TaTrem_ModMoi,LargRou_ModMoi,Haut_ModMoi,Poids_ModMoi,Etat_Moi"
					+ " FROM Moissonneuse JOIN ModeleMoissonneuse ON Moissonneuse.Id_ModMoi=ModeleMoissonneuse.Id_ModMoi JOIN Marque ON ModeleMoissonneuse.Id_Marq=Marque.Id_Marq;";
			rs = st.executeQuery(request);
		}
		ObservableList<Moissonneuse> moissonneuseList = FXCollections.observableArrayList();
		while (rs.next()) {
			moissonneuseList.add(new Moissonneuse(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
					rs.getFloat(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getFloat(10),
					rs.getFloat(11), rs.getInt(12)));
		}
		return moissonneuseList;
	}

	public ObservableList<Moissonneuse> getMoissonneuseList(ObservableList<Integer> ids)
			throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "";
			if (ids.size() > 0) {
				request = "SELECT Id_Moi,Nom_Marq,Nom_ModMoi,LarCou_Moi,ConsRoute_ModMoi,ConsFonc_ModMoi,CapaRes_ModMoi,TaTrem_ModMoi,LargRou_ModMoi,Haut_ModMoi,Poids_ModMoi,Etat_Moi"
						+ " FROM Moissonneuse JOIN ModeleMoissonneuse ON Moissonneuse.Id_ModMoi=ModeleMoissonneuse.Id_ModMoi JOIN Marque ON ModeleMoissonneuse.Id_Marq=Marque.Id_Marq WHERE Id_Moi NOT IN (";
				for (int i = 0; i < ids.size(); ++i) {
					if (i < ids.size() - 1)
						request += (ids.get(i) + ",");
					else
						request += ids.get(i) + ")";
				}
			}else{
				request = "SELECT Id_Moi,Nom_Marq,Nom_ModMoi,LarCou_Moi,ConsRoute_ModMoi,ConsFonc_ModMoi,CapaRes_ModMoi,TaTrem_ModMoi,LargRou_ModMoi,Haut_ModMoi,Poids_ModMoi,Etat_Moi"
						+ " FROM Moissonneuse JOIN ModeleMoissonneuse ON Moissonneuse.Id_ModMoi=ModeleMoissonneuse.Id_ModMoi JOIN Marque ON ModeleMoissonneuse.Id_Marq=Marque.Id_Marq";
			}

			System.out.println(request);
			rs = st.executeQuery(request);
		}
		ObservableList<Moissonneuse> moissonneuseList = FXCollections.observableArrayList();
		while (rs.next()) {
			moissonneuseList.add(new Moissonneuse(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4),
					rs.getFloat(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getFloat(10),
					rs.getFloat(11), rs.getInt(12)));
		}
		return moissonneuseList;
	}

	/* ====== TRACTEURS ====== */
	public ObservableList<Tracteur> getTracteurList() throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Nom_Marq,Nom_ModTract,Cap_Tract,Etat_Tract FROM Tracteur JOIN ModeleTracteur ON Tracteur.Id_ModTract=ModeleTracteur.Id_ModTract JOIN"
					+ " Marque ON ModeleTracteur.Id_Marq=Marque.Id_Marq;";
			rs = st.executeQuery(request);
		}
		ObservableList<Tracteur> tracteurList = FXCollections.observableArrayList();
		while (rs.next()) {
			tracteurList.add(new Tracteur(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
		}
		return tracteurList;
	}

	/* ====== BOTTELEUSE ====== */
	public ObservableList<Botteleuse> getBotteleuseList() throws SQLException, ClassNotFoundException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Nom_Marq,Nom_ModBot,Etat_Bot FROM Botteleuse JOIN ModeleBotteleuse ON Botteleuse.Id_ModBot=ModeleBotteleuse.Id_ModBot"
					+ " JOIN Marque ON ModeleBotteleuse.Id_Marq=Marque.Id_Marq;";
			rs = st.executeQuery(request);
		}
		ObservableList<Botteleuse> botteleuseList = FXCollections.observableArrayList();
		while (rs.next()) {
			botteleuseList.add(new Botteleuse(rs.getString(1), rs.getString(2), rs.getInt(3)));
		}
		return botteleuseList;
	}

	public ObservableList<Integer> getMoissonneuseForDay(String date) throws ClassNotFoundException, SQLException {
		checkConnected();
		if (Connected) {
			String request = "SELECT Machine.Id_Moi FROM RecolteMachine JOIN Recolte ON RecolteMachine.Id_Rec=Recolte.Id_Rec "
					+ "JOIN Machine ON RecolteMachine.Id_Mach=Machine.Id_Mach "
					+ "JOIN Moissonneuse ON Machine.Id_Moi=Moissonneuse.Id_Moi " + "WHERE Recolte.Date_Rec='" + date
					+ "'";
			rs = st.executeQuery(request);
		}
		ObservableList<Integer> ids = FXCollections.observableArrayList();
		while (rs.next()) {
			ids.add(rs.getInt(1));
		}
		return ids;
	}

	public boolean isConnected() {
		return Connected;
	}
}
