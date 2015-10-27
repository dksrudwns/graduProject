package daoto;

public class searchLocalMDTO {
	private String peopleNum;
	private String name;
	
	public searchLocalMDTO(String peopleNum, String name) {
		super();
		this.peopleNum = peopleNum;
		this.name = name;
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
	
	
	
}
