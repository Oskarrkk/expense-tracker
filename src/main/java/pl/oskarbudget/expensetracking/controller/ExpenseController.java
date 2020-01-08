package pl.oskarbudget.expensetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.oskarbudget.expensetracking.model.Expense;
import pl.oskarbudget.expensetracking.model.User;
import pl.oskarbudget.expensetracking.service.ExpenseService;
import pl.oskarbudget.expensetracking.service.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ExpenseController {
    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    ExpenseService expenseService;

    @RequestMapping("/user/addExpense")
        String addExpense(@RequestParam("userId") Long id, Model model ){
        Optional<User> user = userService.getUser(id);
        user.ifPresent(model::addAttribute);
        Expense expense = new Expense();
     //   model.addAttribute("users",user);
        model.addAttribute("expense", expense);

        return "addExpense";
}

    @RequestMapping(value = "/user/addExpense", method = RequestMethod.POST)
    String addExpense(@Valid Expense expense, @RequestParam Long userid){
        Optional<User> user = userService.getUser(userid);
        user.ifPresent(expense::setUser);
        expenseService.createExpense(expense);
        return "redirect:/user?id="+userid;
    }


    @RequestMapping(value = "/user/editExpense")
    String editExpense(@RequestParam Long expenseid,@RequestParam Long userid, Model model){
        Optional<Expense> expense = expenseService.getExpense(expenseid);
        Optional<User> user = userService.getUser(userid);
        expense.ifPresent(model::addAttribute);
        user.ifPresent(model::addAttribute);
        return "editExpense";
    }

    @RequestMapping(value = "/user/editExpense", method = RequestMethod.POST)
    String editExpense(@Valid Expense expense,@RequestParam Long userid,@RequestParam Long expenseid){
        Optional<User> user = userService.getUser(userid);
        user.ifPresent(expense::setUser);
        expense.setId(expenseid);
        expenseService.update(expense);
        return "redirect:/user?id="+userid;
    }
}
