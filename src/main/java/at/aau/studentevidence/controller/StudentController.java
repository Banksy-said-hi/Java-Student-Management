package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.persistence.InMemoryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {
    private final InMemoryPersonRepository personRepository;

    @Autowired
    public StudentController(InMemoryPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam String matriculationNumber) {
        personRepository.removeStudentByMatriculationNumber(matriculationNumber);
        return "redirect:/student";  // Redirect back to the students list page
    }

    @GetMapping
    public String displayAllStudents(Model model) {
        // Add a new student object to bind to the form
        model.addAttribute("student", new Student());

        // Add the list of students to display in the table
        List<Student> allStudents = personRepository.findAll();

        // Add students to the model to display in the view
        model.addAttribute("students", allStudents);

        // Print the students to the console
        return "student"; // assuming "index" is the name of the template displaying the students
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {

        // Checking if the student object has necessary attributes
        if(student.getName() == null || student.getEmailAddress() == null || student.getPhoneNumber() == null) {
            // Logging the error or printing it to console for debugging
            System.out.println("Student details are incomplete");
            // Return to the same form or an error page, depending on your design
            return "error";
        }

        // Save the student to the repository
        boolean isAdded = personRepository.addPerson(student);  // Assuming addPerson returns a boolean indicating success/failure

        // Check if student was added successfully
        if(!isAdded) {
            // Logging the error or printing it to console
            System.out.println("Error in adding student to repository");
            // Return to the same form or an error page, depending on your design
            return "error";
        }

        // Redirecting back to the main student page (or a success page) after adding the student
        return "redirect:/student";
    }
}





