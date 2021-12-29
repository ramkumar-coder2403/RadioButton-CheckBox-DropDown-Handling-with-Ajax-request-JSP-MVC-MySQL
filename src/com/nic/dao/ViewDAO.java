package com.nic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nic.model.AjaxModel;
import com.nic.model.Search;
import com.nic.model.ViewModel;

public class ViewDAO {
	// count row variable declare here
	private int noOfRecords;
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;

	public ViewDAO(Connection con) {
		super();
		this.con = con;
	}

	// View all records
	public List<ViewModel> getAllDetails(int offset, int noOfRecords) {
		List<ViewModel> list = new ArrayList<ViewModel>();

		try {
			query = "select SQL_CALC_FOUND_ROWS * from nic_ajax_user_details limit " + offset + ", " + noOfRecords;
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				ViewModel dModel = new ViewModel();
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
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dob"));
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");
				dModel.setDob(myFormat.format(date1));

				list.add(dModel);
			}
			rs.close();

			rs = pst.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next())
				this.noOfRecords = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// RETURN NO OF RECORD FOR PAGINATION
	public int getNoOfRecords() {
		return noOfRecords;
	}

	// GET SINGLE RECORD FOR EDIT
	public AjaxModel selectUserById(int id) {

		ViewDAO dao = new ViewDAO(DBCon.getCon());

		AjaxModel model = null;
		try {
			query = "select * from nic_ajax_user_details where id = ?;";
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

				model = new AjaxModel(id, name, gender, english, telugu, tamil, aadhar, mobile, email, district, mandal,
						village, dob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// GET SINGLE RECORD FOR DISPLAY
	public AjaxModel selectUserByIdView(int id) {
		AjaxModel model = null;
		try {
			query = "select * from nic_ajax_user_details where id = ?;";
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
				String district = rs.getString("district");
				String mandal = rs.getString("mandal");
				String village = rs.getString("village");
				String dob = rs.getString("dob");

				model = new AjaxModel(id, name, gender, english, telugu, tamil, aadhar, mobile, email, district, mandal,
						village, dob);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// CHECK ID FOR VERIFIED USER
	public int checkUser(int id) {
		int u_id = 0;
		try {
			query = "select id from nic_ajax_user_details where id=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				u_id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u_id;
	}

	// DELETE USER WITH ID
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;

		query = "delete from nic_ajax_user_details where id = ?;";
		pst = this.con.prepareStatement(query);
		pst.setInt(1, id);
		rowDeleted = pst.executeUpdate() > 0;

		return rowDeleted;
	}

	// GET SEARCH LIST(ID AND NAME)
	public List<ViewModel> getAllID() {
		List<ViewModel> list = new ArrayList<ViewModel>();

		try {
			query = "select id,name from nic_ajax_user_details";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				ViewModel model = new ViewModel();
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// ALTER SEARCH LIST///////
	// GET SEARCH LIST(ID AND NAME)
	public List<Search> getAllId() {
		List<Search> list = new ArrayList<Search>();

		try {
			query = "select id,name from nic_ajax_user_details";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Search search = new Search();
				search.setId(String.valueOf(rs.getInt("id")));
				search.setName(rs.getString("name"));
				list.add(search);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
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
