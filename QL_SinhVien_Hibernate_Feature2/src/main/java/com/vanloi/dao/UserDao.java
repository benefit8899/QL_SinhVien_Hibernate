package com.vanloi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vanloi.model.Student;
import com.vanloi.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from User").list();
		return userList;
	}

	@SuppressWarnings("rawtypes")
	public User getUserByUserName(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		
		//HQL
//		Query query = session.createQuery("from User where user_name=:userName");
//		query.setString("userName", userName);
//		List results = query.list();
		
		//Criteria
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("userName", userName));
		List results = criteria.list();
		
		//SQLQuery
		session.createSQLQuery("SELECT * FROM user WHERE user_name = ").addEntity("customer", User.class);
		
		return results.size() > 0 ? (User) results.get(0) : null;
	}

	public User getUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, new Integer(id));
		return user;
	}

	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		return user;
	}

	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
	}

	public void deleteUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User) session.load(User.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
}