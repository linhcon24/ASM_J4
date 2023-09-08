package com.vn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vn.model.Account;
import com.vn.model.Cart;
import com.vn.model.Category;
import com.vn.model.Product;
import com.vn.service.AccountService;
import com.vn.service.CartService;
import com.vn.service.CategoryService;
import com.vn.service.IAccountService;
import com.vn.service.ICartService;
import com.vn.service.ICategoryService;
import com.vn.service.IProductService;
import com.vn.service.ProductService;
import com.vn.unlities.CookieUntil;
import com.vn.unlities.MailSender;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = { "/index", "", "/login", "/product", "/detail", "/register", "/forget", "/logout", "/change",
		"/info" })
public class HomeController extends HttpServlet {
	private IProductService productService = new ProductService();
	private IAccountService accountService = new AccountService();
	private ICategoryService categoryService = new CategoryService();
	private ICartService cartService = new CartService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		if (url.contains("login")) {
			req.removeAttribute("messageLogin");
			req.removeAttribute("typeLogin");
			doGetLogin(req, resp);
		} else if (url.contains("product")) {
			doGetProduct(req, resp);
		} else if (url.contains("detail")) {
			doGetDetail(req, resp);
		} else if (url.contains("logout")) {
			doGetLout(session, req, resp);
		} else if (url.contains("register")) {
			req.removeAttribute("messageRegister");
			req.removeAttribute("typeRegister");
			doGetRegister(req, resp);
		} else if (url.contains("forget")) {
			doGetForget(req, resp);
		} else if (url.contains("change")) {
			doGetChange(req, resp);
		} else if (url.contains("info")) {
			doGetInfo(req, resp);
		}
		else {
			List<Product> count = productService.getAll();
			Integer cont = count.size() / 8;
			if (count.size() % 8 != 0) {
				cont++;
			}
			
			String page = req.getParameter("page");
			if (page == null) page = "1"; 			
			List<Product> list = productService.getAllPhanTrang(8,Integer.parseInt(page));
			List<Product> newList = new ArrayList<>();
			for (Product pro : list) {
				if (pro.getTrangThai() == 0) {
					newList.add(pro);
				}
			}
			req.setAttribute("count", cont);
			req.setAttribute("listProduct", newList);
			req.setAttribute("viewHome", "index.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}

	}

	protected void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "login.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("viewHome", "register.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "info.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "change.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("viewHome", "forget.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> listCate = categoryService.getAll();
		req.setAttribute("listCategory", listCate);
		List<Product> list = productService.getAll();
		List<Product> newList = new ArrayList<>();
		for (Product pro : list) {
			if (pro.getTrangThai() == 0) {
				newList.add(pro);
			}
		}
		req.setAttribute("listProduct", newList);
		req.setAttribute("viewHome", "product.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void doGetLout(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		session.removeAttribute("account");
		resp.sendRedirect("index");
	}

	protected void doGetDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("sanpham");
		List<Product> list = productService.getAll();
		Product product = null;
		for (Product p : list) {
			if (Integer.parseInt(idStr) == p.getId()) {
				product = p;
			}
		}

		req.setAttribute("p", product);
		req.setAttribute("viewHome", "detail.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		if (url.contains("login")) {
			doPostLogin(session, req, resp);
		} else if (url.contains("register")) {
			doPostRegister(req, resp);
		} else if (url.contains("change")) {
			doPostChange(session, req, resp);
		} else if (url.contains("forget")) {
			doPostForget(req, resp);
		} else if (url.contains("detail")) {
			doPostDetail(req, resp);
		} else {
			req.setAttribute("viewHome", "index.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
		}

	}

	protected void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("checkbox");
		Account account = accountService.getByUsername(username);
		if (username.isEmpty()) {
			req.setAttribute("messageLogin", "Username không được bỏ trống !");
			req.setAttribute("typeLogin", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("viewHome", "login.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (password.isEmpty()) {
			req.setAttribute("messageLogin", "Password không được bỏ trống !");
			req.setAttribute("typeLogin", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("viewHome", "login.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (account == null) {
			  req.setAttribute("messageLogin", "Tài khoản hoặc mật khẩu không đúng !");
			  req.setAttribute("typeLogin", "error");
			  req.setAttribute("username",username);
			  req.setAttribute("password", password);
			  req.setAttribute("viewHome", "login.jsp");
			  req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;

		}
		
		else {
			if (account.getRole() == 2) {
				req.setAttribute("messageLogin", "Tài khoản đã bị khóa !");
				req.setAttribute("typeLogin", "error");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("viewHome", "login.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);

			} 
			else {
				if (account.getPassword().equals(password)) {
					session.setAttribute("account", account);
					int hours = (remember == null) ? 0 : 1*24;
					CookieUntil.add("username", username, hours, resp);
					CookieUntil.add("password", password, hours, resp);
					req.setAttribute("messageLogin", "Đăng nhập thành công !");
					req.setAttribute("typeLogin", "success");
					resp.sendRedirect("index");
				}
			
				 else {
					req.setAttribute("messageLogin", "Tài khoản hoặc mật khẩu không đúng !");
					req.setAttribute("typeLogin", "error"); req.setAttribute("username",username);
					req.setAttribute("password", password);
					req.setAttribute("viewHome", "login.jsp");
					req.getRequestDispatcher("layout.jsp").forward(req, resp);

				}
			}
		}
		
	}

	protected void doPostForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		if (username.isEmpty()) {
			req.setAttribute("messageForget", "Username không được bỏ trống !");
			req.setAttribute("typeForget", "error");
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "forget.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (email.isEmpty()) {
			req.setAttribute("messageForget", "Email không được bỏ trống !");
			req.setAttribute("typeForget", "error");
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "forget.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		Account account = accountService.getByUsername(username);
		if (account == null) {
			req.setAttribute("messageForget", "Username không tồn tại !");
			req.setAttribute("typeForget", "error");
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "forget.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (!email.equals(account.getEmail())) {
			req.setAttribute("messageForget", "Username và Email không khớp !");
			req.setAttribute("typeForget", "error");
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "forget.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		MailSender mailSender = new MailSender();
		mailSender.SendMail(username, email);
		req.setAttribute("messageForget", "Mật khẩu mới đã được cập nhật. Vui lòng kiểm tra Email của bạn !");
		req.setAttribute("typeForget", "success");
		req.setAttribute("viewHome", "forget.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	protected void doPostChange(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Account account = (Account) session.getAttribute("account");
		String passcu = req.getParameter("password-cu");
		String passmoi = req.getParameter("password-moi");
		String repassmoi = req.getParameter("re-password-moi");
		if (passcu.isEmpty()) {
			req.setAttribute("messageChange", "Password cũ không được bỏ trống !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (passmoi.isEmpty()) {
			req.setAttribute("messageChange", " Password mới không được bỏ trống !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (repassmoi.isEmpty()) {
			req.setAttribute("messageChange", "Repass mới không được bỏ trống !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		
		if (!account.getPassword().equals(passcu)) {
			req.setAttribute("messageChange", "Password cũ chưa chính xác !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (passmoi.length() < 8 || passmoi.length() > 16) {
			req.setAttribute("messageChange", "Độ dài Password mới trong khoảng từ 8 đến 16 kí tự !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (!passmoi.equals(repassmoi)) {
			req.setAttribute("messageChange", "Password mới và Repassword chưa khớp !");
			req.setAttribute("typeChange", "error");
			req.setAttribute("passcu", passcu);
			req.setAttribute("passmoi", passmoi);
			req.setAttribute("repassmoi", repassmoi);
			req.setAttribute("viewHome", "change.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		accountService.doiMatKhau(account.getUsername(), passmoi);
		req.setAttribute("messageChange", "Đổi mật khẩu thành công !");
		req.setAttribute("typeChange", "success");
		req.setAttribute("viewHome", "change.jsp");
		req.getRequestDispatcher("layout.jsp").forward(req, resp);

	}

	protected void doPostRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("re-password");
		String email = req.getParameter("email");
		String checkbox = req.getParameter("checkbox");
		if (username.equals("")) {
			req.setAttribute("messageRegister", "Vui lòng Username không được bỏ trống !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (password.equals("")) {
			req.setAttribute("messageRegister", "Vui lòng Password không được bỏ trống !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (repassword.equals("")) {
			req.setAttribute("messageRegister", "Vui lòng Repassword không được bỏ trống !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if ( email.equals("")) {
			req.setAttribute("messageRegister", "Vui lòng Email không được bỏ trống !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (username.length() < 6 || username.length() > 10) {
			req.setAttribute("messageRegister", "Độ dài Username nằm trong khoảng từ 6 đến 10 kí tự !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (password.length() < 8 || password.length() > 16) {
			req.setAttribute("messageRegister", "Độ dài Password nằm trong khoảng từ 8 đến 16 kí tự !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (!password.equals(repassword)) {
			req.setAttribute("messageRegister", "Password và Re-Password chưa khớp !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (email.length() > 50) {
			req.setAttribute("messageRegister", "Độ dài Email nhỏ hơn 50 kí tự !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		if (checkbox == null) {
			req.setAttribute("messageRegister", "Bạn chưa đồng ý với điều khoản và dịch vụ !");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			return;
		}
		List<Account> list = accountService.getAll();
		for (Account account : list) {
			if (account.getUsername().equals(username)) {
				req.setAttribute("messageRegister", "Username đã tồn tại trên hệ thống !");
				req.setAttribute("typeRegister", "error");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("repassword", repassword);
				req.setAttribute("email", email);
				req.setAttribute("viewHome", "register.jsp");
				req.getRequestDispatcher("layout.jsp").forward(req, resp);
				return;
			}
		}
		Account account = new Account(username, password, email, 1);
		if (accountService.add(account) == true) {
			req.setAttribute("messageRegister", "Đăng ký thành công!");
			req.setAttribute("typeRegister", "success");
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);
			
		} else {
			req.setAttribute("messageRegister", "Đăng ký thất bại");
			req.setAttribute("typeRegister", "error");
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("repassword", repassword);
			req.setAttribute("email", email);
			req.setAttribute("viewHome", "register.jsp");
			req.getRequestDispatcher("layout.jsp").forward(req, resp);

		}

	}

	protected void doPostDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String idProduct = req.getParameter("idProduct");
		String kichThuoc = req.getParameter("kichThuoc");
		
		System.out.println(idProduct);
		System.out.println(kichThuoc);
		
		Account account =  null;
		List<Account> listAcc = accountService.getAll();
		for (Account account2 : listAcc) {
			if (account2.getUsername() .equals(username)) {
				account = account2;
			}
		}
		
		Product product = null;
		List<Product> listPro = productService.getAll();
		for (Product product2 : listPro) {
			if (Integer.parseInt(idProduct) == product2.getId()) {
				product = product2;
			}
		}
		
		if (account == null) {
			req.setAttribute("messageCart", "Vui lòng đăng nhập trước khi vào giỏ hàng!");
			req.setAttribute("typeCart", "error");
			doGetDetail(req, resp);
			return;
		}
		
		if (kichThuoc == null) {
			req.setAttribute("messageCart", "Vui lòng chọn size trước khi thêm vào giỏ hàng!");
			req.setAttribute("typeCart", "error");
			doGetDetail(req, resp);
			return;
		}
		
		Account user = (Account) req.getSession().getAttribute("account");
		List<Cart> carts = cartService.getAllByUsername(user.getUsername());
		List<Product> products = productService.getAll();
		for (Cart cart : carts) {
				if (cart.getProduct().getId() == product.getId() && cart.getKichThuoc().equals(kichThuoc)) {
					cartService.updateCart(account.getUsername(), product.getId(), cart.getSoLuong() + 1, kichThuoc);
					req.setAttribute("messageCart", "Thêm vào giỏ hàng thành công!");
					req.setAttribute("typeCart", "success");
					doGetDetail(req, resp);
					return;
				}
		}

		Cart cart = new Cart(0, account, product, 1, product.getGia(), kichThuoc);
		if (cartService.add(cart) == true) {
			req.setAttribute("messageCart", "Thêm vào giỏ hàng thành công!");
			req.setAttribute("typeCart", "success");
			doGetDetail(req, resp);
		}
		
		else {
			req.setAttribute("messageCart", "Thêm vào giỏ hàng thất bại");
			req.setAttribute("typeCart", "success");
			doGetDetail(req, resp);
		}
		
		}

	}
//	protected void doGetAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//			List<Product> list = productService.getAll();
//			req.setAttribute("listProduct", list);
//	
//		req.getRequestDispatcher("admin.jsp").forward(req, resp);
//	}


