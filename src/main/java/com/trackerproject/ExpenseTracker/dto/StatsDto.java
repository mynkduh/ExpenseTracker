package com.trackerproject.ExpenseTracker.dto;

import com.trackerproject.ExpenseTracker.entity.Expense;
import com.trackerproject.ExpenseTracker.entity.Income;
import lombok.Data;

@Data
public class StatsDto {

    private Double income;
    private Double expense;
    private Expense latestExpense;
    private Income latestIncome;

    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;
}
