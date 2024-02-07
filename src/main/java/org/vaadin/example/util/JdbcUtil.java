package org.vaadin.example.util;

import org.vaadin.example.model.ContactInfoModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtil {
	private static String serverName = "localhost:3306";
	private static String db = "db_contact";
	private static String url = "jdbc:mysql://" + serverName + "/" + db;
	private static String username = "root";
	private static String password = "l1nx@3!";

	private static String FETCH_ALL_CONTACTS_QUERY = "SELECT * FROM db_contact.contact_info";


	public static Connection getConnection() throws SQLException {

		Connection connection = null;
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName); // here is the ClassNotFoundException
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return connection;
	}
	public static ConcurrentHashMap<Integer, ContactInfoModel> initContactMapFromDB() {
		ConcurrentHashMap<Integer, ContactInfoModel> contactInfoMap = new ConcurrentHashMap<>();
		int index =1;

		try {
			Connection con = JdbcUtil.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(FETCH_ALL_CONTACTS_QUERY);
			while(rs.next()){
				contactInfoMap.put(index,buildContactInfoModel(rs));
				index++;
			}
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return contactInfoMap;
	}

	private static ContactInfoModel buildContactInfoModel(ResultSet rs) {
		ContactInfoModel contactInfoModel = new ContactInfoModel();

		try{
			contactInfoModel.setId(rs.getInt(1));
			contactInfoModel.setFirstName(rs.getString(2));
			contactInfoModel.setLastName(rs.getString(3));
			contactInfoModel.setStreet(rs.getString(4));
			contactInfoModel.setCity(rs.getString(5));
			contactInfoModel.setCountry(rs.getString(6));
			contactInfoModel.setPhoneNumber(rs.getString(7));
			contactInfoModel.setEmail(rs.getString(8));
			contactInfoModel.setEditMode(rs.getBoolean(9));
			contactInfoModel.setEditCount(0);
		}catch (SQLException ex){
			throw new RuntimeException(ex);
		}


		return contactInfoModel;

	}
}