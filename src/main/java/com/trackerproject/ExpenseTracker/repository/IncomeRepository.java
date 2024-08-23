package com.trackerproject.ExpenseTracker.repository;

import com.trackerproject.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByDateBetween(LocalDate start, LocalDate end); //for graph

    @Query("SELECT SUM(i.amount) FROM Income i") //for stats all income amount
    Double sumAllAmounts();

    Optional<Income> findFirstByOrderByDateDesc(); //for latest income stats
}
