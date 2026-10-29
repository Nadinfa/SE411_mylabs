package com.example.lab14.repository;

import com.example.lab14.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository
        extends CrudRepository<Course, Long> {
}
