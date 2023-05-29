package com.example.StudentManagementSystem.Service;

import com.example.StudentManagementSystem.Entity.Student;
import com.example.StudentManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepo studentRepo;
    @Autowired // injecting dependency , annotation not required if we only have one constructor
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo=studentRepo;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll(); // returns a list of students
    }

    @Override
    public Student findById(Long id) {
        // findById method returns an optional , in order to return the same entity type, just add .get()
        return studentRepo.findById(id).get();
    }

    @Override
    public Student saveStudent(Student student) {
        return  studentRepo.save(student);

    }
    @Override
    public Student updateStudent(Student student) {
        //Student student1= studentRepo.findById(student.getId()).get();
        // you can remove this function since JPA repo is smart enough to detect an update is happening and just use save student and call the save functions
        return studentRepo.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
         studentRepo.deleteById(id);
    }

}
