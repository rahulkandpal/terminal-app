package com.terminal.pkg.rk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.terminal.pkg.beans.Journal;
import com.terminal.pkg.beans.User;

@Component
public class LogJournal {

	private User user;
	private HashSet<User> userList;

	private	Manage m;	
	String path="e:/terminal/UserDetails.txt";

	@Autowired
	public void setM(Manage m) {
		this.m = m;
	}
	
	public void loadJounalOption(User u,HashSet<User> list)
	{
		user=u;
		userList=list;
		Scanner s1 = new Scanner(System.in);
		System.out.println("******************** ");
		System.out.println("1. New Entry");
		System.out.println("2. See Last Entries");
		System.out.println("3. Go back to previous Menu");
		System.out.println("******************** ");
		System.out.println("Select the option : ");		
		String option1= s1.next();
		switch(option1)
		{
			case "1": addJournal();
			break;
			case "2": getJournal();
			break;
			case "3": m.start();
			break;
			default:
				System.out.println("Wrong Option Selected! try Again");
				loadJounalOption(user,userList);
		}
	}

	public void addJournal()
	{

		List<Journal> loj=null;
		Scanner s1 = new Scanner(System.in);
		System.out.println("Type your journal: ");		
		String text= s1.nextLine();
		
		Journal j = new Journal(text);
	
		if(user.getMyJournals().size()==5)
			user.getMyJournals().remove(0);
		
			loj=user.getMyJournals();
    		loj.add(j);
    		user.setMyJournals(loj);
    		m.serialize(userList, user);    		
	        loadJounalOption(user,userList);
		
	}
	
	void getJournal() {
		user.getMyJournals().forEach(e->System.out.println(e.getDt()+" - "+e.getText()));
		loadJounalOption(user,userList);
	}
	
}
