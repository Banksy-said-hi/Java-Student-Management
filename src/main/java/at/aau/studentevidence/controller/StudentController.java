package at.aau.studentevidence.controller;

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

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable UUID id, Model model) {
        System.out.println("request reached editStudent controller");

        Student student = personService.findStudentById(id);
        System.out.println("Student: " + student);

        String matriculationNumber = student.getMatriculationNumber();
        System.out.println("Student Matriculation Number: " + matriculationNumber);


        model.addAttribute("isStudent", true);
        model.addAttribute("student", student);
        model.addAttribute("studentId", id);  // Add the person id separately
        model.addAttribute("matriculationNumber", matriculationNumber);  // Add the matriculation number separately

        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, @RequestParam UUID id, @RequestParam String matriculationNumber) {
        System.out.println("received student at update Controller" + student);

        // manually setting the id and matriculation number of the student
        student.setId(id);
        student.setMatriculationNumber(matriculationNumber);

        // Check if the student object has necessary attributes
        if (student.getName() == null || student.getEmailAddress() == null || student.getPhoneNumber() == null) {
            System.out.println("Student details are incomplete");
            return "error";
        }

        // Update the student in the storage
        try {
            personService.updatePerson(student);
        } catch (Exception e) {
            System.out.println("Error in updating student: " + e.getMessage());
            return "error";
        }
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

        return "student";
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
