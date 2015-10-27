package daoto;

public class TraceDTO {

	private String TraceCode = null;
	private String peopleNum = null;
	private String TraceCondition = null;
	private String T_Money = null;
	private String TraceDate = null;
	private String Pri = null;
	
	
	public TraceDTO(String traceCode, String peopleNum, String traceCondition, String t_Money, String traceDate,
			String pri) {
		super();
		TraceCode = traceCode;
		this.peopleNum = peopleNum;
		TraceCondition = traceCondition;
		T_Money = t_Money;
		TraceDate = traceDate;
		Pri = pri;
	}


	public String getTraceCode() {
		return TraceCode;
	}


	public void setTraceCode(String traceCode) {
		TraceCode = traceCode;
	}


	public String getPeopleNum() {
		return peopleNum;
	}


	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}


	public String getTraceCondition() {
		return TraceCondition;
	}


	public void setTraceCondition(String traceCondition) {
		TraceCondition = traceCondition;
	}


	public String getT_Money() {
		return T_Money;
	}


	public void setT_Money(String t_Money) {
		T_Money = t_Money;
	}


	public String getTraceDate() {
		return TraceDate;
	}


	public void setTraceDate(String traceDate) {
		TraceDate = traceDate;
	}


	public String getPri() {
		return Pri;
	}


	public void setPri(String pri) {
		Pri = pri;
	}
	
	
	
}
