package com.noname.retail.appserver.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;*/
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.noname.retail.appserver.token.OAuthTokenConstant;
import com.noname.retail.model.usermgmt.User;
import com.noname.retail.util.EncryptionUtils;

public class LoginUtil {
	
	public static final String ACCOUNT_ADMIN = "accountAdmin";
	public static final String NETWORK_ADMIN = "networkAdmin";
	public static final String CAN_READ = "canRead";
	public static final String CAN_WRITE = "canWrite";
	public static final GrantedAuthority roleRead = new SimpleGrantedAuthority(Role.ROLE_READ.toString());
	public static final GrantedAuthority roleWrite = new SimpleGrantedAuthority(Role.ROLE_WRITE.toString());
	public static final GrantedAuthority roleNetAdmin = new SimpleGrantedAuthority(Role.ROLE_NETADMIN.toString());
	public static final GrantedAuthority roleAdmin = new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString());
	public static final String AUTH_STATUS = "auth-status";
	public static final int AUTH_ERROR = 440;
	

   /* private static MongoTemplate mongoTemplate;
    
    
	public static void setMongoTemplate(MongoTemplate mongoTemplate) {
		LoginUtil.mongoTemplate = mongoTemplate;
	}*/

    /*
     * get user by username from MongoDB
     */
    public static User getUserDetail(String username) {
      /*  MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
        User user = mongoOperation.findOne(new Query(Criteria.where("login").is(username)), User.class);
        //DeCryption password
*/      User user = new User();
		user.setPassword("");
		user.setPassword("");
    	if(user != null){
        	user.setPassword(EncryptionUtils.decrypt(user.getPassword()));
        	user.setPassword(EncryptionUtils.decrypt(user.getPassword()));
        }
        
        return user;
    }

    
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
    	Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					cookies[i].setValue("");
					cookies[i].setMaxAge(0);
					cookies[i].setPath(OAuthTokenConstant.COOKIE_PATH);
					response.addCookie(cookies[i]);
					break;
				}
			}
		}
    }
    
    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static boolean isAdminRole(){
    	boolean isAdmin = false;
    	try{
    		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		isAdmin = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    	}catch(Exception ex){

    		return isAdmin;
    	}
    	return isAdmin;

    }
}
