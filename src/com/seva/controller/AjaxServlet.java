package com.seva.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seva.dao.AjaxUploadDAO;
import com.seva.dao.DBCon;
import com.seva.model.AjaxUploadModel;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AjaxUploadDAO dao;

	public AjaxServlet() {
		super();
		this.dao = new AjaxUploadDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// writer
		final PrintWriter writer = response.getWriter();
		String op = request.getParameter("operation");
		// Declare and initialize values
		String district = null;
		String mandal = null;
		String village = null;
		String aadhar = null;
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String english = request.getParameter("english");
		String telugu = request.getParameter("telugu");
		String tamil = request.getParameter("tamil");
		aadhar = request.getParameter("aadhar");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		int districtId = Integer.parseInt(request.getParameter("district"));
		int mandalId = Integer.parseInt(request.getParameter("mandal"));
		int villageId = Integer.parseInt(request.getParameter("village"));
		String dob = request.getParameter("dob");

		// Get Address form Database
		district = dao.selectDistrict(districtId);
		mandal = dao.selectMandal(mandalId);
		village = dao.selectVillage(villageId);

		if (op.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			AjaxUploadModel model = new AjaxUploadModel(id, name, gender, english, telugu, tamil, aadhar, mobile, email,
					district, mandal, village, dob);
			int res = dao.updateUser(model);
			// Here 1 is data is updated...
			if (res >= 1) {
				System.out.println("Updated");
				
				writer.println("1");
			} else
				writer.println("Database Not Insert these values!");
		}

		if (op.equals("insert")) {
			// Check User already inserted person or not through aadhar
			if (checkUser(aadhar)) {
				// Here 2 means user already registered....

				// One of the way convert string to json
				/*
				 * String d = "{ \"id\": \"1\"}"; Gson json = new Gson(); JsonObject
				 * convertedObject = new Gson().fromJson(d, JsonObject.class); String
				 * s=json.toJson(convertedObject);
				 */
				writer.println("2");
			} else {
				AjaxUploadModel model = new AjaxUploadModel(name, gender, english, telugu, tamil, aadhar, mobile, email,
						district, mandal, village, dob);
				int res = dao.uploadData(model);
				// Here 1 is data is inserted...
				if (res >= 1) {
					System.out.println("Inserted");
					writer.println("1");
					/*
					 * RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
					 * dispatcher.forward(request, response);
					 */
				} else {
					writer.println("Database Not Insert these values!");
				}
			}

//					System.out.print(name+gender+email+district);

		}

	}

	public boolean checkUser(String aadhar) {
		String check = dao.checkRegister(aadhar);
		if (check == null || check == "") {
			return false;
		} else
			return true;
	}

}
