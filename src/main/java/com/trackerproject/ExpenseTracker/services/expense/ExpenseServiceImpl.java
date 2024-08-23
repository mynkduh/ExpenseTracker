package com.trackerproject.ExpenseTracker.services.expense;

import com.trackerproject.ExpenseTracker.dto.ExpenseDto;
import com.trackerproject.ExpenseTracker.entity.Expense;
import com.trackerproject.ExpenseTracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDto expenseDto){ //main method for creating new expense
        return saveOrUpdateExpense(new Expense(), expenseDto);
    }
    //this is private since abstraction whereas postExpense is public which will be called by end user
    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed()) //show the latest date first
                .collect(Collectors.toList()); //present it in the form of list

    }

    public Expense getExpenseById(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            return optionalExpense.get();
        }else{
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }

    public Expense updateExpense(Long id,ExpenseDto expenseDto){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            return saveOrUpdateExpense(optionalExpense.get(),expenseDto);
        }else{
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }
    }

    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense is not present with id " + id);
        }

    }

}
