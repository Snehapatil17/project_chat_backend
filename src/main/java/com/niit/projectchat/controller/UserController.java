package com.niit.projectchat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.projectchat.dao.UserDAO;
import com.niit.projectchat.model.User;

@RestController
public class UserController {

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;
	
	/*@Autowired
	HttpSession httpsession;*/

	// http://localhost:8080/Project_chat_Backend/users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> users = userDAO.list();

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No user are available");
			//users.add(user);
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	// http://localhost:8080/Project_chat_Backend/users/id/password
	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user,HttpSession httpsession)
	{
		user = userDAO.validate(user.getId(),user.getPassword());
		
		if(user==null)
		{
			user = new User(); // To avoid NLP
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Creditials.... Please try again");
		}
		else
		{
			httpsession.setAttribute("loggedInUserId", user.getId());
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user)
	{
		
		
		if(userDAO.save(user)==false)
		{
			user = new User(); // To avoid NLP
			user.setErrorCode("404");
			user.setErrorMessage("the registration is not success plz try after some time");
		}
		else
		{
			user.setErrorCode("200");
			user.setErrorMessage("thank you for registration");
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/update/", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user)
	{
		
		
		if(userDAO.update(user)==false)
		{
			
			user.setErrorCode("404");
			user.setErrorMessage("the update is not success plz try after some time");
		}
		else
		{
			user.setErrorCode("200");
			user.setErrorMessage("Updated successfuly");
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
		
}
