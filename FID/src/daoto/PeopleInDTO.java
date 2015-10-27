package daoto;

public class PeopleInDTO {
	private String peopleNum = null;
	private String name = null;
	private String zipCode = null;
	private String sido = null;
	private String sigungu = null;
	private String detailAddr = null;
	private String detail = null;
	private String faceid = null;

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}



	public PeopleInDTO(String peopleNum, String name, String zipCode, String sido, String sigungu, String detailAddr,
			String faceid, String deatil) {
		// TODO Auto-generated constructor stub
		this.peopleNum = peopleNum;
		this.name = name;
		this.zipCode = zipCode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.detailAddr = detailAddr;
		this.faceid = faceid;
		this.detail= deatil;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getFaceid() {
		return faceid;
	}

	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	

}
