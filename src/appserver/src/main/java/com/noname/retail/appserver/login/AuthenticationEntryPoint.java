package com.noname.retail.appserver.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.noname.retail.appserver.token.OAuthTokenConstant;


public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	public AuthenticationEntryPoint(String loginUrl) {
		super(loginUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String url = request.getRequestURI();
		// Get Root web app path
		String contextPath = request.getContextPath() + "/";
		LoginUtil.removeCookie(request, response, OAuthTokenConstant.ACCESS_TOKEN);
		//Error occurs when sending license request to ovlicenseservice
		if (authException.getCause() != null && authException.getCause() instanceof NGAuthenticationException) {
			response.setHeader(LoginUtil.AUTH_STATUS, authException.getMessage());
			response.sendError(LoginUtil.AUTH_ERROR, authException.getMessage());
		} else if (url.equalsIgnoreCase(contextPath) || url.contains(".html")) { 			// Redirect all unauthorized html requests to login page
			response.sendRedirect(contextPath + "login.html");								
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");		// Send  401 code to all unauthorized RESTful APIs
		}

	}
}