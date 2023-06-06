package com.example.schoolmanagement.model;

public class CourseStatistics {
    private Course course;
    private int studentCount;

    public CourseStatistics() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
