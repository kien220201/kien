package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/{id}")
    public String getCourseById(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetails";
    }

    @PostMapping
    public String addCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute("course") Course course) {
        course.setId(id);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/{courseId}/register/{studentId}")
    public String registerStudentForCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.registerStudentForCourse(courseId, studentId);
        return "redirect:/courses/" + courseId;
    }

    @DeleteMapping("/{courseId}/unregister/{studentId}")
    public String unregisterStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.unregisterStudentFromCourse(courseId, studentId);
        return "redirect:/courses/" + courseId;
    }

    @GetMapping("/{studentId}/courses")
    public String getRegisteredCoursesByStudentId(@PathVariable Long studentId, Model model) {
        CourseController studentService = null;
        List<Course> courses = studentService.getRegisteredCoursesByStudentId(studentId);
        model.addAttribute("courses", courses);
        return "studentRegisteredCourses";
    }

    @GetMapping("/{courseId}/students")
    public String getStudentsByCourseId(@PathVariable Long courseId, Model model) {
        List<Student> students = courseService.getStudentsByCourseId(courseId);
        model.addAttribute("students", students);
        return "courseStudents";
    }

    @GetMapping("/mostPopular")
    public String getMostPopularCourses(Model model) {
        List<Course> courses = courseService.getMostPopularCourses();
        model.addAttribute("courses", courses);
        return "mostPopularCourses";
    }
}