package com.example.kinofullstack.Controller;

import com.example.kinofullstack.Model.Show;
import com.example.kinofullstack.Service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "*")
public class ShowController {

    private final ShowService showService;

    // Constructor injection of ShowService
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // Get all shows
    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    // Get a specific show
    @GetMapping("/{id}")
    public ResponseEntity<Show> getShow(@PathVariable Integer id) {
        return showService.getShowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a show
    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Integer id, @RequestBody Show updated) {
        try {
            Show updatedShow = showService.updateShow(id, updated);
            return ResponseEntity.ok(updatedShow);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}