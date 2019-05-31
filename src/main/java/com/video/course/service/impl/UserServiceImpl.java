package com.video.course.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.video.course.core.util.UserCheckerUtil;
import com.video.course.entity.User;
import com.video.course.repository.UserRepository;
import com.video.course.service.UserService;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
    @Value("${user.image.path}")
    private String USER_IMAGE_UPLOADED_FOLDER;
    
    @Override public User createUser(User user, MultipartFile image) {
        try {
            String imageFilePath = UserCheckerUtil
                    .createAndSaveImage(image, USER_IMAGE_UPLOADED_FOLDER);
            user.setUserImageFilePath(imageFilePath);
            return userRepo.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override public User updateUser(User user, MultipartFile image, User userFromJson) {
        try {
            String imageFilePath = user.getUserImageFilePath();
            if (image != null) {
                imageFilePath = UserCheckerUtil
                        .createAndSaveImage(image, USER_IMAGE_UPLOADED_FOLDER);
            }
            BeanUtils.copyProperties(userFromJson, user, "applicants", "active", "imagePath");
            user.setUserImageFilePath(imageFilePath);
            return userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	@Override
	public Optional<User> fetchUser(String userId) {
		return userRepo.findById(Long.parseLong(userId));
	}

	@Override
	public byte[] fetchUserImage(String userId) {
		Optional<User> imagePath = userRepo.findById(Long.parseLong(userId));
		InputStream in = null;
		byte[] contents = null;
		try {
			in = new FileInputStream(imagePath.get().getUserImageFilePath());
			return IOUtils.toByteArray(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

}
