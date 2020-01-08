package pl.oskarbudget.expensetracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarbudget.expensetracking.model.Expense;
import pl.oskarbudget.expensetracking.model.User;
import pl.oskarbudget.expensetracking.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ExpenseService {
    ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void createExpense(Expense expense){
        expenseRepository.save(expense);
    }
    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }
    public List<Expense> getExpenses(User user){
     // return  expenseRepository.findByDateAndUser(date, user);
        return  expenseRepository.findByUser(user);
    }
    public Optional<Expense> getExpense(Long id){
        return expenseRepository.findById(id);
    }

    public void update(Expense expense) {
        expenseRepository.save(expense);
    }
}
