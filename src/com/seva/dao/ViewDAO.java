package com.seva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seva.model.AjaxUploadModel;
import com.seva.model.DisplayModel;

public class ViewDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;

	public ViewDAO(Connection con) {
		super();
		this.con = con;
	}

	// View all records
	public List<DisplayModel> getAllDetails() {
		List<DisplayModel> list = new ArrayList<DisplayModel>();

		try {
			query = "SELECT * FROM seva_ajax_upload;";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				DisplayModel dModel = new DisplayModel();
				dModel.setId(rs.getInt("id"));
				dModel.setName(rs.getString("name"));
				dModel.setGender(rs.getString("gender"));
				dModel.setLanguage(
						rs.getString("english") + " " + rs.getString("telugu") + " " + rs.getString("tamil"));
				dModel.setAadhar(rs.getString("aadhar"));
				dModel.setMobile(rs.getString("mobile"));
				dModel.setEmail(rs.getString("email"));
				dModel.setAddress(
						rs.getString("village") + "," + rs.getString("mandal") + "," + rs.getString("district"));
				dModel.setDob(rs.getString("dob"));

				list.add(dModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// Get record by id for Edit
	public AjaxUploadModel selectUserById(int id) {

		ViewDAO dao = new ViewDAO(DBCon.getCon());

		AjaxUploadModel model = null;
		try {
			query = "select * from seva_ajax_upload where id = ?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String english = rs.getString("english");
				String telugu = rs.getString("telugu");
				String tamil = rs.getString("tamil");
				String aadhar = rs.getString("aadhar");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String district = dao.selectDistrictId(rs.getString("district"));
				String mandal = dao.selectMandalId(rs.getString("mandal"));
				String village = dao.selectVillageId(rs.getString("village"));
				String dob = rs.getString("dob");

				model = new AjaxUploadModel(id, name, gender, english, telugu, tamil, aadhar, mobile, email, district,
						mandal, village, dob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// delete users
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;

		query = "delete from seva_ajax_upload where id = ?;";
		pst = this.con.prepareStatement(query);
		pst.setInt(1, id);
		rowDeleted = pst.executeUpdate() > 0;

		return rowDeleted;
	}

	// DistrictId
	public String selectDistrictId(String name) {
		String district = null;

		try {
			query = "select id from seva_district where name=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, name);
			rs = pst.executeQuery();

			while (rs.next()) {
				district = rs.getString("id");
			}
		} catch (SQLException e) {
			System.out.println("err : " + e);
		}
		return district;
	}

	// MandalId
	public String selectMandalId(String name) {
		String mandal = null;
		try {
			query = "select id from seva_mandal where name=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, name);
			rs = pst.executeQuery();

			while (rs.next()) {
				mandal = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mandal;
	}

	// VillageId
	public String selectVillageId(String name) {
		String village = null;
		try {
			query = "select id from seva_village where name=?;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, name);
			rs = pst.executeQuery();

			while (rs.next()) {
				village = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return village;
	}

}
