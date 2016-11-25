package com.niit.YD.DAO;

import java.util.List;

import com.niit.YD.Model.User;

public interface UserDAO {
	
	public boolean save(User user);
	public boolean update(User user);
	public User get(String id);
	public List<User> list();
	public User validate(String id, String password);

}
