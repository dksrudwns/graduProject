package daoto;

public class peopleInfoDTO {
	private String peopleNum;
	private String name;
	private String zipcode;
	private String detailAdress;
	
	public peopleInfoDTO(String peopleNum, String name, String zipcode, String detailAdress) {
		this.peopleNum = peopleNum;
		this.name = name;
		this.zipcode = zipcode;
		this.detailAdress = detailAdress;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDetailAdress() {
		return detailAdress;
	}

	public void setDetailAdress(String detailAdress) {
		this.detailAdress = detailAdress;
	}
	
	
}
