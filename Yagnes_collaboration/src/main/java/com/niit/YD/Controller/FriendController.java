package com.niit.YD.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.YD.DAO.FriendDAO;
import com.niit.YD.Model.Friend;

@RestController
public class FriendController {

	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;

	@RequestMapping(value = "/myFriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session) {
		logger.debug("->->->->calling method getMyFriends");
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");

		logger.debug("getting friends of : " +loggedInUserID);
		List<Friend> myFriends = friendDAO.getMyFriends(loggedInUserID);
		
		if(myFriends.isEmpty())
		{
			logger.debug("Friends does not exsit for the user : " +loggedInUserID);
			friend.setErrorCode("404");
			friend.setErrorMessage("You does not have any friends");
			myFriends.add(friend);
		}
		logger.debug("Send the friend list ");
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session) {
		logger.debug("->->->->calling method sendFriendRequest");
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus("N"); //N - New, R->Rejected, A->Accepted
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}


	@RequestMapping(value = "/unFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID,HttpSession session) {
		logger.debug("->->->->calling method unFriend");
		updateRequest(friendID, "U", session);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	private void updateRequest(String friendID, String status,HttpSession session)
	{
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		friend.setUserID(loggedInUserID);
		friend.setFriendID(friendID);
		friend.setStatus(status); //N - New, R->Rejected, A->Accepted
		friendDAO.update(friend);
		
	}

	@RequestMapping(value = "/getMyFriendRequests/", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequests(HttpSession session) {
		logger.debug("->->->->calling method getMyFriendRequests");
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		List<Friend>  myFriendRequests =   friendDAO.getNewFriendRequests(loggedInUserID);
		return new ResponseEntity<List<Friend>>(myFriendRequests, HttpStatus.OK);
		
	}
   
	@RequestMapping(value = "/accepttFriend/{friendID}", method = RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session) {
		logger.debug("->->->->calling method acceptFriendFriendRequest");
		
		updateRequest(friendID, "A", session);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	


	
}
