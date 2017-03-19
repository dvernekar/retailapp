package com.noname.retail.usermgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.noname.retail.model.usermgmt.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	public User getUser(String login) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.user_login = :login");
		query.setParameter("user_login", login);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

}
