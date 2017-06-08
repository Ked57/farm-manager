package model;

public class Machine {
	private int id;
	private int idMoi;
	private int idTract;
	private int idBot;
	
	public Machine(int id,int idMoi,int idTract, int idBot){
		this.id = id;
		this.idMoi = idMoi;
		this.idTract = idTract;
		this.idBot = idBot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMoi() {
		return idMoi;
	}

	public void setIdMoi(int idMoi) {
		this.idMoi = idMoi;
	}

	public int getIdTract() {
		return idTract;
	}

	public void setIdTract(int idTract) {
		this.idTract = idTract;
	}

	public int getIdBot() {
		return idBot;
	}

	public void setIdBot(int idBot) {
		this.idBot = idBot;
	}
	
}
