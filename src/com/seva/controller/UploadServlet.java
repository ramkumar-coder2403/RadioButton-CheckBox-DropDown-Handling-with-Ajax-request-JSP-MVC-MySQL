package com.seva.controller;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.seva.dao.DBCon;
//import com.seva.dao.UploadDAO;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private UploadDAO dao;

	public UploadServlet() {
		super();
//		this.dao = new UploadDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// writer
//		final PrintWriter writer = response.getWriter();
//		// Declare Fields
//		String language = "";
//		String district = null;
//		String mandal = null;
//		String village = null;
//		String aadhar = null;
//		String name, gender, mobile, email;
//		// Getting values from index page
//		try {
//
//			name = request.getParameter("u_name");
//			gender = request.getParameter("gender");
//			String languageArr[] = request.getParameterValues("language");
//			int length = 1;
//			for (String val : languageArr) {
//				if (length == languageArr.length)
//					language += val;
//				else
//					language += val + "/";
//				length++;
//			}
//			aadhar = request.getParameter("aathar");
//			mobile = request.getParameter("mobile");
//			email = request.getParameter("email");
//
//		} catch (Exception e) {
//			writer.println("<br>Getting Null Values!!!");
//		}
//		// Get Address form Database
//		try {
//			district = dao.selectDistrict(Integer.parseInt(request.getParameter("district")));
//			mandal = dao.selectMandal(Integer.parseInt(request.getParameter("mandal")));
//			village = dao.selectVillage(Integer.parseInt(request.getParameter("village")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String checkUser = dao.checkRegister(aadhar);
//		if (checkUser.equals(aadhar)) {
//			System.out.print(checkUser);
//		}
//		System.out.print(checkUser);
////		//Upload to database
////		if(district!=null && gender!=null && email!=null && mobile!=null) {
////			UploadModel model=new UploadModel(name, gender, language, aadhar, mobile, email, district, mandal, village);
////			int res = dao.uploadData(model);
////			if (res >= 1) {
////				System.out.println("Inserted");
////				RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
////				dispatcher.forward(request, response);
////			} else
////				writer.println("<br>Not Inserted");
////		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
