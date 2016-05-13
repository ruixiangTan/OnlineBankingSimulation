package concordia.inse6260.bankingsimulation.web;

import concordia.inse6260.bankingsimulation.dao.UserRepository;
import concordia.inse6260.bankingsimulation.domain.User;
import concordia.inse6260.bankingsimulation.domain.enums.Gender;
import concordia.inse6260.bankingsimulation.service.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/create", "/admin"}, method = RequestMethod.GET)
    public String admin() {
        return "Admin";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAccount(HttpServletRequest request) {
        User user = new User();
        AccountServices.initializeUser(user);
        user.setPassword(request.getParameter("passWord"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setGender(Gender.valueOf(request.getParameter("selectGender")));
        user.setBirthDate(LocalDate.parse(request.getParameter("dateOfBirth")));
        user.getAddress().setCity(request.getParameter("homeAddress"));

        user.setEmail(request.getParameter("emailAddress"));
        user.getCheckingAccount().setBalance(0);
        user.getCheckingAccount().getTransactions().clear();
        user.getSavingAccount().setBalance(0);
        user.getSavingAccount().getTransactions().clear();
        user.getCreditAccount().setBalance(0);
        user.getCreditAccount().getTransactions().clear();

        userRepository.save(user);
        return "/Admin?success=true";
    }


}
