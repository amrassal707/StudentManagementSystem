package com.example.StudentManagementSystem.Service;

import com.example.StudentManagementSystem.Entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student findById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);

}
