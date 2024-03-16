package org.tp.springboot.projects.SpringProject01.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity

public class Todo {
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String description;
	private LocalDate targertDate;
	private boolean done;	
	
	public Todo() {
		
	}
	
	public Todo(int id, String username, String description, LocalDate targertDate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targertDate = targertDate;
		this.done = done;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getTargertDate() {
		return targertDate;
	}
	public void setTargertDate(LocalDate targertDate) {
		this.targertDate = targertDate;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targertDate="
				+ targertDate + ", done=" + done + "]";
	}
	
	
}
