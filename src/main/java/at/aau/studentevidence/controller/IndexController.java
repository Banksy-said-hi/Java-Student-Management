package at.aau.studentevidence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Markus Schneider
 * @since 23.05.2022
 */
@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        return "index";
    }

}
