package com.terminal.pkg.rk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Manage {
	
	private	LogJournal j;
	
	String username,password,path="e:/terminal/UserDetails.txt";
	
	@Autowired
	public void setJ(LogJournal j) {
		this.j = j;
	}
	
	public void start() {
		
		Scanner s = new Scanner(System.in);
		System.out.println("******************** ");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. See Users");
		System.out.println("0. Exit");
		System.out.println("******************** ");
		System.out.println("Select the option : ");
	
		String option1= s.next();
		
		switch(option1)
		{
			case "1": login();
			break;
			case "2": signUp();
			break;
			case "3": getUsers();
			break;
			case "0": System.exit(0);
			default:
				System.out.println("Wrong Option Selected");
				//Manage.start();
		}
	}
	
	public  void login() {
		
		HashSet<User> usersList=null;
		boolean flag=false;
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Username:");
		username=s1.next();
		System.out.println("Enter Password:");
		password=s1.next();
		
		usersList=deserialize();
		if(usersList==null)
		{
			System.out.println("Record Not Exists");
			start();
		}
	        
        for(User u:usersList)
        {
        	if((u.getUsername().equals(username))&&(u.getPassword().equals(password)))
        		{
        		  j.loadJounalOption(u,usersList);
        		  flag=true;
        	      break;
        	    }
        }
        
        if(!flag)
    	{
    		System.out.print("Wrong username or password");
    		start();
    	}
	}

	public  void signUp(){
		
		HashSet<User> userList=null;
		
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Username:");
		username=s1.next();
		System.out.println("Enter Password:");
		password=s1.next();
		User u = new User(username,password);
		//u.setMyjournal(new Journal());
		u.setMyJournals(new ArrayList<Journal>());
		userList=deserialize();
		if(userList==null)
		{
			serialize(userList,u);
	        j.loadJounalOption(u,userList);
	        return;
		}
		if(userList.size()>=10)
        {
        	System.out.println("Only 10 users are allowed. "+userList.size()+"has been registed. Capacity is full !!!!! ");
        	start();		        	
        }
        else 
        {
		 	serialize(userList,u);
	        j.loadJounalOption(u,userList);
        }
	}
	
	public void getUsers()
	{
		try {
			  HashSet<User> users=deserialize();
	        if(users!=null)
	        users.forEach(e->System.out.println(e.getUsername()+":"+e.getPassword()));
	        else
	        	System.out.print("no records to show");
	       
			start();
			
		}catch(Exception e)
		{
			//e.printStackTrace();
			System.out.print("no records to show");
			start();
		}
	}
	
	void serialize(HashSet<User> userList,User u)
	{
		try
		{
			if(userList==null)
				userList=new HashSet<User>();
			FileOutputStream fos=new FileOutputStream(path);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);
	        userList.add(u);
	        oos.writeObject(userList);
	        oos.flush();
	        oos.close();
	        fos.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 HashSet<User> deserialize()
	{
		ObjectInputStream ois=null;
		HashSet<User> userList=null;
		FileInputStream fis=null;
		
		 try{
				 fis=new FileInputStream(path);
				 ois=new ObjectInputStream(fis);
				 userList=(HashSet<User>)ois.readObject();
				 ois.close();
			     fis.close();
		   }catch(Exception e) {
			   //e.printStackTrace();
		   }     
			
		return userList;
	 }
	
}

