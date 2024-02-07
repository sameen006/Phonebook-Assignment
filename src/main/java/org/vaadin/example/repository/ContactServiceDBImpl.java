package org.vaadin.example.repository;

import org.vaadin.example.model.ContactInfoModel;
import org.vaadin.example.service.ContactInfoService;
import org.vaadin.example.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ContactServiceDBImpl implements ContactInfoService {

	private static ConcurrentHashMap<Integer, ContactInfoModel> contactInfoMap = JdbcUtil.initContactMapFromDB();

	public static String DELETE_CONTACT_QUERY = "DELETE FROM db_contact.contact_info where id = ?";

	public static String UPDATE_CONTACT_QUERY = "UPDATE db_contact.contact_info set first_name = ?, last_name = ?, street = ?, city = ?,"
			+ "country = ?, phoneNumber = ?, email = ? ,edit_mode = ? "
			+ "where id = ?";

	public static String INSERT_CONTACTS_QUERY = "INSERT INTO db_contact.contact_info (first_name, last_name, "
			+ "street, city, country, phoneNumber, email ,edit_mode)"
			+ "VALUES (?,?,?,?,?,?,?,?)";

	public static String FIND_ID_BY_PHONE_NUMBER = "SELECT id FROM db_contact.contact_info where phoneNumber = ?";



	@Override
	public ConcurrentHashMap<Integer, ContactInfoModel> getContacts(){

		return contactInfoMap;
	}


	@Override
	public void delete(ContactInfoModel model) {

		contactInfoMap.entrySet().removeIf(entity -> entity.getValue().getId().equals(model.getId()));

		try {
			Connection con = JdbcUtil.getConnection();
			PreparedStatement stmt = con.prepareStatement(DELETE_CONTACT_QUERY);
			stmt.setInt(1,model.getId());
			stmt.executeUpdate();
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public  Boolean isExistingPhoneNumber(String phoneNumber, ContactInfoModel model) {
		Optional<ContactInfoModel> contactInfoModel = contactInfoMap.values().stream().filter(entity -> entity.getPhoneNumber().equals(phoneNumber))
				.findFirst();
		return contactInfoModel.isPresent() && contactInfoModel.get().getId()!= model.getId();
	}

	@Override
	public String persist(ContactInfoModel model, ContactInfoModel orgModel) {

		if (model.getId() == null )
			return addNewContact(model, orgModel);

		else {
			if (isExistingPhoneNumber(model.getPhoneNumber(),orgModel))
				return "Contact already exist in phone book";

			Integer position = contactInfoMap.entrySet().stream().filter(entry -> entry.getValue().getId().equals(model.getId())).findFirst().get().getKey();
			model.setEditMode(false);
			contactInfoMap.remove(position);
			contactInfoMap.put(position, model);

			try{
				Connection con = JdbcUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(UPDATE_CONTACT_QUERY);
				stmt.setString(1,model.getFirstName());
				stmt.setString(2,model.getLastName());
				stmt.setString(3,model.getStreet());
				stmt.setString(4,model.getCity());
				stmt.setString(5, model.getCountry());
				stmt.setString(6,model.getPhoneNumber());
				stmt.setString(7, model.getEmail());
				stmt.setString(8,"0");
				stmt.setInt(9,model.getId());

				stmt.executeUpdate();
				con.close();
			}catch(SQLException ex){
				throw new RuntimeException(ex);
			}
			return "Contact Successfully updated";

		}
	}

	private String addNewContact(ContactInfoModel model, ContactInfoModel orgModel) {
		if (isExistingPhoneNumber(model.getPhoneNumber(), orgModel))
			return "Contact already exist in phone book";

		else{

			try{
				Connection con = JdbcUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT_CONTACTS_QUERY);
				stmt.setString(1,model.getFirstName());
				stmt.setString(2,model.getLastName());
				stmt.setString(3,model.getStreet());
				stmt.setString(4,model.getCity());
				stmt.setString(5, model.getCountry());
				stmt.setString(6,model.getPhoneNumber());
				stmt.setString(7, model.getEmail());
				stmt.setString(8,"0");

				stmt.executeUpdate();
				con.close();
			}catch(SQLException ex){
				throw new RuntimeException(ex);
			}

			model.setId(getId(model.getPhoneNumber()));
			model.setEditMode(false);
			model.setEditCount(0);
			contactInfoMap.put(contactInfoMap.size()+1, model);
			return "Contact Successfully added";
		}
	}

	private int getId(String phoneNumber) {
		int id;
		try {
			Connection con = JdbcUtil.getConnection();
			PreparedStatement stmt = con.prepareStatement(FIND_ID_BY_PHONE_NUMBER);
			stmt.setString(1, phoneNumber);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			id =rs.getInt(1);

			con.close();

			return id;

		}catch(SQLException ex){
			throw new RuntimeException(ex);
		}

	}
}
