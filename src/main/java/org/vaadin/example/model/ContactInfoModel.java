package org.vaadin.example.model;

public class ContactInfoModel implements Cloneable {

	private Integer id;
	private String firstName;
	private String lastName;
	private String city;
	private String country;
	private String street;
	private String email;
	private String phoneNumber;

	private boolean editMode;

	private int editCount;

	public ContactInfoModel(Integer id, String firstName, String lastName, String city, String country, String street, String email, String phoneNumber, boolean editMode,
			int editCount) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.country = country;
		this.street = street;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.editMode = editMode;
		this.editCount= editCount;
	}

	public ContactInfoModel(){}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public int getEditCount() {
		return editCount;
	}

	public void setEditCount(int editCount) {
		this.editCount = editCount;
	}

	@Override
	public ContactInfoModel clone() {
		try {
			ContactInfoModel clone = (ContactInfoModel) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
