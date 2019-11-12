package com.terminal.pkg.beans;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

public class Journal implements Comparator<Journal>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private LocalDateTime dt;
	
	public Journal(){}
	
	public Journal(String text){
		this.text=text;
		dt=LocalDateTime.now();
	}
	
	@Override
	public int compare(Journal l1,Journal l2)
	{
		if(l1.getDt().isAfter(l2.getDt()))
			return 1;
		else if(l1.getDt().isBefore(l2.getDt()))
			return -1;
		else
			return 0;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}
	
	
}
