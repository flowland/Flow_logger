package application;

public class LogEntry {
	private String date, goal;
	private int start, stop;

	public LogEntry() {
		this.date = "";
		this.goal = "";
		this.start = 0;
		this.stop = 0;
	}

	public LogEntry(String date, String goal, int start, int stop) {
		this.date = date;
		this.goal = goal;
		this.start = start;
		this.stop = stop;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

}
