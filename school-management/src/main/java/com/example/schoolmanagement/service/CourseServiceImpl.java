package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.CourseStatistics;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void registerStudentForCourse(Long courseId, Long studentId) {

    }

    @Override
    public void unregisterStudentFromCourse(Long courseId, Long studentId) {

    }

    @Override
    public void registerCourse(Long studentId, Long courseId) {

    }

    @Override
    public void unregisterCourse(Long studentId, Long courseId) {

    }

    @Override
    public List<Course> getRegisteredCourses(Long studentId) {
        return null;
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return null;
    }

    @Override
    public CourseStatistics getMostPopularCourses() {
        return null;
    }
}