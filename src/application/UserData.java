package application;

public class UserData {
	private String userName, regnr;
	
	
	public UserData(String userName, String regnr) {
		this.userName = userName;
		this.regnr = regnr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String regnr) {
		this.regnr = regnr;
	}
}
