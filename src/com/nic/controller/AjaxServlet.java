package com.nic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nic.dao.AjaxDAO;
import com.nic.dao.DBCon;
import com.nic.dao.ViewDAO;
import com.nic.model.AjaxModel;
import com.nic.model.Search;
import com.nic.model.ViewModel;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AjaxDAO dao;

	public AjaxServlet() {
		super();
		this.dao = new AjaxDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DECLARATION AND INITIALIZATION
		final PrintWriter writer = response.getWriter();
		String op = request.getParameter("operation");
		ViewDAO viewDAO = new ViewDAO(DBCon.getCon());

		// PROVIDE ID LIST
		if (op.equals("user_id")) {
			List<Search> clist = viewDAO.getAllId();
			Gson json = new Gson();
			String idList = json.toJson(clist);
			response.setContentType("text/html");
			response.getWriter().write(idList);
		}

		// VIEW ALL DETAILS
		if (op.equals("view")) {
			int page = 1;
			int recordsPerPage = 5;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));

			List<ViewModel> list = viewDAO.getAllDetails((page - 1) * recordsPerPage, recordsPerPage);
			int noOfRecords = viewDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("userList", list);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);

			RequestDispatcher view = request.getRequestDispatcher("view.jsp");
			view.forward(request, response);
		}
		// VIEW SINGLE USER AND PROVIDE DEL AND EDIT OPTION
		if (op.equals("opt")) {
			int id = Integer.parseInt(request.getParameter("u_id"));

			int verifyId = viewDAO.checkUser(id);
			if (verifyId == 0) {
				writer.write("<h1>USER ID DOESN'T EXIST!</h1>");
			} else {
				AjaxModel existingUser = viewDAO.selectUserByIdView(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("view_user.jsp");
				request.setAttribute("user", existingUser);
				dispatcher.forward(request, response);
			}
		}
		// EDIT OR UPDATE USER
		if (op.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("u_id"));
			AjaxModel existingUser = viewDAO.selectUserById(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);
		}
		// DELETE USER
		if (op.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("u_id"));
			System.out.print(id);
			try {
				viewDAO.deleteUser(id);
				response.sendRedirect("index.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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
		if (aadhar == "")
			aadhar = null;
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
			AjaxModel model = new AjaxModel(id, name, gender, english, telugu, tamil, aadhar, mobile, email, district,
					mandal, village, dob);
			int res = dao.updateUser(model);
			// Here 1 is data is updated...
			if (res >= 1) {
				System.out.println("Updated");
				writer.println("1");
			} else
				writer.println("Database Not Insert these values!");
		}

		if (op.equals("insert")) {
			System.out.print(checkUser(aadhar));
			if (checkUser(aadhar)) { // Check User already inserted person or not through aadhar

				LocalDateTime time = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddmmss");
				int uniqueId = Integer.parseInt(time.format(formatter));

				AjaxModel model = new AjaxModel(uniqueId, name, gender, english, telugu, tamil, aadhar, mobile, email,
						district, mandal, village, dob);
				int res = dao.insertUser(model);
				if (res >= 1) {
					System.out.println("Inserted");
					writer.println(uniqueId); // Here 1 is data is inserted...
				} else {
					System.out.println("Database Not Insert these values!");
				}
			} else
				writer.println("2"); // Here 2 means user already registered....
		}

	}

	public boolean checkUser(String aadhar) {
		String check = dao.checkRegister(aadhar);
		System.out.println(check);
		if (check == null || check == "") {
			return true;
		} else
			return false;
	}

}
