package com.niit.YD.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.YD.DAO.UserDAO;
import com.niit.YD.DAO.UserDAOImpl;

import com.niit.YD.Model.User;

@RestController
public class UserController {
	
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);
		

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;

	// http://locahost:8080/collaboration/tusers
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		log.debug("Starting of the method getAllUsers" );
		List<User> users = userDAO.list();

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No Users are available");
		}
		log.debug("Ending of the method getAllUsers" );
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// http://locahost:8080/collaboration/tuser/srinivas
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserDetails(@PathVariable("id") String id) {
		log.debug("Starting of the method getUserDetails" );
		user = userDAO.get(id);
		
		if (user== null) {
           user = new User();
			user.setErrorCode("404"); // NLP NullPointerException
			user.setErrorMessage("Iser does not exist with this id :" + id);
		}
		log.debug("Ending of the method getUserDetails" );
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/validate/", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
		log.debug("Starting of the method login" );
		
		String id = user.getId();
		 user = userDAO.validate(user.getId(), user.getPassword());
		if (user == null) {
			user = new User();
			user.setErrorCode("404"); // NLP NullPointerException
			user.setErrorMessage("User does not exist with this id:" + id);
		} else {
			user.setIsOnline('Y');
			user.setErrorCode("200"); // NLP NullPointerException
			user.setErrorMessage("You have successfully logged");
	
			userDAO.update(user);

			session.setAttribute("loggedInUserID", user.getId());
		}
		log.debug("Ending of the method login" );
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		log.debug("Starting of the method logout" );
		
		String loggedInUserID= (String) session.getAttribute("loggedInUserID");
		
		log.debug("loggedInUserID : " + loggedInUserID);
		
		user =userDAO.get(loggedInUserID);
		
		log.debug("user:"+ user);
		user.setIsOnline('N');

		session.invalidate();

		if (userDAO.update(user)) {
			user.setErrorCode("200");
			user.setErrorMessage("You have logged out successfully");
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("You could not logged. please contact admin");
		}
		log.debug("Ending of the method logout" );
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user) {
		log.debug("Starting of the method register" );
		if (userDAO.get(user.getId()) != null) {
			user.setErrorCode("404");
			user.setErrorMessage("With this id, the record is already exist.  Please choose another id");
		} else {
			user.setStatus('N');
			if (userDAO.save(user)) {
				user.setErrorCode("200");
				user.setErrorMessage("Your Registration is Successfull");

			} else {
				user.setErrorCode("405");
				user.setErrorMessage("Unable process your registration. Please contact Admin");
			}
		}
		log.debug("Ending of the method register" );
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("id") String id) {
		log.debug("Starting of the method register" );
		
		  
			user = updateStatus(id, 'A',"");
			
			return new  ResponseEntity<User>(user,HttpStatus.OK);
			
		
		
		
	}
	
	
	@RequestMapping(value = "/reject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("id") String id ,@PathVariable("reason")  String reason) {
		log.debug("Starting of the method register" );
		
		 
		user = updateStatus(id, 'R', reason);
		
	
		
		return new  ResponseEntity<User>(user,HttpStatus.OK);
		
		
		
	}
	
	
	private User updateStatus(String id, char status, String reason)
	{
		log.debug("Starting of the method updateStatus" );
		
		log.debug("status: " + status);
	  user =userDAO.get(id);
	  
	  if(user==null)
	  {
		  user = new User();
		  user.setErrorCode("404");
		  user.setErrorMessage("Could not update the status");
	  }
	  else
	  {
			
		  user.setStatus(status);
		  user.setReason(reason);
		  userDAO.update(user);
	  }
	  log.debug("Ending of the method updateStatus" );
	return user;
	  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

