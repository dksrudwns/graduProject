package daoto;

public class MissingDTO {

	private String MissingCode = null;
	private String peopleNum = null;
	private String missingDate = null;
	private String findDate = null;
	private String missingAddress = null;
	private String findAddress = null;
	private String pri = null;
	public MissingDTO(String missingCode, String peopleNum, String missingDate, String findDate, String missingAddress,
			String findAddress, String pri) {
		super();
		MissingCode = missingCode;
		this.peopleNum = peopleNum;
		this.missingDate = missingDate;
		this.findDate = findDate;
		this.missingAddress = missingAddress;
		this.findAddress = findAddress;
		this.pri = pri;
	}
	public String getMissingCode() {
		return MissingCode;
	}
	public void setMissingCode(String missingCode) {
		MissingCode = missingCode;
	}
	public String getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getMissingDate() {
		return missingDate;
	}
	public void setMissingDate(String missingDate) {
		this.missingDate = missingDate;
	}
	public String getFindDate() {
		return findDate;
	}
	public void setFindDate(String findDate) {
		this.findDate = findDate;
	}
	public String getMissingAddress() {
		return missingAddress;
	}
	public void setMissingAddress(String missingAddress) {
		this.missingAddress = missingAddress;
	}
	public String getFindAddress() {
		return findAddress;
	}
	public void setFindAddress(String findAddress) {
		this.findAddress = findAddress;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	
	
	
}
