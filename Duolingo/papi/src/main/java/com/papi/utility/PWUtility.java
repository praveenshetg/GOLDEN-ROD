package com.papi.utility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.papi.entity.User;

public class PWUtility {

	// This Method returns set of email id of groups
	public List<String> getUserEmailIdOfGroups(List<Long> groups) {

		return new ArrayList<String>();
	}

	public Date formatSchedulerDate(String date) {

		return new Date(0);
	}

	public static List<User> createUserFromUserEmail(List<String> users) {
		List<User> usList = new ArrayList<User>();
		for (String emailId : users) {
			String username = generateUserName(emailId);
			String password = generateRandomPasswordForUser(username);
			
			System.out.println("user Created for "+emailId+" with username : "+username+ " and password : "+password);
			//User user = new User(username, password, emailId);
		}

		usList.add(new User());
		return usList;
	}

	public static String generateRandomPasswordForUser(String username) {
		StringBuilder password = new StringBuilder();
		 password.append(shuffle(username.substring(0,username.length()-2)));
		 password.append(generateRandomNumber().toString());
		
				return password.toString();

	}

	public static String generateUserName(String emailId) {
		String regex = "(\\w+)@";
		String username = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(emailId);
        if(m.find())
        	username =  m.group(1);
        else{
        	username="PWU#"+generateRandomNumber().toString();
        }
        //if username exists in DB re generate new one
		return username;
	}

	public User createUserbyEmailId(String emailId) {

		return new User();
	}

	public static Integer generateRandomNumber() {
		// create instance of Random class
		Random rand = new Random();

		// Generate random integers in range 0 to 9999
		return rand.nextInt(10000);
	}
	
	public static String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
       return output.toString();
    }
}
