package org.tp.springboot.projects.SpringProject01.login;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

 @Controller
 @SessionAttributes("name")
public class WellcomeController {


	@RequestMapping(value="/",method=RequestMethod.GET)
	public String gologin(ModelMap model){
		model.put("name", getLoggedInUserName());
		return "wellcome";
	}
	
	public String getLoggedInUserName() {
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
