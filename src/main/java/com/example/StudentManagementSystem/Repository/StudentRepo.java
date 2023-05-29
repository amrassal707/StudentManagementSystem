package com.example.StudentManagementSystem.Repository;

import com.example.StudentManagementSystem.Entity.Student;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository // not needed since it already extends to JPARepository

public interface StudentRepo extends JpaRepository<Student, Long> {

}
