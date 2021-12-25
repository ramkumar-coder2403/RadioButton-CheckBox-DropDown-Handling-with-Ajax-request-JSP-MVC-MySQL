package com.seva.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.seva.dao.DBCon;
import com.seva.dao.ViewDAO;
import com.seva.model.AjaxUploadModel;
import com.seva.model.DisplayModel;

@WebServlet("/OptServlet")
public class OptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewDAO dao;

	public OptServlet() {
		super();
		this.dao = new ViewDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");

		if (op.equals("get_details")) {

			List<DisplayModel> dlist = dao.getAllDetails();
			Gson json = new Gson();
			String detailsList = json.toJson(dlist);
			response.setContentType("text/html");
			response.getWriter().write(detailsList);
		}
		if (op.equals("delete")) {

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.print(id);
			try {
				dao.deleteUser(id);
				response.sendRedirect("view.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (op.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			AjaxUploadModel existingUser = dao.selectUserById(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
