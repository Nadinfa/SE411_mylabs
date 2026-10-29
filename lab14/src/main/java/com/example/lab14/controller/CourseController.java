
package com.example.lab14.controller;

import com.example.lab14.model.Course;
import com.example.lab14.repository.CourseRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course) {

        Course newCourse = new Course(
                null,
                course.code(),
                course.name(),
                course.description(),
                course.credits()
        );

        Course saved = repository.save(newCourse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // RETRIEVE ALL
    @GetMapping
    public Iterable<Course> getAllCourses() {
        return repository.findAll();
    }

    // RETRIEVE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Course updated = new Course(
                id,
                course.code(),
                course.name(),
                course.description(),
                course.credits()
        );

        return ResponseEntity.ok(
                repository.save(updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
