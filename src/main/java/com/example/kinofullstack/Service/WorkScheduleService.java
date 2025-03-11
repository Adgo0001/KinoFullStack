package com.example.kinofullstack.Service;

import com.example.kinofullstack.Model.WorkSchedule;
import com.example.kinofullstack.Repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;

    @Autowired
    public WorkScheduleService(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    public List<WorkSchedule> getAllSchedules() {
        return workScheduleRepository.findAll();
    }

    public Optional<WorkSchedule> getScheduleById(Integer id) {
        return workScheduleRepository.findById(id);
    }

    public WorkSchedule createSchedule(WorkSchedule schedule) {
        return workScheduleRepository.save(schedule);
    }

    public WorkSchedule updateSchedule(Integer id, WorkSchedule updatedSchedule) {
        return workScheduleRepository.findById(id)
                .map(existing -> {
                    existing.setWorkDate(updatedSchedule.getWorkDate());
                    existing.setStartTime(updatedSchedule.getStartTime());
                    existing.setEndTime(updatedSchedule.getEndTime());
                    existing.setDescription(updatedSchedule.getDescription());
                    existing.setEmployee(updatedSchedule.getEmployee());
                    return workScheduleRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }


    public void deleteSchedule(Integer id) {
        workScheduleRepository.deleteById(id);
    }

    public List<WorkSchedule> getScheduleForDateRange(LocalDate start, LocalDate end) {
        return workScheduleRepository.findByWorkDateBetween(start, end);
    }
}
