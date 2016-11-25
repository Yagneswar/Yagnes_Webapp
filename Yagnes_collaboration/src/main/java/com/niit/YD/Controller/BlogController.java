package com.niit.YD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.YD.DAO.BlogDAO;
import com.niit.YD.Model.Blog;

@Controller
@RequestMapping(value="/blog") 
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;
	
	@RequestMapping(value="/allblogs", method=RequestMethod.GET)
	@ResponseBody
	public List<Blog> findAllBlog(){
		System.out.println(blogDAO.getAllBlogs());
		return blogDAO.getAllBlogs();
	}


	
}
