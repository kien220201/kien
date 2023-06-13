package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.model.Course;
import com.example.schoolmanagement.model.Student;
import com.example.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student-details";
    }

    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudents";
    }


    @PostMapping
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/edit-student/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-students";
    }


    @PostMapping("/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student updatedStudent) {
        Student student = studentService.getStudentById(id);
        student.setId(id);
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        studentService.updateStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @PostMapping("/{studentId}/register/{courseId}")
    public String registerCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.registerCourse(studentId, courseId);
        return "redirect:/students/" + studentId + "/registeredCourses";
    }

    @DeleteMapping("/{studentId}/unregister/{courseId}")
    public String unregisterCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.unregisterCourse(studentId, courseId);
        return "redirect:/students/" + studentId + "/registeredCourses";
    }

    @GetMapping("/{studentId}/registeredCourses")
    public String getRegisteredCourses(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudent(studentId); // Sửa thành getStudent
        List<Course> registeredCourses = studentService.getRegisteredCourses(studentId);
        List<Course> availableCourses = studentService.getAvailableCourses(studentId);
        model.addAttribute("student", student);
        model.addAttribute("registeredCourses", registeredCourses);
        model.addAttribute("availableCourses", availableCourses);
        return "registeredCourses";
    }


}