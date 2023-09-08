package com.vn.filter;

import java.io.IOException;

import com.vn.model.Account;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/admin/*", "/users/*", "/category/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse repo, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpServletResponse httpServletResponse = (HttpServletResponse) repo;

		Account account = (Account) httpServletRequest.getSession().getAttribute("account");
		if (account == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
			return;
		}
		if (account.getRole() == 1) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
			return;
		}
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

}
