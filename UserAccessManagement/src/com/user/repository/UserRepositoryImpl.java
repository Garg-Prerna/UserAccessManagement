package com.user.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.User;

/**
 * @author Prerna Garg
 *
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User createUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		return user;
	}

	@Override
	public void deleteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = session.load(User.class, new Integer(id));
		if (null != user) {
			session.delete(user);
		} else {
			throw new UsernameNotFoundException(String.valueOf(id));
		}
	}

	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from User").list();
		return userList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where id=:id");
		query.setParameter("id", id);
		List<User> users = query.list();
		return users.size() > 0 ? users.get(0) : null;
	}
}
