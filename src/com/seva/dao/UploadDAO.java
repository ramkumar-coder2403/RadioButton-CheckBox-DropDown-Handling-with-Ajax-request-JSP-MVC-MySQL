package com.seva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seva.model.UploadModel;

public class UploadDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;

	public UploadDAO(Connection con) {
		super();
		this.con = con;
	}

	public int uploadData(UploadModel model) {
		int success = 0;
		try {
			query = "insert into seva_form (name,gender,language,aathar,"
					+ "mobile,email,district,mandal,village) values(?,?,?,?,?,?,?,?,?);";
			pst = this.con.prepareStatement(query);

			pst.setString(1, model.getName());
			pst.setString(2, model.getGender());
			pst.setString(3, model.getLanguage());
			pst.setString(4, model.getAathar());
			pst.setString(5, model.getMobile());
			pst.setString(6, model.getEmail());
			pst.setString(7, model.getDistrict());
			pst.setString(8, model.getMandal());
			pst.setString(9, model.getVillage());

			success = pst.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

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
	//Check Already registered or not
	public String checkRegister(String aadharNum) {
		String aadhar = null;
		try {
			query = "select * from seva_form where aathar=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, aadharNum);
			rs = pst.executeQuery();

			while (rs.next()) {
				aadhar = rs.getString("aathar");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aadhar;
	}
}
