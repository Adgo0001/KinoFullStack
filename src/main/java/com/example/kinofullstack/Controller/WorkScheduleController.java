package com.example.kinofullstack.Controller;

import com.example.kinofullstack.Model.WorkSchedule;
import com.example.kinofullstack.Service.WorkScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workschedule")
@CrossOrigin(origins = "*")
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @GetMapping
    public List<WorkSchedule> getAllSchedules() {
        return workScheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkSchedule> getSchedule(@PathVariable Integer id) {
        return workScheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WorkSchedule> createSchedule(@RequestBody WorkSchedule schedule) {
        WorkSchedule created = workScheduleService.createSchedule(schedule);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkSchedule> updateSchedule(@PathVariable Integer id, @RequestBody WorkSchedule updatedSchedule) {
        try {
            WorkSchedule updated = workScheduleService.updateSchedule(id, updatedSchedule);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        try {
            workScheduleService.deleteSchedule(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint til at hente vagtplan for et datointerval (fx for en uge)
    @GetMapping("/week")
    public ResponseEntity<List<WorkSchedule>> getWeekSchedule(@RequestParam("start") String start,
                                                              @RequestParam("end") String end) {
        LocalDate startDate = LocalDate.parse(start.trim());
        LocalDate endDate = LocalDate.parse(end.trim());
        List<WorkSchedule> schedule = workScheduleService.getScheduleForDateRange(startDate, endDate);
        return ResponseEntity.ok(schedule);
    }
}
