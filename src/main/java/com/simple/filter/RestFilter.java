package com.simple.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		chain.doFilter(servletRequest, response);
		String url = "";
		if (servletRequest instanceof HttpServletRequest) {
			url = ((HttpServletRequest) servletRequest).getRequestURL().toString();
		}
		if (!url.startsWith("resources") && !url.startsWith("/resources")) {
			response.addHeader("Set-Cookie", "HttpOnly;Secure");
		}
		response.addHeader("X-Frame-Options", "DENY");
	}

	@Override
	public void destroy() {
	}

}
