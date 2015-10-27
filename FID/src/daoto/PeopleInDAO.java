package daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PeopleInDAO {
	private String url = "jdbc:mysql://14.63.217.25:3306/project-fid";
	private String id = "root";
	private String pw = "5284";
	
	public PeopleInDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertPeopleIn(PeopleInDTO dto){
		
		PictureDAO pDAO = new  PictureDAO();
		PictureDTO pDTO	= new PictureDTO( dto.getPeopleNum(), dto.getFaceid());
		pDAO.insertPicture(pDTO);
		
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO aDTO = new AddressCodeDTO(dto.getZipCode(), dto.getSido(), dto.getSigungu(),dto.getDetail());
		aDAO.insertAddressCode(aDTO);
		
		peopleInfoDAO peDAO = new peopleInfoDAO();
		peopleInfoDTO peDTO = new peopleInfoDTO(dto.getPeopleNum(), dto.getName(), dto.getZipCode(), dto.getDetailAddr());
		peDAO.insertPeopleInfor(peDTO);

		
	}
	public PeopleInDTO selectPeopelIn(String peopleNum){
		
		PictureDAO pDAO = new  PictureDAO();
		PictureDTO pDTO = pDAO.selectPicture(peopleNum);
		
		peopleInfoDAO peDAO = new peopleInfoDAO();
		peopleInfoDTO peDTO = peDAO.selectPeopleInfo(peopleNum);
		
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO aDTO = aDAO.selectAddressCodeDAO(peDTO.getZipcode());
		
		PeopleInDTO PeoInDTO = new PeopleInDTO(peDTO.getPeopleNum(), peDTO.getName(), peDTO.getZipcode(), aDTO.getSido(), aDTO.getGugun(), peDTO.getDetailAdress(),pDTO.getFacID(),null);
		
		
		return PeoInDTO;
		
	}
	
public void updatePeopleIn(PeopleInDTO dto){
		
		PictureDAO pDAO = new  PictureDAO();
		PictureDTO pDTO	= new PictureDTO( dto.getPeopleNum(), dto.getFaceid());
		pDAO.updatePicture(pDTO);
		
		AddressCodeDAO aDAO = new AddressCodeDAO();
		AddressCodeDTO aDTO = new AddressCodeDTO(dto.getZipCode(), dto.getSido(), dto.getSigungu(),dto.getDetail());
		aDAO.insertAddressCode(aDTO);
		
		peopleInfoDAO peDAO = new peopleInfoDAO();
		peopleInfoDTO peDTO = new peopleInfoDTO(dto.getPeopleNum(), dto.getName(), dto.getZipCode(), dto.getDetailAddr());
		peDAO.updatePeopleInfor(peDTO);

		
	}
}
