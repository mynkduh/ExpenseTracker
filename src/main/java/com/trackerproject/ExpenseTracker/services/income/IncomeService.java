package com.trackerproject.ExpenseTracker.services.income;

import com.trackerproject.ExpenseTracker.dto.IncomeDto;
import com.trackerproject.ExpenseTracker.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);

    List<IncomeDto> getAllIncomes();

    Income updateIncome(Long id, IncomeDto incomeDto);

    IncomeDto getIncomeById(Long id);

    void deleteIncome(Long id);
}
