package com.video.course.core.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UserCheckerUtil {

	public static String createAndSaveImage(MultipartFile file, String checkerImageUploadedFolder) {
		String uploadedFilePath = checkerImageUploadedFolder + file.getOriginalFilename();
		try {
			if (file.isEmpty()) {
				return "";
			}
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadedFilePath);
			Files.write(path, bytes);
			return uploadedFilePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFilePath;
	}

}
