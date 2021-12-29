package com.nic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nic.model.AjaxModel;

public class AjaxDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;

	public AjaxDAO(Connection con) {
		super();
		this.con = con;
	}

	// Insert Data
	public int insertUser(AjaxModel model) {
		int success = 0;
		try {
			query = "insert into nic_ajax_user_details (id,name,gender,english,telugu,tamil,aadhar,"
					+ "mobile,email,district,mandal,village,dob) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setString(2, model.getName());
			pst.setString(3, model.getGender());
			pst.setString(4, model.getEnglish());
			pst.setString(5, model.getTelugu());
			pst.setString(6, model.getTamil());
			pst.setString(7, model.getAadhar());
			pst.setString(8, model.getMobile());
			pst.setString(9, model.getEmail());
			pst.setString(10, model.getDistrict());
			pst.setString(11, model.getMandal());
			pst.setString(12, model.getVillage());
			pst.setString(13, model.getDob());

			success = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	// Update User
	public int updateUser(AjaxModel model) {
		int success = 0;
		try {
			query = "update nic_ajax_user_details set name=?,gender=?,english=?,telugu=?,tamil=?,aadhar=?,"
					+ "mobile=?,email=?,district=?,mandal=?,village=?,dob=? where id=?;";
			pst = this.con.prepareStatement(query);

			pst.setString(1, model.getName());
			pst.setString(2, model.getGender());
			pst.setString(3, model.getEnglish());
			pst.setString(4, model.getTelugu());
			pst.setString(5, model.getTamil());
			pst.setString(6, model.getAadhar());
			pst.setString(7, model.getMobile());
			pst.setString(8, model.getEmail());
			pst.setString(9, model.getDistrict());
			pst.setString(10, model.getMandal());
			pst.setString(11, model.getVillage());
			pst.setString(12, model.getDob());
			pst.setInt(13, model.getId());

			success = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	// District name
	public String selectDistrict(int id) {
		String district = null;

		try {
			query = "select name from seva_district where id=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				district = rs.getString("name");
			}
		} catch (SQLException e) {
			System.out.println("err : " + e);
		}
		return district;
	}

	// Mandal name
	public String selectMandal(int id) {
		String mandal = null;
		try {
			query = "select name from seva_mandal where id=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				mandal = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mandal;
	}

	// Village name
	public String selectVillage(int id) {
		String village = null;
		try {
			query = "select name from seva_village where id=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				village = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return village;
	}

	// Check Already registered or not
	public String checkRegister(String aadharNum) {
		String aadhar = null;
		try {
			query = "select aadhar from nic_ajax_user_details where aadhar=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, aadharNum);
			rs = pst.executeQuery();

			while (rs.next()) {
				aadhar = rs.getString("aadhar");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aadhar;
	}
}
