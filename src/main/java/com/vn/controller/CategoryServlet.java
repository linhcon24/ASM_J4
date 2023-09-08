package com.vn.controller;

import java.io.IOException;
import java.util.List;

import com.vn.model.Category;
import com.vn.service.CategoryService;
import com.vn.service.ICategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(value = {"/category" , "/category/add" , "/category/update" , "/category/delete"})
public class CategoryServlet extends HttpServlet{
	private ICategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("category/add")) {
			doGetAdd(req, resp);
		}
		else if(url.contains("category/update")) {
			doGetUpdate(req, resp);
		}
		else if(url.contains("category/delete")) {
			doGetDelete(req, resp);
		}
		else {
			List<Category> count = categoryService.getAll();
			Integer cont = count.size() / 5;
			if (count.size() % 5 != 0) {
				cont++;
			}
			String page = req.getParameter("page");
			if (page != null) {
				if (Integer.parseInt(page) > 1) {
					List<Category> list = categoryService.getAllPhanTrang(5, Integer.parseInt(page));
					req.setAttribute("count", cont);
					req.setAttribute("listCategory", list);
					req.setAttribute("viewHome", "category/index.jsp");
					req.getRequestDispatcher("layout.jsp").forward(req, resp);
				}
			}
			List<Category> list = categoryService.getAllPhanTrang(5,1);
			req.setAttribute("listCategory", list);
			req.setAttribute("viewHome", "category/index.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
		
		
		
	}
	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "category/addCategory.jsp");
		req.getRequestDispatcher("../layout.jsp").forward(req, resp);
		
	}
	protected void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idCategory");
		Category category = categoryService.getByID(Integer.parseInt(id));
		req.setAttribute("cate", category);
		
		req.setAttribute("viewHome", "category/upateCategory.jsp");
		req.getRequestDispatcher("../layout.jsp").forward(req, resp);
		
	}
	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idCategory");
		
		
		categoryService.delete(Integer.parseInt(id));
			System.out.println("Thanh cong");
			resp.sendRedirect(req.getContextPath()+"/category");
}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("category/add")) {
			doPostAdd(req, resp);
		}
		else if(url.contains("category/update")) {
			doPostUpdate(req, resp);
		}
	}
	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ten = req.getParameter("ten");
		Category category = new Category(0, ten, 0);
		if (categoryService.add(category) == true) {
			System.out.println("thanh cong");
			resp.sendRedirect(req.getContextPath()+"/category");
		}
		else {
			System.out.println("That bai");
		}
	}
	protected void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id = req.getParameter("id");
			String ten = req.getParameter("ten");
			Category category = new Category(Integer.parseInt(id), ten, 0);
			if (categoryService.update(category) == true) {
				System.out.println("thanh cong");
				resp.sendRedirect(req.getContextPath()+"/category");
			}
			else {
				System.out.println("That bai");
			}
		}
	
}
