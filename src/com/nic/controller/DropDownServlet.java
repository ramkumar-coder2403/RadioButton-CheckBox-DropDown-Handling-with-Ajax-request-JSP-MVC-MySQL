package com.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nic.dao.DBCon;
import com.nic.dao.DropDownDAO;
import com.nic.model.District;
import com.nic.model.Mandal;
import com.nic.model.Village;

@WebServlet("/DropDownServlet")
public class DropDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DropDownDAO dao;

	// Configure Database Connection
	public DropDownServlet() {
		super();
		this.dao = new DropDownDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			String op = request.getParameter("operation");

			if (op.equals("district")) {
				List<District> clist = dao.getAllDistrict();
				Gson json = new Gson();
				String districtList = json.toJson(clist);
				response.setContentType("text/html");
				response.getWriter().write(districtList);

			}
			if (op.equals("mandal")) {
				int id = Integer.parseInt(request.getParameter("id"));
				List<Mandal> mList = dao.getMandalByDistrictId(id);
				Gson json = new Gson();
				String list = json.toJson(mList);
				response.setContentType("text/html");
				response.getWriter().write(list);
			}

			if (op.equals("village")) {
				int id = Integer.parseInt(request.getParameter("id"));
				List<Village> villageList = dao.getVillageByMandalId(id);
				Gson json = new Gson();
				String list = json.toJson(villageList);
				response.setContentType("text/html");
				response.getWriter().write(list);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
