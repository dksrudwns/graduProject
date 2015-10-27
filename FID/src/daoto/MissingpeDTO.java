package daoto;

public class MissingpeDTO {
	private String MissingPeopleCode = null;
	private String ProtecterCode = null;
	private String ProtecterPh = null;

	public MissingpeDTO(String missingPeopleCode, String protecterCode, String protecterPh) {
		super();
		MissingPeopleCode = missingPeopleCode;
		ProtecterCode = protecterCode;
		ProtecterPh = protecterPh;
	}

	public String getMissingPeopleCode() {
		return MissingPeopleCode;
	}

	public void setMissingPeopleCode(String missingPeopleCode) {
		MissingPeopleCode = missingPeopleCode;
	}

	public String getProtecterCode() {
		return ProtecterCode;
	}

	public void setProtecterCode(String protecterCode) {
		ProtecterCode = protecterCode;
	}

	public String getProtecterPh() {
		return ProtecterPh;
	}

	public void setProtecterPh(String protecterPh) {
		ProtecterPh = protecterPh;
	}

}
