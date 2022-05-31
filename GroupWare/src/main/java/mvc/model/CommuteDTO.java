package mvc.model;

public class CommuteDTO {
	private int commute_log;
	private String number;
	private String start_time;
	private String end_time;
	private boolean whether_commute;
	
	
	public int getCommute_log() {
		return commute_log;
	}
	public void setCommute_log(int commute_log) {
		this.commute_log = commute_log;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public boolean isWhether_commute() {
		return whether_commute;
	}
	public void setWhether_commute(boolean whether_commute) {
		this.whether_commute = whether_commute;
	}
	
	
}
