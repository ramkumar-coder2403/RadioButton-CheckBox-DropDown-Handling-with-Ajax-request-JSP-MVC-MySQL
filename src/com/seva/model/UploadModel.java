package com.seva.model;

public class UploadModel {
	private int id;
	private String name, gender, language, aathar, mobile, email, district, mandal, village;

	public UploadModel(String name, String gender, String language, String aathar, String mobile, String email,
			String district, String mandal, String village) {
		super();
		this.name = name;
		this.gender = gender;
		this.language = language;
		this.aathar = aathar;
		this.mobile = mobile;
		this.email = email;
		this.district = district;
		this.mandal = mandal;
		this.village = village;
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

	public String getAathar() {
		return aathar;
	}

	public void setAathar(String aathar) {
		this.aathar = aathar;
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

}
