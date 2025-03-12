package com.example.kinofullstack.Service;

import com.example.kinofullstack.Model.Show;
import com.example.kinofullstack.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // Retrieve all shows
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    // Retrieve a specific show by id
    public Optional<Show> getShowById(Integer id) {
        return showRepository.findById(id);
    }

    // Create a new show
    public Show createShow(Show show) {
        return showRepository.save(show);
    }

    // Update an existing show
    public Show updateShow(Integer id, Show updatedShow) {
        return showRepository.findById(id)
                .map(existing -> {
                    existing.setMovieId(updatedShow.getMovieId());
                    existing.setTheatreId(updatedShow.getTheatreId());
                    existing.setShowDate(updatedShow.getShowDate());
                    existing.setShowTime(updatedShow.getShowTime());
                    return showRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Show not found with id " + id));
    }

    // Delete a show
    public void deleteShow(Integer id) {
        showRepository.deleteById(id);
    }
}
