package com.nic.model;

public class ViewModel {
	private int id;
	private String name, gender, language, aadhar, mobile, email, address, dob;

	public ViewModel() {
		super();
	}

	public ViewModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ViewModel(int id, String name, String gender, String language, String aadhar, String mobile, String email,
			String address, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.language = language;
		this.aadhar = aadhar;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
