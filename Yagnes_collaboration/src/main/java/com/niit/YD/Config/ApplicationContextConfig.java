package com.niit.YD.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.YD.DAO.BlogDAO;
import com.niit.YD.DAO.BlogDAOImpl;
import com.niit.YD.DAO.UserDAO;
import com.niit.YD.DAO.UserDAOImpl;
import com.niit.YD.Model.Blog;
import com.niit.YD.Model.Friend;
import com.niit.YD.Model.User;



@Configuration
@ComponentScan("com.niit.YD")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("COLB_DB"); //Schema name
		dataSource.setPassword("root");

		Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("hibernate.dialect",
				   "org.hibernate.dialect.Oracle10gDialect");
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		//sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);

		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		/*sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);


		sessionBuilder.addAnnotatedClass(ChatForum.class);
		sessionBuilder.addAnnotatedClass(ChatForumComment.class);
		sessionBuilder.addAnnotatedClass(User.class);*/

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDetailsDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}
	
	
	@Autowired
	@Bean(name = "friendDAO")
	public BlogDAO getFriendDetailsDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}


}
