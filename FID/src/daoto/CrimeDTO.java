package daoto;

public class CrimeDTO {
	
	private String CrimeCode;
	private String PeopleNum;
	private String CrimeArea;
	private String CrimeDate;
	private String Pri;
	
	public CrimeDTO(String crimeCode, String peopleNum, String crimeArea, String crimeDate, String pri) {
		super();
		CrimeCode = crimeCode;
		PeopleNum = peopleNum;
		CrimeArea = crimeArea;
		CrimeDate = crimeDate;
		Pri = pri;
	}

	public String getCrimeCode() {
		return CrimeCode;
	}

	public void setCrimeCode(String crimeCode) {
		CrimeCode = crimeCode;
	}

	public String getPeopleNum() {
		return PeopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		PeopleNum = peopleNum;
	}

	public String getCrimeArea() {
		return CrimeArea;
	}

	public void setCrimeArea(String crimeArea) {
		CrimeArea = crimeArea;
	}

	public String getCrimeDate() {
		return CrimeDate;
	}

	public void setCrimeDate(String crimeDate) {
		CrimeDate = crimeDate;
	}

	public String getPri() {
		return Pri;
	}

	public void setPri(String pri) {
		Pri = pri;
	}
	
	
	
	
	

}
