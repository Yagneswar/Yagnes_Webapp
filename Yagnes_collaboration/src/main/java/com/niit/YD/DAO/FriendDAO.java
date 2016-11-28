package com.niit.YD.DAO;

import java.util.List;

import com.niit.YD.Model.Friend;

public interface FriendDAO {
	
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public void delete(String userID, String friendID);
	public List<Friend> getMyFriends(String userID);
	public List<Friend> getNewFriendRequests(String userID);
	public Friend get(String userID, String friendID);
	public void setOnline(String userID);
	public void setOffLine(String userID);
	

}
