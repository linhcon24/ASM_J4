package com.vn.controller;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.vn.model.Account;
import com.vn.model.Cart;
import com.vn.model.HoaDon;
import com.vn.model.HoaDonChiTiet;
import com.vn.model.Product;
import com.vn.service.AccountService;
import com.vn.service.CartService;
import com.vn.service.HoaDonChiTietService;
import com.vn.service.HoaDonService;
import com.vn.service.IAccountService;
import com.vn.service.ICartService;
import com.vn.service.IHoaDonChiTietService;
import com.vn.service.IHoaDonService;
import com.vn.service.IProductService;
import com.vn.service.ProductService;
import com.vn.unlities.MailSender;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(value = { "/cart", "/cart/add", "/cart/delete","/cart/them", "/cart/giam", "/cart/thanhtoan" })
public class CartServlet extends HttpServlet {
	private IAccountService accountService = new AccountService();
	private IProductService productService = new ProductService();
	private IHoaDonService hoaDonService = new HoaDonService();
	private IHoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
	private ICartService cartService = new CartService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("cart/delete")) {
			String id = req.getParameter("id");
			String kichthuoc = req.getParameter("kichThuoc");
			
			Account user = (Account) req.getSession().getAttribute("account");
			/*
			 * System.out.println(user.getUsername());
			 * System.out.println(Integer.parseInt(id)); System.out.println(kichthuoc);
			 */
			cartService.deleteCart(user.getUsername(), Integer.parseInt(id), kichthuoc);
			
			resp.sendRedirect(req.getContextPath() + "/cart");

		} else if (url.contains("cart/them")) {
			String id = req.getParameter("id");
			String kichThuoc = req.getParameter("kichThuoc");
			
		
			Account user = (Account) req.getSession().getAttribute("account");
			System.out.println(user.getUsername());
			System.out.println(Integer.parseInt(id));
			System.out.println(kichThuoc);
			Cart cart = cartService.getCart(user.getUsername(), Integer.parseInt(id), kichThuoc);
			List<Product> products = productService.getAll();

			cartService.updateCart(user.getUsername(), Integer.parseInt(id), cart.getSoLuong() + 1, kichThuoc);
			resp.sendRedirect(req.getContextPath() + "/cart");

		} else if (url.contains("cart/giam")) {
			String id = req.getParameter("id");
			String kichThuoc = req.getParameter("kichThuoc");
			Account user = (Account) req.getSession().getAttribute("account");

			Cart cart = cartService.getCart(user.getUsername(), Integer.parseInt(id), kichThuoc);
			if (cart.getSoLuong() <= 1) {
				cartService.deleteCart(user.getUsername(), Integer.parseInt(id), kichThuoc);
				resp.sendRedirect(req.getContextPath() + "/cart");
				return;
			}
			System.out.println(cart.getSoLuong());
			cartService.updateCart(user.getUsername(), Integer.parseInt(id), cart.getSoLuong() - 1, kichThuoc);
			resp.sendRedirect(req.getContextPath() + "/cart");

		}
		else {
			Account user = (Account) req.getSession().getAttribute("account");
			if (user == null) {
				req.setAttribute("viewHome", "cart.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
				return;
			}
			Double tongTien = 0.0;
			List<Cart> listCart = cartService.getAllByUsername(user.getUsername());
			for (Cart cart : listCart) {
				String donGia=  String.valueOf(cart.getDonGia());
				Double donGiadb = Double.parseDouble(donGia);
				tongTien += (cart.getSoLuong() * donGiadb);
			}
			req.setAttribute("tongTien", tongTien);
			req.setAttribute("listCart", listCart);
			req.setAttribute("viewHome", "cart.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			
		}

	}
	protected void doGetCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Account user = (Account) req.getSession().getAttribute("account");
		if (user == null) {
			req.setAttribute("viewHome", "cart.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		Double tongTien = 0.0;
		List<Cart> listCart = cartService.getAllByUsername(user.getUsername());
		for (Cart cart : listCart) {
			String donGia=  String.valueOf(cart.getDonGia());
			Double donGiadb = Double.parseDouble(donGia);
			tongTien += (cart.getSoLuong() * donGiadb);
		}
		req.setAttribute("tongTien", tongTien);
		req.setAttribute("listCart", listCart);
		
		req.setAttribute("viewHome", "cart.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tennguoinhan = req.getParameter("tennguoinhan");
		String sdt = req.getParameter("sdt");
		String diachi = req.getParameter("diachi");
		String ghiChu = req.getParameter("ghichu");
		
		Account user = (Account) req.getSession().getAttribute("account");
		List<Cart> carts = cartService.getAllByUsername(user.getUsername());
		
		if (carts.isEmpty()) {
			req.setAttribute("messageGioHang", "Giỏ hàng của bạn chưa có gì!");
			req.setAttribute("typeGioHang", "error");
			return;
		}
		if (tennguoinhan.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
			req.setAttribute("messageGioHang", "Thông tin giao hàng không được bỏ trống!");
			req.setAttribute("typeGioHang", "error");
			doGetCart(req, resp);
			return;
		}
		
		List<Product> products = productService.getAll();
		for (Product p : products) {
			List<Cart> carts1 = cartService.getAllByUsername(user.getUsername());
			for (Cart c : carts1) {
				if (c.getProduct().getId() == p.getId() ) {
					if (c.getSoLuong() > p.getSoLuong() && c.getKichThuoc().equals(p.getKichThuoc())) {
						req.setAttribute("messageGioHang", "Số lượng trong giỏ hàng vượt quá số lượng sản phẩm!");
						req.setAttribute("typeGioHang", "error");
						return;
					}
				}
			}
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		ZonedDateTime now = ZonedDateTime.now();
		String date = dtf.format(now);
		
		HoaDon hoaDon = new HoaDon();
		hoaDon.setUsername(user);
		hoaDon.setTrangThai(0);
		hoaDon.setNguoiNhan(tennguoinhan);
		hoaDon.setDiaChi(diachi);
		hoaDon.setSoDienThoai(sdt);
		hoaDon.setNgayTao(date);
		hoaDon.setNgayTao(date);
		hoaDonService.add(hoaDon);
		
		for (Cart cart : carts) {
			HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(0, hoaDon, cart.getProduct(), cart.getSoLuong(), cart.getDonGia(), cart.getKichThuoc());
			hoaDonChiTietService.add(hoaDonChiTiet);
			cartService.deleteCart(cart.getUsername().getUsername(), cart.getProduct().getId(), cart.getKichThuoc());
		}
		
		req.setAttribute("messageGioHang", "Đặt hàng thành công! ");
		req.setAttribute("typeGioHang", "success");
		doGetCart(req, resp);
		}
//		
//		
//		Account account = null;
//		List<Account> listAcc = accountService.getAll();
//		for (Account account2 : listAcc) {
//			if (account2.getUsername().equals(username)) {
//				account = account2;
//			}
//		}
//		Product product = null;
//		List<Product> listPro = productService.getAll();
//		for (Product product2 : listPro) {
//			if (Integer.parseInt(id) == product2.getId()) {
//				product = product2;
//			}
//		}
//		System.out.println(kichThuoc);
//
//		System.out.println(product.getTieuDe());
//		System.out.println(account.getUsername());
//
//		Cart cart = new Cart(0, account, product, 1, product.getGia(), kichThuoc);
//		if (cartService.add(cart) == true) {
//			System.out.println("Them vao gio thanh cong");
//		} else {
//			System.out.println("Them vao gio that bai");
//		}
//
//		resp.sendRedirect(req.getContextPath() + "/detail?sanpham=" + id);
//	}
}
