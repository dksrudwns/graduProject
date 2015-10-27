package daoto;

public class searchLocalCDTO {
	private String peopleNum;
	private String name;
	private String CrimeArea;
	private String CrimeText;
	public searchLocalCDTO(String peopleNum, String name, String crimeArea, String crimeText) {
		super();
		this.peopleNum = peopleNum;
		this.name = name;
		CrimeArea = crimeArea;
		CrimeText = crimeText;
	}
	public String getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrimeArea() {
		return CrimeArea;
	}
	public void setCrimeArea(String crimeArea) {
		CrimeArea = crimeArea;
	}
	public String getCrimeText() {
		return CrimeText;
	}
	public void setCrimeText(String crimeText) {
		CrimeText = crimeText;
	}
	
	
}
