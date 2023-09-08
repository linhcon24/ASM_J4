package com.vn.filter;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class LanguageFilter implements Filter {

	private static final String LANGUAGE_DEFAULT = "vi";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httRequest = (HttpServletRequest) request;
		String lang = httRequest.getParameter("language");
		if (lang != null) {
			httRequest.getSession().setAttribute("lang", lang);
		} 
		if(httRequest.getSession().getAttribute("lang") == null){
			httRequest.getSession().setAttribute("lang", LANGUAGE_DEFAULT);
		}
		chain.doFilter(httRequest, response);
	}

}
