package com.example.StudentManagementSystem.Controller;

import com.example.StudentManagementSystem.Entity.Student;
import com.example.StudentManagementSystem.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@RestController // you can't use RestController because it will return a string not a view
@Controller // in order to deal with MVC pattern, use @Controller
public class StudentController {

    private StudentService studentService;
    //No need for @AutoWired since we only have one constructor
    public StudentController(StudentService studentService) {
        this.studentService= studentService;
    }

    // handler method that will handle requests and return mode and view
    @GetMapping("/students")
    public String listStudents(Model model) {
        // returns a list of students by adding them to a model and return that model to the frontend and to the students page
        List<Student> studentList= new ArrayList<>();
        studentList= studentService.getAllStudents();

        Collections.sort(studentList,
                Student.StuNameComparator);
        model.addAttribute("students", studentList);
        // return a view of this method
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        // create student object to hold student data from the form page
        Student student= new Student();
        model.addAttribute("student", student); // it's an empty attribute but will set the form's data to match with your entity's data
        // returns a view
         return "create_student"; // a form page in which you will add student data

    }

    @PostMapping("/students")
    // binding the data that will come from the front-end to our entity student by using @modelAttribute
    public String saveStudent(@ModelAttribute("student") Student student) {
            studentService.saveStudent(student);
            // redirect to the student's page
            return "redirect:/students";
    }
    @GetMapping("students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.findById(id));


        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String editStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/students";

    }
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

}
