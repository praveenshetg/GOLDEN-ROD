package com.papi.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.papi.dao.GroupDao;
import com.papi.dao.UserDao;
import com.papi.entity.Group;
import com.papi.entity.User;

@RestController
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	GroupDao groupDao;
	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public HashMap<String, HashMap<String,String>> login(@RequestBody User user) {
		logger.info("Inside Method : login()");
		
		HashMap<String,HashMap<String,String>> validUserDetails = new HashMap<String,HashMap<String,String>>();
		HashMap<String,String> error = new HashMap();
		
		User userExists = userDao.getUserByMailIdAndPassword(user.getoEmail(), user.getPassword());
		
		if(userExists!=null){
			validUserDetails = getLoggedInUserDetails(user.getToken(), userExists, validUserDetails);
		}else{
			error.put("loginValid", "true");
			error.put("error", "true");
			error.put("error-type", "INVALID_USER");
			validUserDetails.put("error", error);
		}
		return validUserDetails;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public HashMap<String, HashMap<String,String>> loginCheck(@RequestBody User user) {
		logger.info("Inside Method : loginCheck()");
		User userExists = userDao.getUserByToken(user.getToken());
		HashMap<String,HashMap<String,String>> validUserDetails = new HashMap<String,HashMap<String,String>>();
		HashMap<String,String> error = new HashMap();
		if(userExists!=null && user.getToken().equals(userExists.getToken())){
			validUserDetails = getLoggedInUserDetails(user.getToken(), userExists, validUserDetails);
		}else{
			error.put("loginValid", "false");
			error.put("error", "true");
			error.put("error-type", "INVALID_TOKEN");
			validUserDetails.put("error", error);
		}
		return validUserDetails;
		//return userExists!=null && user.getToken().equals(userExists.getToken()) ? true : false;
	}
	
	public HashMap<String, HashMap<String,String>> getLoggedInUserDetails(String token, User userExists, HashMap<String, HashMap<String,String>> validUserDetails){

		Group group = groupDao.getGroup(userExists.getGroup_id());
		logger.info("User Exists : "+ userExists.getId());
		HashMap<String,String> userDetail = new HashMap();
		HashMap<String,String> groupDetail = new HashMap();
		HashMap<String,String> error = new HashMap();
		
		
		userExists.setToken(token);
		userDao.updateUser(userExists);
	
		userDetail.put("id", userExists.getId().toString());
		userDetail.put("username", userExists.getUsername());
		userDetail.put("token", userExists.getToken());
		userDetail.put("dob", "");
		userDetail.put("role_name", userExists.getRole_name());
		userDetail.put("fName", userExists.getfName());
		userDetail.put("lName", userExists.getlName());
		userDetail.put("organization", userExists.getOrganization());
		userDetail.put("oEmail", userExists.getoEmail());
		userDetail.put("pEmail", userExists.getpEmail());
		userDetail.put("gender", userExists.getGender());
		userDetail.put("aboutYou", userExists.getAboutYou());
		
		groupDetail.put("id", group.getId().toString());
		groupDetail.put("name", group.getName());
		groupDetail.put("category", group.getCategory());
		groupDetail.put("status", group.getStatus());
		//groupDetail.put("user_count", group.getUser_count().toString());
		groupDetail.put("user_count","0");

		validUserDetails.put("user", userDetail);
		validUserDetails.put("group", groupDetail);
		error.put("error", "false");
		validUserDetails.put("error", error);
		
	  return validUserDetails;
	}
}