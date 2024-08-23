package com.trackerproject.ExpenseTracker.services.stats;

import com.trackerproject.ExpenseTracker.dto.GraphDto;
import com.trackerproject.ExpenseTracker.dto.StatsDto;
import com.trackerproject.ExpenseTracker.entity.Expense;
import com.trackerproject.ExpenseTracker.entity.Income;
import com.trackerproject.ExpenseTracker.repository.ExpenseRepository;
import com.trackerproject.ExpenseTracker.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27); //range of 27 days in chart

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        return graphDto;
    }

    public StatsDto getStats(){
        Double totalIncome = incomeRepository.sumAllAmounts(); //totalIncome stat
        Double totalExpense = expenseRepository.sumAllAmounts(); //totalExpense stat

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc(); //for latest income stat
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc(); //for latest expense stat

        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense); //for total expense stat
        statsDto.setIncome(totalIncome); //for total income stat

        if(optionalIncome.isPresent()){
            statsDto.setLatestIncome(optionalIncome.get());
        }
        optionalExpense.ifPresent(statsDto::setLatestExpense); //same as above, written in functional style expression

        statsDto.setBalance(totalIncome - totalExpense); //for net balance

        List<Income> incomeList = incomeRepository.findAll(); //from here on below we go min,max logic
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min(); //for min,max income
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min(); //for min,max expense
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);



        return statsDto;
    }


}
