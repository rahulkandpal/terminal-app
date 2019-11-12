package com.terminal.pkg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.terminal.pkg.beans.Journal;
import com.terminal.pkg.rk.Manage;

@SpringBootApplication
public class TerminalAppApplication {

	//static public String fileLocation;
	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx=SpringApplication.run(TerminalAppApplication.class, args);
		//fileLocation=args[0];
		Manage m1= ctx.getBean(Manage.class);
		m1.start();		
	}	

}
