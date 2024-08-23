package com.trackerproject.ExpenseTracker.dto;

import com.trackerproject.ExpenseTracker.entity.Expense;
import com.trackerproject.ExpenseTracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;
}
