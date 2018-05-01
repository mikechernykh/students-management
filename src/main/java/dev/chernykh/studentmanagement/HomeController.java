package dev.chernykh.studentmanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to display a home page.
 */
@Controller
public class HomeController {
    @GetMapping
    public String home() {
        return "home";
    }
}
