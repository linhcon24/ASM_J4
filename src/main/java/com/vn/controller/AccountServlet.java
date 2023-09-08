package com.vn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vn.model.Account;
import com.vn.model.Product;
import com.vn.service.AccountService;
import com.vn.service.IAccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = { "/users", "/users/add", "/users/update", "/users/delete" })
public class AccountServlet extends HttpServlet {
	private IAccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		if (url.contains("users/add")) {
			doGetAdd(req, resp);
		} else if (url.contains("users/update")) {
			doGetUpdate(req, resp);
		} else if (url.contains("users/delete")) {
			doGetDelete(req, resp);
		} 
		else {
			List<Account> count = accountService.getAll();
			Integer cont = count.size() / 5;
			if (count.size() % 5 !=0) {
				cont++;
			}
			String page = req.getParameter("page");
			if (page == null) page = "1";
			List<Account> list = accountService.getAllPhanTrang(5, Integer.parseInt(page));
			req.setAttribute("count", cont);
			req.setAttribute("listAccount", list);
			req.setAttribute("viewHome", "users/index.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			
		}
	}

	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "users/addUser.jsp");
		req.getRequestDispatcher("../layout.jsp").forward(req, resp);
	
	}

	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userStr = req.getParameter("user");

		accountService.delete(userStr);
		System.out.println("xoa thanh cong");
		resp.sendRedirect(req.getContextPath() + "/users");

	}

	protected void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		Account account1 = accountService.getByUsername(user);
		String roleStr = "";
		if (account1.getRole() == 1) {
			roleStr = "Nhân viên";
		} else {
			roleStr = "Admin";
		}
		req.setAttribute("role", roleStr);
		req.setAttribute("account1", account1);
		req.setAttribute("viewHome", "users/updateUser.jsp");
		req.getRequestDispatcher("../layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("users/add")) {
			doPostAdd(req, resp);
		} else if (url.contains("users/update")) {
			doPostUpdate(req, resp);
		} else {
			
			req.setAttribute("viewHome", "users/index.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			
		}
	}

	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String roleStr = req.getParameter("role");
		Integer role = 0;
		if (roleStr.equalsIgnoreCase("Thành viên")) {
			role = 1;
		}

		Account account = new Account(username, password, email, role);
		if (accountService.add(account) == true) {
			resp.sendRedirect(req.getContextPath() + "/users");
		} else {
			req.setAttribute("message", "Thêm user thất bại");
			
			req.setAttribute("viewHome", "users/addUser.jsp");
			req.getRequestDispatcher("../layout.jsp").forward(req, resp);
			
		}
	}

	protected void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String roleStr = req.getParameter("role");
		Integer role = 0;
		if (roleStr.equalsIgnoreCase("Thành viên")) {
			role = 1;
		}

		Account account = new Account(username, password, email, role);
		if (accountService.update(account) == true) {
			resp.sendRedirect(req.getContextPath() + "/users");
		} else {
			req.setAttribute("message", "Sửa user thất bại");
			req.setAttribute("viewHome", "users/updateUser.jsp");
			req.getRequestDispatcher("../layout.jsp").forward(req, resp);
		}
	}

}
