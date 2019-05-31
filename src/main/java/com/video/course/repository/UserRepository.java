package com.video.course.repository;

import org.springframework.data.repository.CrudRepository;

import com.video.course.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findById(String id);
	
}
