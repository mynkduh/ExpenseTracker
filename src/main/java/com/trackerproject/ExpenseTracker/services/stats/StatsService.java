package com.trackerproject.ExpenseTracker.services.stats;

import com.trackerproject.ExpenseTracker.dto.GraphDto;
import com.trackerproject.ExpenseTracker.dto.StatsDto;

public interface StatsService {

    GraphDto getChartData();

    StatsDto getStats();
}
