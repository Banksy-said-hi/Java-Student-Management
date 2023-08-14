package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final PersonService personService;

    // This method will automatically add an empty student to the model for every request handled by this controller
    @ModelAttribute("student")
    public Student getEmptyStudent() {
        return new Student();
    }

    @Autowired
    public StudentController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable UUID id, Model model) {
        Student student = personService.findStudentById(id);
        if (student == null) {
            return "error";  // You can handle this more gracefully if needed.
        }

        model.addAttribute("student", student);
        model.addAttribute("studentId", id.toString());  // Add the id separately
        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        // Check if the student object has necessary attributes
        if (student.getName() == null || student.getEmailAddress() == null || student.getPhoneNumber() == null) {
            System.out.println("Student details are incomplete");
            return "error"; // or redirect to another page with a proper message
        }

        // Check if the student with the provided ID exists
        if (!personService.doesStudentExist(student)) {
            System.out.println("Student with the provided ID doesn't exist");
            return "error"; // or redirect to another page with a proper message
        }

        // Update the student in the storage
        try {
            personService.updatePerson(student);
        } catch (Exception e) {
            System.out.println("Error in updating student: " + e.getMessage());
            return "error";
        }

        return "redirect:/student";
//        return "student";
    }

    @GetMapping
    public String displayAllStudents(Model model) {
        // Add a new student object to bind to the form
//        model.addAttribute("student", new Student());

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
