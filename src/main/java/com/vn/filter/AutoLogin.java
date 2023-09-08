package com.vn.filter;

import java.io.IOException;

import com.vn.model.Account;
import com.vn.service.AccountService;
import com.vn.service.IAccountService;
import com.vn.unlities.CookieUntil;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/", dispatcherTypes = DispatcherType.REQUEST)
public class AutoLogin implements Filter {

	private IAccountService accountService = new AccountService();

	@Override
	public void doFilter(ServletRequest req, ServletResponse reps, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String username = CookieUntil.get("username", httpRequest);
		String password = CookieUntil.get("password", httpRequest);

		Account account = (Account) httpRequest.getSession().getAttribute("account");
		if (account == null) {
			if (username != null && password != null) {
				Account acc = accountService.getByUsername(username);
				httpRequest.getSession().setAttribute("account", acc);
			}
		}

		chain.doFilter(req, reps);

	}
}
