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

@WebFilter(urlPatterns = { "/cart", "/change"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse repo, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpServletResponse httpServletResponse = (HttpServletResponse) repo;

		Account accounts = (Account) httpServletRequest.getSession().getAttribute("account");
		if (accounts == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
			return;
		}
		chain.doFilter(httpServletRequest, httpServletResponse);

	}

}
