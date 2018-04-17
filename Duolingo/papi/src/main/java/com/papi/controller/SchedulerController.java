package com.papi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.papi.dao.SchedulerDao;
import com.papi.entity.Conversation;
import com.papi.entity.User;
import com.papi.utility.PWScheduler;
import com.papi.wrapper.PWSchedulerWrapper;


@RestController
@RequestMapping("/schedule")
public class SchedulerController {
	@Autowired
	SchedulerDao schedulerDao;
	@RequestMapping()
	public String welcomePage() {
		return "Welcome to Scheduler.";
	}
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void getGroup(@RequestBody PWSchedulerWrapper schedule) {
	// TODO Auto-generated method stub
		Timer t1= new Timer();
		PWScheduler ss = new PWScheduler();
		
		Conversation conv = new  Conversation();
		conv.setDescription(schedule.getDescription());
		conv.setScheduleDate(schedule.getScheduleDate());
		schedulerDao.addConversation(conv);
		//String scDate= "31/03/2018 5:57 PM";
		String scDate = schedule.getScheduleDate();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		
		Date date = null;
		try {
			date = format.parse(scDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		t1.schedule(ss, date);
	}
	@RequestMapping(value = "/sharemessage", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void sharemessage(@RequestBody Conversation conversation) {
		schedulerDao.addConversation(conversation);
	}
	@RequestMapping(value = "/getmessagelist/{groupId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Conversation> getConversationListForGroup(@PathVariable("groupId") Long groupId) {
		System.out.println("getConversationListForGroup");

		return schedulerDao.getAllConversationForGroup(groupId);
	}
}
	
