package com.video.course.core;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(updatable = false, name = "creation_time_stamp")
    @CreationTimestamp
    private Calendar creationTimeStamp;

    @Column(name = "updation_time_stamp")
    @UpdateTimestamp
    private Calendar updationTimeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Calendar creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public Calendar getUpdationTimeStamp() {
        return updationTimeStamp;
    }

    public void setUpdationTimeStamp(Calendar updationTimeStamp) {
        this.updationTimeStamp = updationTimeStamp ;
    }
}
