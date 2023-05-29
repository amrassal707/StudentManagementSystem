package com.example.StudentManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Comparator;

@Entity // by using this annotation JPA will detect this class as an entity
@Table(name = "students") // if not provided , jpa will create a table with the same name of the entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @Data has all the above mentioned functions from allargs down to getters
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // letting JPA know that SQL will handle id creation
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
// hibernate needs a default constructor since it uses proxy to create instances, solved by using @noargs constructors, you can also write public student()


    // to be able to sort students by their firstname using comparator functions when added to list.
public static Comparator<Student> StuNameComparator = new Comparator<Student>() {

    // Comparing attributes of students
    public int compare(Student s1, Student s2) {
        String StudentName1
                = s1.getFirstName().toUpperCase();
        String StudentName2
                = s2.getFirstName().toUpperCase();

        // Returning in ascending order
        return StudentName1.compareTo(
                StudentName2);

        // descending order
        // return
        // StudentName2.compareTo(StudentName1);
    }
};


}
