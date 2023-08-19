package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Person;
import at.aau.studentevidence.domain.Staff;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final PersonService personService;

    @Autowired
    public StaffController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping
    public String displayAllStaff(Model model) {
        // Add a new student object to bind to the form
        model.addAttribute("staff", new Staff());

        // Fetch the list of students to display in the table
        List<Staff> allStaff = personService.getAllStaff();

        // Add students to the model to display in the view
        model.addAttribute("staffs", allStaff);

        return "staff";
    }

    @PostMapping("/add")
    public String addStaff(@ModelAttribute Staff staff) {
        // Checking if the student object has necessary attributes
        if (staff.getName() == null || staff.getEmailAddress() == null || staff.getPhoneNumber() == null) {
            System.out.println("Staff credentials are incomplete");
            return "error";

        }

        // Check if a staff with the same attributes already exists
        if (personService.doesStaffExist(staff)) {
            System.out.println("Staff with the same credentials already exists");
            return "error";
        }

        // Save the staff
        try {
            personService.addPerson(staff);
        } catch (Exception e) {
            System.out.println("Error in adding staff: " + e.getMessage());
            return "error";
        }
        return "redirect:/staff";
    }

    @PostMapping("/delete")
    public String deleteStaff(@RequestParam UUID id) {
        personService.removePerson(id.toString());
        return "redirect:/staff";  // Redirect back to the students list page
    }

    @GetMapping("/edit/{id}")
    public String editStaff(@PathVariable UUID id, Model model) {

        Staff staff = personService.findStaffById(id);
//        System.out.println("Student: " + student);

        String socialSecurity = staff.getSocialSecurity();

//        System.out.println("Student Matriculation Number: " + matriculationNumber);

//        if (staff == null) {
//            return "error";
//        }
//        model.addAttribute("isStaff", true);
        model.addAttribute("staff", staff);
        model.addAttribute("staffId", id);  // Add the person id separately
        model.addAttribute("socialSecurity", socialSecurity);  // Add the matriculation number separately

        return "edit";
    }

    @PostMapping("/update")
    public String updateStaff(@ModelAttribute Staff staff, @RequestParam UUID id, @RequestParam String socialSecurity) {
//        System.out.println("received student at update Controller" + staff);

        // manually setting the id and matriculation number of the staff
        staff.setId(id);
        staff.setSocialSecurity(socialSecurity);

        // Check if the staff object has necessary attributes
        if (staff.getName() == null || staff.getEmailAddress() == null || staff.getPhoneNumber() == null) {
            System.out.println("Staff details are incomplete");
            return "error";
        }

        // Update the staff in the storage
        try {
            personService.updatePerson(staff);
        } catch (Exception e) {
            System.out.println("Error in updating staff: " + e.getMessage());
            return "error";
        }
        return "redirect:/staff";
    }
}
