package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Course;
import at.aau.studentevidence.domain.Staff;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.CourseService;
import at.aau.studentevidence.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final PersonService personService;

    @Autowired
    public CourseController(CourseService courseService, PersonService personService) {
        this.personService = personService;
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public String createCourse(Course course) {
        courseService.saveCourse(course);
        return "redirect:/course";
    }

    @GetMapping ("{id}")
    public String coursePage(@PathVariable String id, Model model) {
        // Fetch the course using the provided ID
        Course course = courseService.findCourseById(id);

        // Fetch students and staff associated with the specific course
        Set<Student> courseStudents = course.getStudents();

        Staff courseTeacher = course.getTeacher();

        // Fetch all students and staff
        List<Student> allStudents = personService.getAllStudents();
        List<Staff> allStaff = personService.getAllStaff();

        // Add necessary things to the model
        model.addAttribute("courseStudents", courseStudents);
        model.addAttribute("courseTeacher", courseTeacher);
        model.addAttribute("course", course);
        model.addAttribute("students", allStudents);
        model.addAttribute("staffs", allStaff);

        // Return the course details view
        return "course";
    }

    @PostMapping("/assignTeacher")
    public ModelAndView assignTeacherToCourse(@RequestParam String courseId, @RequestParam UUID staffId) {
        // Fetch the course by its ID
        Course course = courseService.findCourseById(courseId);

        // Fetch the student by its ID
        Staff staff = personService.findStaffById(staffId);

        // Add student to the course
        course.setTeacher(staff);

        // Redirect back to the course page (or wherever you'd like)
        return new ModelAndView("redirect:/course/" + courseId);
    }

    @PostMapping("/removeTeacher")
    public String removeTeacher(@RequestParam String courseId) {

        courseService.removeTeacherFromCourse(courseId);

        return "redirect:/course/" + courseId;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudentToCourse(@RequestParam String courseId, @RequestParam UUID studentId) {
        // Fetch the course by its ID
        Course course = courseService.findCourseById(courseId);

        // Fetch the student by its ID
        Student student = personService.findStudentById(studentId);

        // Add student to the course
        course.addStudent(student);

        // Redirect back to the course page (or wherever you'd like)
        return new ModelAndView("redirect:/course/" + courseId);
    }

    @GetMapping
    public String displayAllCourses(Model model) {

        // Add a new student object to bind to the form
        model.addAttribute("course", new Course("", null));
//        System.out.println("Newly created course students: " + newCourse.getStudents());

        // Fetch the list of courses to display in the table
        Set<Course> allCourses = courseService.findAllCourses();

        // Add students to the model to display in the view
        model.addAttribute("courses", allCourses);

        return "courses";
    }

    @PostMapping("/flipstatus")
    public String flipStatus(@RequestParam String courseId) {
        try {
            // Attempt to change the course status
            courseService.changeCourseStatus(courseId);
        } catch (NoSuchElementException ex) {
            // handle the error, e.g., redirect with an error message
            return "error";
        }

        // Redirect back to the course page 
        return "redirect:/course";
    }
}
