package com.perscholas.springboot_basic_web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perscholas.springboot_basic_web.models.User;

public class AuthorizationFilter implements Filter {
	
	private Logger logger = LogManager.getLogger(AuthorizationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		User currentUser = (User) session.getAttribute("currentUser");
		/* This filter prevents access to an admin page unless the user is logged in and the 
		 * user's userRole is set to "admin". */
		if (currentUser != null && currentUser.getUserRole().equals("admin")) {
			logger.info(req.getServletPath() + ": Valid access to admin page. User is authorized as admin.");
			chain.doFilter(request, response);
		} else {
			/* If the user is not an admin then the request gets forwarded to the homePage. */
			logger.warn(req.getServletPath() + ": Invalid authorization. User not authorized as administrator.");
			RequestDispatcher rd = request.getRequestDispatcher("/homePage");
			HttpServletResponse resp = (HttpServletResponse) response;
			rd.forward(req, resp);
		}
	}
}
