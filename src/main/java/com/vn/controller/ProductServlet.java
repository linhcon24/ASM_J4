package com.vn.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.vn.model.Category;
import com.vn.model.Product;
import com.vn.service.CategoryService;
import com.vn.service.ICategoryService;
import com.vn.service.IProductService;
import com.vn.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig()
@WebServlet(value = {"/admin" , "/admin/add", "/admin/update", "/admin/delete", "/admin/chitiet"})
public class ProductServlet extends HttpServlet{
	private IProductService productService = new ProductService();
	private ICategoryService categoryService = new CategoryService();
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String url = req.getRequestURI();
			if (url.contains("admin/add")) {
				this.doGetAdd(req, resp);
			}
			else if(url.contains("admin/update")) {
				this.doGetUpdate(req, resp);
			}else if(url.contains("admin/delete")) {
				this.doGetDelete(req, resp);
			}
			else if(url.contains("admin/chitiet")) {
				this.doGetChiTiet(req, resp);
			}
			
			else {
				
				List<Category> categories = categoryService.getAll();
				List<Category> newListCategory = new ArrayList<>();
				for(Category cate : categories) {
					if(cate.getTrangThai() == 0) {
						newListCategory.add(cate);
					}
				}
				
				List<Product> count = productService.getAll();
				Integer cont = count.size() / 5;
				if (count.size() % 5 != 0) {
					cont++;
				}
				String page = req.getParameter("page");
				if (page == null) page = "1";
				List<Product> list = productService.getAllPhanTrang(5, Integer.parseInt(page));
				List<Product> newList = new ArrayList<>();
				for(Product pro : list) {
					if (pro.getTrangThai() == 0) {
						newList.add(pro);
					}
				}
				req.setAttribute("count", cont);
				req.setAttribute("listProduct", newList);
				req.setAttribute("viewHome", "admin/admin.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
				
			}
		}
		protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			List<Category> categories = categoryService.getAll();
			List<Category> newListCategory = new ArrayList<>();
			for(Category cate : categories) {
				if(cate.getTrangThai() == 0) {
					newListCategory.add(cate);
				}
			}
			req.setAttribute("listCategory", newListCategory);
			req.setAttribute("viewHome", "admin/addProduct.jsp");
			req.getRequestDispatcher("../layout.jsp").forward(req, resp);
		
		}
		protected void doGetChiTiet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id = req.getParameter("sp");
			Product product = productService.getByID(Integer.parseInt(id));
			req.setAttribute("p", product);
			req.setAttribute("viewHome", "admin/admin.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}
		protected void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String idStr = req.getParameter("sp");
			List<Product> list = productService.getAll();
			Product product = null;
			for(Product p : list) {
				if(Integer.parseInt(idStr) == p.getId())  {
					product = p ;
				}
			}
			
			
			String [] size = product.getKichThuoc().split(",");
			String  S = "";
			String  M = "";
			String  L = "";
			String  XL = "";
			String  XXL = "";
			for(int i = 0 ; i < size.length ; i++) {
				System.out.println(size[i]);
				if (size[i].equals("M")) {
					M = "checked";
				}
				if (size[i].equals("S")) {
					S = "checked";
				}
				if (size[i].equals("L")) {
					L = "checked";
				}
				if (size[i].equals("XL")) {
					XL = "checked";
				}
				if (size[i].equals("XXL")) {
					XXL = "checked";
				}
			}
			
			req.setAttribute("S", S);
			req.setAttribute("M", M);
			req.setAttribute("L", L);
			req.setAttribute("XL", XL);
			req.setAttribute("XXL", XXL);
			req.setAttribute("product", product);
			req.setAttribute("viewHome", "admin/updateProduct.jsp");
			req.getRequestDispatcher("../layout.jsp").forward(req, resp);
		
		}
		protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String idStr = req.getParameter("sp");
			
			productService.delete(Integer.parseInt(idStr));
				System.out.println("xoa thanh cong");
				resp.sendRedirect(req.getContextPath()+"/admin");
			
		}
	
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String url = req.getRequestURI();
			if (url.contains("admin/add")) {
				this.addProduct(req, resp);
			}
			else if(url.contains("admin/update")) {
				this.updateProduct(req, resp);
			}
			else {
				req.setAttribute("viewHome", "admin/admin.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
			}
		}

		protected void updateProduct(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String id = req.getParameter("id");
			File han = new File(req.getServletContext().getRealPath("/files"));
			if (!han.exists()) {
				han.mkdirs();
			}
			String anh1 = "";
			Part photo = req.getPart("anh");
			if (photo.getSubmittedFileName().equals("")) {
				Product p = productService.getByID(Integer.parseInt(id));
				anh1 = p.getAnh();
			}
			else {
				File photoFile = new File(han, photo.getSubmittedFileName());
				System.out.println(photoFile);
				photo.write(photoFile.getAbsolutePath());
				anh1 = photoFile.getName();
			}
			
			String tieude = req.getParameter("tieude");
			String mota = req.getParameter("mota");
			String gia = req.getParameter("gia");
			String giamgia = req.getParameter("giamgia");
			String soluong = req.getParameter("soluong");
			String phanloai = req.getParameter("phanloai");
			Category idCate = null;
			List<Category> listC = categoryService.getAll();
			for (Category category : listC) {
				if (category.getTen().equals(phanloai)) {
					idCate = category;
				}
			}
			String[] kichthuoc = req.getParameterValues("kichthuoc");
			String chatlieu = req.getParameter("chatlieu");
			String thietke = req.getParameter("thietke");

			double giaDb = Double.parseDouble(gia);
			BigDecimal giaban = BigDecimal.valueOf(giaDb);
			Integer giam = Integer.parseInt(giamgia);
			Integer soLuong = Integer.parseInt(soluong);
			String mangKT = "";
			for (int i = 0; i < kichthuoc.length; i++) {
				mangKT += kichthuoc[i] + ",";
			}
			Product p = new Product(Integer.parseInt(id), tieude, mota, anh1, giaban, giam, soLuong,
					phanloai, mangKT, chatlieu, thietke, 0, idCate);
			if (productService.update(p, Integer.parseInt(id)) == true) {
				System.out.println("sua thanh cong");
				resp.sendRedirect(req.getContextPath() + "/admin");
			} else {
				System.out.println("sua that bai");
			}
		}
		
		protected void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			File han = new File(req.getServletContext().getRealPath("/files"));
			if(!han.exists()) {
				han.mkdirs();
			}
			Part photo = req.getPart("anh");
			File photoFile = new File(han,photo.getSubmittedFileName());
			photo.write(photoFile.getAbsolutePath());
			String id = req.getParameter("id");
			String tieude = req.getParameter("tieude");
			String mota = req.getParameter("mota");
			String gia = req.getParameter("gia");
			String giamgia = req.getParameter("giamgia");
			String soluong = req.getParameter("soluong");
			String phanloai = req.getParameter("phanloai");
			Category idCate = null;
			List<Category> listC = categoryService.getAll();
			for (Category category : listC) {
				if (category.getTen().equals(phanloai)) {
					idCate = category ;
				}
			}
			String [] kichthuoc = req.getParameterValues("kichthuoc");
			String chatlieu = req.getParameter("chatlieu");
			String thietke = req.getParameter("thietke");
			
			double giaDb = Double.parseDouble(gia);
			BigDecimal giaban = BigDecimal.valueOf(giaDb);
			Integer giam = Integer.parseInt(giamgia);
			Integer soLuong = Integer.parseInt(soluong);
			String mangKT = "";
			for(int i = 0 ; i < kichthuoc.length ; i++) {
				mangKT += kichthuoc[i] + ",";
			}
			
			Product p = new Product(0, tieude, mota,photoFile.getName() , giaban, giam, soLuong, phanloai, mangKT, chatlieu, thietke, 0 ,idCate);
			if(productService.add(p) == true) {
				System.out.println("them thanh cong");
				resp.sendRedirect(req.getContextPath()+"/admin");
			}
			else {
				System.out.println("them that bai");
			}
			
		}
		
}
