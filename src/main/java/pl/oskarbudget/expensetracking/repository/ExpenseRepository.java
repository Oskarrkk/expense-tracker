package pl.oskarbudget.expensetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarbudget.expensetracking.model.Expense;
import pl.oskarbudget.expensetracking.model.User;

import java.util.Date;
import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
 List<Expense> findByDateAndUser(Date date, User user);
 List<Expense> findByUser(User user);
}
