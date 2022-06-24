package mvc.model;

public class CommuteDTO {
	private String number;
	private String commute_log;
	private String start_time;
	private String end_time;
	private long t_time;
	private boolean chk;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCommute_log() {
		return commute_log;
	}
	public void setCommute_log(String commute_log) {
		this.commute_log = commute_log;
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
	public long getT_time() {
		return t_time;
	}
	public void setT_time(long t_time) {
		this.t_time = t_time;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	
	
	
}
