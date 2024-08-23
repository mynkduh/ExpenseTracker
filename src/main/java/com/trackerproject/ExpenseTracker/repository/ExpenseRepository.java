package com.trackerproject.ExpenseTracker.repository;

import com.trackerproject.ExpenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);  //for graph

    @Query("SELECT SUM(e.amount) FROM Expense e")  //for stats all expense amount
    Double sumAllAmounts();

    Optional<Expense> findFirstByOrderByDateDesc(); //for latest expense stats
}
