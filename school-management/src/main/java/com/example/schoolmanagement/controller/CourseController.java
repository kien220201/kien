package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.CourseService;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private LogManager enrollmentRepository;

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

    @GetMapping("/add-course")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "addCourses";
    }

    @PostMapping
    public String addCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit-course/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "edit-courses";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course updatedCourse) {
        Course course = courseService.getCourseById(id);
        course.setId(id);
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        courseService.updateCourse(course);
        return "redirect:/courses";
    }



    @GetMapping("/{id}/delete")
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

    @GetMapping("/courses/{studentId}")
    public String getRegisteredCoursesByStudentId(@PathVariable Long studentId, Model model) {
        List<Course> courses = courseService.getRegisteredCourses(studentId);
        model.addAttribute("courses", courses);
        return "registered_courses";
    }

    @GetMapping("/{courseId}/students")
    public String getStudentsByCourseId(@PathVariable Long courseId, Model model) {
        List<Student> students = courseService.getStudentsByCourseId(courseId);
        model.addAttribute("students", students);
        return "courseStudents";
    }

    @GetMapping("/mostPopular")
    public String getMostPopularCourses(Model model) {
        List<Course> courses = (List<Course>) courseService.getMostPopularCourses();
        model.addAttribute("courses", courses);
        return "mostPopularCourses";
    }

    @GetMapping("/enrolled-students/{courseId}")
    public ResponseEntity<List<Student>> getEnrolledStudents(@PathVariable Long courseId) {
        List<Student> enrolledStudents = new ArrayList<>();

        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            List<Student> students = course.getStudents();
            enrolledStudents.addAll(students);
        }

        return ResponseEntity.ok(enrolledStudents);
    }

}