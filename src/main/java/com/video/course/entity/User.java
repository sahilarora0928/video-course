package com.video.course.entity;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.video.course.core.BaseEntity;
import com.video.course.enums.UserStatusEnum;

@Entity
@Table(name = "user")
public class User extends BaseEntity {


	@Column(name = "name")
	private String name;

	@Column(name = "dob")
	private Calendar dob;

	@Column(name = "email")
	private String email;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "gender")
	private String gender;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	Set<Course> courses;

	@Column(name = "user_image_file_path")
	private String userImageFilePath;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "status")
	private UserStatusEnum status;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String getUserImageFilePath() {
		return userImageFilePath;
	}

	public void setUserImageFilePath(String userImageFilePath) {
		this.userImageFilePath = userImageFilePath;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	

}
