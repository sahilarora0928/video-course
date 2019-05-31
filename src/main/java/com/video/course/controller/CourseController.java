package com.video.course.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.video.course.entity.Course;
import com.video.course.entity.User;
import com.video.course.service.CourseService;
import com.video.course.service.UserService;

@Controller
@RequestMapping("/v1/course/")
public class CourseController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	@GetMapping(value = "{userId}/info")
	public ResponseEntity<?> fetchCourse(@PathVariable("userId")String userId){
		Optional<User> user = userService.fetchUser(userId);
		Course course = courseService.fetchCourse(user.get());
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userId}/video")
	public ResponseEntity<?> fetchVideoLocation(@PathVariable("userId")String userId){
		byte[] contents = null;
        try {
        	Optional<User> user = userService.fetchUser(userId);
        	String videoFilePath = courseService.fetchVideoLocation(user.get());
            File file = new File(videoFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            contents = new byte[(int) file.length()];
            fileInputStream.read(contents);
            return new ResponseEntity<byte[]>(contents, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(contents, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}
