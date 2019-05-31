package com.video.course.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.video.course.entity.User;

public interface UserService {
	
	User findUserById(Long id);
	
	Optional<User> fetchUser(String userId);
	
	byte[] fetchUserImage(String userId);
	
    User createUser(User user, MultipartFile image);

    User updateUser(User user, MultipartFile image, User userFromJson);
}
