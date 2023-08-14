package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final PersonService personService;

    @Autowired
    public StudentController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam UUID id) {
        personService.removePerson(id.toString());
        return "redirect:/student";  // Redirect back to the students list page
    }

    @GetMapping
    public String displayAllStudents(Model model) {
        // Add a new student object to bind to the form
        model.addAttribute("student", new Student());

        // Fetch the list of students to display in the table
        List<Student> allStudents = personService.getAllStudents();

        // Add students to the model to display in the view
        model.addAttribute("students", allStudents);

        return "student"; // assuming "student" is the name of the template displaying the students
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        // Checking if the student object has necessary attributes
        if (student.getName() == null || student.getEmailAddress() == null || student.getPhoneNumber() == null) {
            System.out.println("Student details are incomplete");
            return "error";
        }

        // Check if a student with the same attributes already exists
        if (personService.doesStudentExist(student)) {
            System.out.println("Student with the same details already exists");
            return "error";  // or redirect to another page with a proper message
        }

        // Save the student
        try {
            personService.addPerson(student);
        } catch (Exception e) {
            System.out.println("Error in adding student: " + e.getMessage());
            return "error";
        }

        return "redirect:/student";
    }
}
