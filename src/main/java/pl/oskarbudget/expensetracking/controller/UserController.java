package pl.oskarbudget.expensetracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.oskarbudget.expensetracking.model.Expense;
import pl.oskarbudget.expensetracking.model.User;
import pl.oskarbudget.expensetracking.service.ExpenseService;
import pl.oskarbudget.expensetracking.service.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    UserDetailsServiceImpl userService;
    ExpenseService expenseService;
    @Autowired
    public UserController(UserDetailsServiceImpl userService, ExpenseService expenseService){
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @RequestMapping("/users")
    public String getAllUsers(Model model){
        List<User> allUsers = userService.getUsers();
        model.addAttribute("users",allUsers);
        return "users";
    }

    @RequestMapping("/user")
    public String getUser(@RequestParam("id") Long id, Model model){
        Optional<User> user = userService.getUser(id);
        User userr = user.get();
        List<Expense> expenses = expenseService.getExpenses(userr);
        model.addAttribute("expenses", expenses);
        model.addAttribute("user",user);
        model.addAttribute("id",id);
        return "user";
    }


    @RequestMapping("/create")
    public String createUsers(Model model){
        model.addAttribute("user",new User());
        return "create";
    }
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createUsers(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("There were errors!!!");
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.getObjectName() +"" + objectError.getDefaultMessage());
            });
            return "/user";
        }
        else{
            userService.createUser(user);
            return "redirect:/user?id="+user.getId();
        }

    }

    @RequestMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
