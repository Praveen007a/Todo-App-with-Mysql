package org.tp.springboot.projects.SpringProject01.todo;

import java.util.List;

public interface JpaRepository extends 
			org.springframework.data.jpa.repository.JpaRepository<Todo, Integer>{

	List<Todo> findByUsername(String username);
	

}
