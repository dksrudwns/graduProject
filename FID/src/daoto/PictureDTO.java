
package daoto;

public class PictureDTO {
	private String URL;
	private String FacID;

	public PictureDTO(String URL, String FaceId) {
		this.URL = URL;
		this.FacID = FaceId;
	}


	public String getURL() {
		return this.URL;
	}

	public void setURL(String uRL) {
		this.URL = uRL;
	}

	public String getFacID() {
		return this.FacID;
	}

	public void setFacID(String facID) {
		this.FacID = facID;
	}
}