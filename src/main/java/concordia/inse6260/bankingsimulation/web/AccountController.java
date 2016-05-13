package concordia.inse6260.bankingsimulation.web;

import concordia.inse6260.bankingsimulation.dao.UserRepository;
import concordia.inse6260.bankingsimulation.domain.Transaction;
import concordia.inse6260.bankingsimulation.domain.User;
import concordia.inse6260.bankingsimulation.domain.enums.Gender;
import concordia.inse6260.bankingsimulation.domain.enums.TransactionCategory;
import concordia.inse6260.bankingsimulation.domain.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/account", "/user"}, method = RequestMethod.GET)
    public String showAccount(User user, Model model) {

        model.addAttribute("user", user);

        return "Account";

    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String transferFund(User user, Model model, HttpServletRequest request) {

        String fromAccount = request.getParameter("fromAccount");
        String toAccount = request.getParameter("toAccount");

        if (fromAccount.equals(toAccount)) {
           // session.setAttribute("error", "You must select two different accounts!");
            return "redirect:/Errors";
        }

        double paymentAmount;

        try {
            paymentAmount = Double.parseDouble(request.getParameter("transferAmount"));
        } catch (Exception e) {
            //session.setAttribute("error", "The amount can only be Integer!");
            return "redirect:/Errors";
        }

        if (paymentAmount < 0) {
            //session.setAttribute("error", "The amount must > 0!");
            return "redirect:/Errors";
        }

        switch (fromAccount) {
            case "Chequing":
                if (paymentAmount > user.getCheckingAccount().getBalance()) {
                    //session.setAttribute("error", "Your Chequing account doesn't have enough money!");
                    return "redirect:/Errors";
                }
                user.getCheckingAccount().setBalance(user.getCheckingAccount().getBalance() - paymentAmount);
                user.getCheckingAccount().getTransactions().add(addTransactionRecord(paymentAmount, "WWW Internal Transfer", TransactionType.Credit, TransactionCategory.INTERNAL_TRANSFER));
                break;
            case "Saving":
                if (paymentAmount > user.getSavingAccount().getBalance()) {
                    //session.setAttribute("error", "Your Saving account doesn't have enough money!");
                    return "redirect:/Errors";
                }
                user.getSavingAccount().setBalance(user.getSavingAccount().getBalance() - paymentAmount);
                user.getSavingAccount().getTransactions().add(addTransactionRecord(paymentAmount, "WWW Internal Transfer", TransactionType.Credit, TransactionCategory.INTERNAL_TRANSFER));
                break;
        }

        switch (toAccount) {
            case "Chequing":
                user.getCheckingAccount().setBalance(user.getCheckingAccount().getBalance() + paymentAmount);
                user.getCheckingAccount().getTransactions().add(addTransactionRecord(paymentAmount, "WWW Internal Transfer", TransactionType.Debit, TransactionCategory.INTERNAL_TRANSFER));
                break;
            case "Saving":
                user.getSavingAccount().setBalance(user.getSavingAccount().getBalance() + paymentAmount);
                user.getSavingAccount().getTransactions().add(addTransactionRecord(paymentAmount, "WWW Internal Transfer", TransactionType.Debit, TransactionCategory.INTERNAL_TRANSFER));
                break;
            case "Credit":
                user.getCreditAccount().setBalance(user.getCreditAccount().getBalance() - paymentAmount);
                user.getCreditAccount().getTransactions().add(addTransactionRecord(paymentAmount, "WWW Internal Transfer", TransactionType.Debit, TransactionCategory.INTERNAL_TRANSFER));
                break;
        }

        model.addAttribute("user", user);
        userRepository.save(user);

        return "Account";

    }


    private Transaction addTransactionRecord(double paymentAmount, String transactionDescription, TransactionType transactionType, TransactionCategory transactionCategory) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDate(LocalDateTime.now());
        newTransaction.setDescription(transactionDescription);
        newTransaction.setAmount(paymentAmount);
        newTransaction.setTransactionType(transactionType);
        newTransaction.setTransactionCategory(transactionCategory);
        return newTransaction;
    }


    @RequestMapping(value = "/account/{accountNo}", method = RequestMethod.GET)
    public String showAccountDetails(@PathVariable("accountNo") String accountNo, @RequestParam("accountType") String accountType, Model model) {

        User user = userRepository.findOne(1L);
        switch (accountType) {
            case "Chequing":
                model.addAttribute("account", user.getCheckingAccount());
                break;
            case "Savings":
                model.addAttribute("account", user.getSavingAccount());
                break;
            case "CreditCard":
                model.addAttribute("account", user.getCreditAccount());
                model.addAttribute("accountType", "CreditCard");
                break;
        }
        return "AccountDetails";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showUserProfile(User user, Model model) {
        model.addAttribute("user", user);
        return "Profile";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String setUserProfile(HttpServletRequest request, User user, Model model) {

        //User user = userRepository.findOne(1L);
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setGender(Gender.valueOf(request.getParameter("selectGender")));
        user.setBirthDate(LocalDate.parse(request.getParameter("dateOfBirth")));
        //user.setAddress();
        user.setEmail(request.getParameter("emailAddress"));

        userRepository.save(user);
        model.addAttribute("user", user);
        return "/Profile?success=true";

    }

/*    @RequestMapping(value = "/expense", method = RequestMethod.GET)
    @ResponseBody
    public List<Expense> Expenses(Model model, HttpSession session) {

        List<Expense> expenseList = getExpenseList((User) session.getAttribute("user"));
        model.addAttribute("user", session.getAttribute("user"));
        return expenseList;
    }*/
}
