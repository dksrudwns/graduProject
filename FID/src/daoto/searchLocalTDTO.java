package daoto;

public class searchLocalTDTO {
	private String TraceText;
	private String Money;
	private String Name;
	private String PeopleNum;
	public searchLocalTDTO(String traceText, String money, String name, String peopleNum) {
		super();
		TraceText = traceText;
		Money = money;
		Name = name;
		PeopleNum = peopleNum;
	}
	public String getTraceText() {
		return TraceText;
	}
	public void setTraceText(String traceText) {
		TraceText = traceText;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPeopleNum() {
		return PeopleNum;
	}
	public void setPeopleNum(String peopleNum) {
		PeopleNum = peopleNum;
	}
	
	
	
	

}
