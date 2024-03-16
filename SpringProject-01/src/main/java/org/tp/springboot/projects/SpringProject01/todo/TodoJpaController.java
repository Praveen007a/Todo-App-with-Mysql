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

@Controller
@SessionAttributes("name")
public class TodoJpaController {
	
	
	private JpaRepository jpaRepository;

	public TodoJpaController(JpaRepository jpaRepository ) {
		super();
	
		this.jpaRepository=jpaRepository;
	}
	public String getLoggedInUserName(ModelMap model) {
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping("list-todo")
	public String todoListPage(ModelMap model) {
		String username = getLoggedInUserName(model);
		List<Todo> todos = jpaRepository.findByUsername(username);
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
		todo.setUsername(username);
		jpaRepository.save(todo);
		
	//	service.addTodo(username, todo.getDescription(), todo.getTargertDate(), todo.isDone());
		return "redirect:list-todo";

	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		
		jpaRepository.deleteById(id);
		return "redirect:list-todo";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String updateTodo(@RequestParam int id,ModelMap model){
		Todo todo =jpaRepository.findById(id).get();
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
		jpaRepository.save(todo);
		//	service.update(todo);
		return "redirect:list-todo";

	}

}





