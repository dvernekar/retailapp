package com.noname.retail.usermgmt.dao;

import com.noname.retail.model.usermgmt.User;

public interface UserDAO {

	public User getUser(String login);

}
