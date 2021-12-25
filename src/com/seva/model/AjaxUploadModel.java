package com.seva.model;

public class AjaxUploadModel {
	private int id;
	private String name, gender, english, telugu, tamil, aadhar, mobile, email, district, mandal, village, dob;

	public AjaxUploadModel() {
		super();
	}

	public AjaxUploadModel(String name, String gender, String english, String telugu, String tamil, String aadhar,
			String mobile, String email, String district, String mandal, String village, String dob) {
		super();
		this.name = name;
		this.gender = gender;
		this.english = english;
		this.telugu = telugu;
		this.tamil = tamil;
		this.aadhar = aadhar;
		this.mobile = mobile;
		this.email = email;
		this.district = district;
		this.mandal = mandal;
		this.village = village;
		this.dob = dob;
	}

	public AjaxUploadModel(int id, String name, String gender, String english, String telugu, String tamil,
			String aadhar, String mobile, String email, String district, String mandal, String village, String dob) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.english = english;
		this.telugu = telugu;
		this.tamil = tamil;
		this.aadhar = aadhar;
		this.mobile = mobile;
		this.email = email;
		this.district = district;
		this.mandal = mandal;
		this.village = village;
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

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getTelugu() {
		return telugu;
	}

	public void setTelugu(String telugu) {
		this.telugu = telugu;
	}

	public String getTamil() {
		return tamil;
	}

	public void setTamil(String tamil) {
		this.tamil = tamil;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
