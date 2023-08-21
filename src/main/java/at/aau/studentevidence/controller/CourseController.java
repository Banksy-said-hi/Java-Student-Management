package at.aau.studentevidence.controller;

import at.aau.studentevidence.domain.Course;
import at.aau.studentevidence.domain.Student;
import at.aau.studentevidence.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public String createCourse(Course course, Model model) {
        System.out.println("MODEL IS LIKE THIS:" + "AT POST" + model);
        courseService.saveCourse(course);
        return "redirect:/course";
    }


    @GetMapping
    public String displayAllCourses(Model model) {
        // Add a new student object to bind to the form
        model.addAttribute("course", new Course("", null));

        // Fetch the list of students to display in the table
        Set<Course> allCourses = courseService.findAllCourses();

        // Add students to the model to display in the view
        model.addAttribute("courses", allCourses);

        return "course";
    }
}
