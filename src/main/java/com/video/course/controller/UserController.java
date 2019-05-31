package com.video.course.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.video.course.core.util.TransformJsonUtil;
import com.video.course.entity.User;
import com.video.course.service.UserService;

@Controller
@RequestMapping("/v1/course/")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PostMapping(value = "/user")
	public ResponseEntity<?> createUser(
			@RequestParam(value = "user", required = false) String userJson,
			@RequestParam("file") MultipartFile image) {
		Map<String, String> responseMap = new HashMap<>();
		if (StringUtils.isEmpty(userJson) || image == null) {
			responseMap.put("responseMessage", "User json or Image invalid");
			responseMap.put("location", null);
			return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User userFromJson = TransformJsonUtil.jsonStringToObject(User.class, userJson);
		User user = null;
		User newUser;
		Long userId = userFromJson.getId();
		if (userId != null) {
			user = userService.findUserById(userId);
		}

		if (user != null) {
			newUser = userService.updateUser(user, image, userFromJson);
		} else {
			newUser = userService.createUser(userFromJson, image);
		}
		if (newUser != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newUser.getId()).toUri();
			responseMap.put("responseMessage", "Success");
			responseMap.put("location", location.toString());
			responseMap.put("id", Long.toString(newUser.getId()));
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} else {
			responseMap.put("responseMessage", "Failure");
			responseMap.put("location", null);
			return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<?> findUser(@PathVariable("userId") String userId) {
		return new ResponseEntity(userService.fetchUser(userId).get(), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userId}/image", produces = { "image/jpg", "image/jpeg", "image/png" })
	public ResponseEntity<?> fetchUserImage(@PathVariable("userId") String userId) {
		byte[] contents = userService.fetchUserImage(userId);
		return new ResponseEntity<>(contents, HttpStatus.OK);
	}
}
