package org.tp.springboot.projects.SpringProject01.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	private TodoService service;
	public TodoController(TodoService service) {
		super();
		this.service=service;
	}
	public String getLoggedInUserName(ModelMap model) {
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping("list-todo")
	public String todoListPage(ModelMap model) {
		String username = getLoggedInUserName(model);
		List<Todo> todos = service.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "listTodo";
	}
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String goTodo(ModelMap model) {
		String username=getLoggedInUserName(model);
		Todo todo = new Todo(0,username," ",LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		return "todo";
	}
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addTodo(@Valid Todo todo,ModelMap model,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username=getLoggedInUserName(model);
		service.addTodo(username, todo.getDescription(), todo.getTargertDate(), false);
		return "redirect:list-todo";

	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		
		service.deleteTodo(id);
		return "redirect:list-todo";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String updateTodo(@RequestParam int id,ModelMap model){
		Todo todo =service.updateTodo(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String update(@Valid Todo todo,ModelMap model,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username=(String)model.get("name");
		todo.setUsername(username);
		service.update(todo);
		return "redirect:list-todo";

	}

}





