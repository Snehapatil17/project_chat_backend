package com.niit.projectchat.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.projectchat.dao.UserDAO;
import com.niit.projectchat.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	public SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {

		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 List<User> userList = query.list();
		 System.out.println("Checking for list of users");
		 return userList;
	}

	@Transactional
	public boolean save(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public User get(String id) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	@Transactional
	public User validate(String id, String password) {

		String hql = "from User where id = '" + id + "'and password = '" + password + "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		User user = (User) query.uniqueResult();

		return user;
	}

}
