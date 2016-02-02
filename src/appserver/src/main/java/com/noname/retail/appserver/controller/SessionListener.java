package com.noname.retail.appserver.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.noname.retail.appserver.token.OAuthTokenConstant;
import com.noname.retail.logger.RetailLogger;;


public class SessionListener implements HttpSessionListener {

    Logger m_logger = LoggerFactory.getLogger(SessionListener.class);

	
	public static final String SESSION_MAP = "sessionMap";

	@Override
    public void sessionCreated(HttpSessionEvent event) {
    	HttpSession session = event.getSession();
		ServletContext context = session.getServletContext();
		@SuppressWarnings("unchecked")
		Map<String,HttpSession> sessionMap = (Map<String,HttpSession>) context.getAttribute(SESSION_MAP);
		if(sessionMap == null){
			 sessionMap = new ConcurrentHashMap<String, HttpSession>(16, 0.9f, 1);
		}
		//Put sessionId & session Object pair into a map for later use.
		sessionMap.put(session.getId(), session);
		context.setAttribute("sessionMap", sessionMap);
		RetailLogger.info(m_logger, String.format("A new session with sessionId %s has been created", session.getId()));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    	try{
    		HttpSession session = event.getSession();
    		String sessionID = session.getId();
    		String clientId = (String) session.getAttribute(OAuthTokenConstant.CLIENT_ID);
    		RetailLogger.info(m_logger, String.format("Session %s is destroyed", sessionID));

    		// Remove session info in the session map before invalidate the session
    		ServletContext context = session.getServletContext();
    		@SuppressWarnings("unchecked")
    		Map<String,HttpSession> sessionMap = (Map<String,HttpSession>) context.getAttribute(SESSION_MAP);
    		if(sessionMap != null){
    			sessionMap.remove(sessionID);
    		}    	
    	}catch(Exception e){
    		RetailLogger.error(m_logger, "Cannot connect to MongoDB,the current access token won't be removed..");
    	}
    }

}
