package com.video.course.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.video.course.entity.Course;
import com.video.course.entity.User;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	Course findByUser(User user);
}
