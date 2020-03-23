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

public class AuthenticationFilter implements Filter {
	
	private Logger logger = LogManager.getLogger(AuthenticationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* The "doFilter" method only accepts an argument of ServletRequest type so we must 
		 * cast this to HttpServletRequest to access the HttpSession instance.  */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		/* Here we check to see if a user is logged in. If this is true then the HTTP request 
		 * proceeds as normal to the specified URL. */
		if (session.getAttribute("currentUser") != null) {
			logger.info(req.getServletPath() + ": User logged in. Valid access to page.");

			chain.doFilter(request, response);
		} else {
			/* If the request gets filtered (i.e., the user is not logged in) then the user 
			 * is directed to the base URL which is the login page.  */
			logger.warn(req.getServletPath() + ": Invalid authentication. User not logged in.");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			HttpServletResponse resp = (HttpServletResponse) response;
			rd.forward(req, resp);
		}
	}

}
