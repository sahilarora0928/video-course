package com.video.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.video.course.entity.Course;
import com.video.course.entity.User;
import com.video.course.repository.CourseRepository;
import com.video.course.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courseRepo;

	@Override
	public String fetchVideoLocation(User user) {
		Course course = courseRepo.findByUser(user);
		return course.getVideoLocation();
	}

	@Override
	public Course fetchCourse(User user) {
		return courseRepo.findByUser(user);
	}

	@Override
	public Course findByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
