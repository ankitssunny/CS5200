package com.neu.edu.cs5200.assignment4.DAO;

import javax.ws.rs.*;

//@Path("/hello")
public class Hello {

	@GET
	@Path("/test")
	public String Test()
	{
		return "This is a test for Web Services Servlet..........";
		
	}
}
