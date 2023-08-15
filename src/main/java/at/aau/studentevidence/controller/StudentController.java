package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.util.List;
import java.util.UUID;
// d6ead08d-cc07-4a36-9efe-838db9edca10
@Controller
@RequestMapping("/student")
public class StudentController {

    private final PersonService personService;

    // This method will automatically add an empty student to the model for every request handled by this controller
//    @ModelAttribute("student")
//    public Student getEmptyStudent() {
//        return new Student();
//    }

    @Autowired
    public StudentController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable UUID id, Model model) {
        System.out.println("request reached editStudent controller");
        Student student = personService.findStudentById(id);
        System.out.println("found student by its ID" + student);

        if (student == null) {
            return "error";  // You can handle this more gracefully if needed.
        }

        model.addAttribute("student", student);
        model.addAttribute("studentId", id);  // Add the id separately
        System.out.println("model :" + model);
        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, @RequestParam UUID id) {
        System.out.println("received student at update Controller" + student);
        System.out.println("received id at update controller" + id);
        student.setId(id);

        // Check if the student object has necessary attributes
        if (student.getName() == null || student.getEmailAddress() == null || student.getPhoneNumber() == null) {
            System.out.println("Student details are incomplete");
            return "error"; // or redirect to another page with a proper message
        }

        System.out.println("ID at the second pinpoint of the update Controller" + student.getId());
        System.out.println("=================");


        // Update the student in the storage
        try {
            personService.updatePerson(student);
        } catch (Exception e) {
            System.out.println("Error in updating student: " + e.getMessage());
            return "error";
        }
//
        return "redirect:/student";
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
            System.out.println("Student credentials are incomplete");
            return "error";

        }
        // Check if a student with the same attributes already exists
        if (personService.doesStudentExist(student)) {
            System.out.println("Student with the same credentials already exists");
            return "error";

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

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam UUID id) {
        personService.removePerson(id.toString());
        return "redirect:/student";  // Redirect back to the students list page
    }
}
