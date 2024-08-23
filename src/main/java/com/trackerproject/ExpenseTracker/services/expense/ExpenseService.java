package com.trackerproject.ExpenseTracker.services.expense;

import com.trackerproject.ExpenseTracker.dto.ExpenseDto;
import com.trackerproject.ExpenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id,ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
