package com.video.course.service;

import com.video.course.entity.Course;
import com.video.course.entity.User;

public interface CourseService {
	Course findByUser(String userId);
	
	Course fetchCourse(User user);
	
	String fetchVideoLocation(User user);
	
}
