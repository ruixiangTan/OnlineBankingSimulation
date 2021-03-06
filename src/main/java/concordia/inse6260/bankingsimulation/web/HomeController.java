package concordia.inse6260.bankingsimulation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/public"}, method = RequestMethod.GET)
    public String home() {
        return "Home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "Login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }
}