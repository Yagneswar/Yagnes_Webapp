package com.niit.YD.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.YD.Model.Blog;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Blog blog;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		
		this.sessionFactory = sessionFactory;
		
		
	}
	
	
	@Override
	public List<Blog> getAllBlogs() {

		return sessionFactory.getCurrentSession().createQuery("from Blog").list();

	}

	@Override
	public void insertBlog(Blog b) {

		sessionFactory.getCurrentSession().persist(b);
	}

	@Override
	public void deleteBlogById(int blogId) {
		
		sessionFactory.getCurrentSession().delete(getBlogById(blogId));

	}

	@Override
	public void updateBlog(Blog blog) {
		
		sessionFactory.getCurrentSession().update(blog);


	}

	@Override
	public Blog getBlogById(int blogId) {
		Blog blog = sessionFactory.getCurrentSession().get(Blog.class, new Integer(blogId));
		return blog;

	}

	@Override
	public List<Blog> getBlogByOwnerId(String ownerId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Blog WHERE blogOwnerId=?");
		query.setParameter(0, ownerId);
		return query.list();

	}

}
