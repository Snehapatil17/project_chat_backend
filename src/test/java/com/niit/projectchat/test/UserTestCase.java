/*package com.niit.projectchat.test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.projectchat.dao.UserDAO;
import com.niit.projectchat.model.User;



public class UserTestCase {

	@Autowired
	 static AnnotationConfigApplicationContext context;
	
	
	@Autowired
	static User user;
	
	
	@Autowired
	static UserDAO userDAO;


	
	
	@BeforeClass
	 public static void inti()
	 {
		 context =  new  AnnotationConfigApplicationContext();
		 context.scan("com.niit");
		 context.refresh();
		 
		 userDAO  = (UserDAO) context.getBean("userDAO");
		 
		 user  = (User) context.getBean("user");
		 
		 System.out.println("The object is created");
	 }

	@Test
	public void createUserTestCase()
	{
		user.setId("103");
		
		user.setName("Aditya");
		
		user.setPassword("Aditya");
		
		user.setEmail("adityalimaye@gmail.com");
		
		user.setAddress("Pune");
		
		user.setRole("User");
		
		Boolean status = userDAO.save(user);
		
		Assert.assertEquals("createUserTestcase", true, status);
	}
	
	
	

	



		
	

}
*/