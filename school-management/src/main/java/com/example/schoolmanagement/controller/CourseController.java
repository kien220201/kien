package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
}