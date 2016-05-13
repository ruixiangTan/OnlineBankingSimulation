package concordia.inse6260.bankingsimulation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/public"}, method = RequestMethod.GET)
    public String Home() {
        //model.addAttribute("isLogin", (String) session.getAttribute("isLogin"));
        return "Home";
    }
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login() {
        return "Login";
    }*/
}