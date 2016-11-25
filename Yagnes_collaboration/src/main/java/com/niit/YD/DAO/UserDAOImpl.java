package com.niit.YD.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.YD.Model.User;



@Repository("userDAO")
public class UserDAOImpl implements  UserDAO {
	
	 private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
		
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	User user;
	
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		log.debug("Starting of the method  UserDAOImpl");
		this.sessionFactory = sessionFactory;
		log.debug("Ending of the method  UserDAOImpl");
		
	}
	
	
	
	
	
	@Transactional
	public boolean save(User user) {
		log.debug("Starting of the method  save");
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	@Transactional
	public boolean update(User user) {
		log.debug("Starting of the method  update");
		log.debug("update the user " + user);
		try {
			sessionFactory.openSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	@Transactional
	public User get(String id) {
		log.debug("Starting of the method  get with the id :"+ id);
		
		user =	(User)sessionFactory.getCurrentSession().get(User.class, id);
		
		log.debug("user:" + user);
				return user;
		
		
		
	}

	@Transactional
	public List<User> list() {
		log.debug("Starting of the method  list");
		String hql = "from User";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  UserDAOImpl");
		return  query.list();
		
		
	}

	@Transactional
	public User validate(String id, String password) {
		log.debug("Starting of the method  validate");
		//select * from User where id= ' Srinivas' and password = 'Srinivas'
		String hql = "from User  where id= '" + id +"' and password = '" + password+"'";
		
		Query query=  sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending of the method  UserDAOImpl");
		return  (User)query.uniqueResult();
		
		
	}

}

