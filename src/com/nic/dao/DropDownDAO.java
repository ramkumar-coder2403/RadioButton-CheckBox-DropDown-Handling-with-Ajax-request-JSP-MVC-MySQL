package com.nic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nic.model.District;
import com.nic.model.Mandal;
import com.nic.model.Village;


public class DropDownDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;
	
	public DropDownDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public List<District> getAllDistrict() {
		List<District> list = new ArrayList<District>();

		try {
			query = "select * from seva_district";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				District district = new District();
				district.setId(rs.getInt("id"));
				district.setName(rs.getString("name"));
				list.add(district);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Mandal> getMandalByDistrictId(int mandalId) {
		List<Mandal> list = new ArrayList<Mandal>();

		try {
			query = "select * from seva_mandal where d_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, mandalId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Mandal mandal = new Mandal();
				mandal.setId(rs.getInt("id"));
				mandal.setDistrictId(rs.getInt("d_id"));
				mandal.setName(rs.getString("name"));
				list.add(mandal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Village> getVillageByMandalId(int mandalId) {
		List<Village> list = new ArrayList<Village>();

		try {
			query = "select * from seva_village where m_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, mandalId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Village village = new Village();
				village.setId(rs.getInt("id"));
				village.setDistrictId(rs.getInt("d_id"));
				village.setMandalId(rs.getInt("m_id"));
				village.setName(rs.getString("name"));
				list.add(village);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
