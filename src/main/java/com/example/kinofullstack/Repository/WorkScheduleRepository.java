package com.example.kinofullstack.Repository;

import com.example.kinofullstack.Model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {
    List<WorkSchedule> findByWorkDateBetween(LocalDate start, LocalDate end);
}
