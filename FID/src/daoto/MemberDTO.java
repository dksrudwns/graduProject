package daoto;


public class MemberDTO {
	private String id;
	private String pw;
	private String lv;
	private String pn;

	public MemberDTO(String id, String pw, String lv, String pn) {
		this.id = id;
		this.pw = pw;
		this.lv = lv;
		this.pn = pn;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLv() {
		return this.lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public String getPn() {
		return this.pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}
}