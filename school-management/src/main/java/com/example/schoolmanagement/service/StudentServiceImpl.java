    package com.example.schoolmanagement.service;

    import com.example.schoolmanagement.model.Course;
    import com.example.schoolmanagement.model.Student;
    import com.example.schoolmanagement.repository.CourseRepository;
    import com.example.schoolmanagement.repository.StudentRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class StudentServiceImpl implements StudentService {
        private final StudentRepository studentRepository;
        private final CourseRepository courseRepository;

        @Override
        public List<Course> getAvailableCourses(Long studentId) {
            Student student = studentRepository.findById(studentId).orElse(null);
            List<Course> allCourses = courseRepository.findAll();

            if (student != null) {
                List<Course> registeredCourses = student.getCourses();
                allCourses.removeAll(registeredCourses);
            }

            return allCourses;
        }

        @Autowired
        public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
            this.studentRepository = studentRepository;
            this.courseRepository = courseRepository;
        }

        @Override
        public List<Student> getAllStudents() {
            return studentRepository.findAll();
        }

        @Override
        public Student getStudentById(Long studentId) {
            return studentRepository.findById(studentId).orElse(null);
        }

        @Override
        public void addStudent(Student student) {
            studentRepository.save(student);
        }

        @Override
        public void updateStudent(Student student) {
            studentRepository.save(student);
        }

        @Override
        public void deleteStudent(Long studentId) {
            studentRepository.deleteById(studentId);
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
        public List<Course> getAllCourses() {
            return courseRepository.findAll();
        }

        @Override
        public Student getStudent(Long studentId) {
            return studentRepository.findById(studentId).orElse(null);
        }

    }
