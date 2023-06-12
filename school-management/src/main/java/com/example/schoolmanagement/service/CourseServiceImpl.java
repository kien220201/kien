package com.example.schoolmanagement.service;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.repository.CourseRepository;
import com.example.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.schoolmanagement.model.CourseStatistics;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            List<Course> registeredCourses = student.getCourses();
            registeredCourses.add(course);
            student.setCourses(registeredCourses);
            studentRepository.save(student);
        }
    }

    @Override
    public void unregisterCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            List<Course> registeredCourses = student.getCourses();
            registeredCourses.remove(course);
            student.setCourses(registeredCourses);
            studentRepository.save(student);
        }
    }

    @Override
    public List<Course> getRegisteredCourses(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            return student.getCourses();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getStudents();
        }
        return new ArrayList<>();
    }

    @Override
    public CourseStatistics getMostPopularCourses() {
        List<Course> courses = courseRepository.findAll();
        CourseStatistics statistics = new CourseStatistics();

        int maxStudentCount = 0;
        Course mostPopularCourse = null;

        for (Course course : courses) {
            int studentCount = course.getStudents().size();
            if (studentCount > maxStudentCount) {
                maxStudentCount = studentCount;
                mostPopularCourse = course;
            }
        }

        if (mostPopularCourse != null) {
            statistics.setCourse(mostPopularCourse);
            statistics.setStudentCount(maxStudentCount);
        }

        return statistics;
    }
}