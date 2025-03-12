package com.example.kinofullstack.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int movie_id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int age_limit;
    private int duration;  // Duration in minutes
    private String description;
    private String image_path;  // Default value is handled by DB.
    private String trailer_path;

    // Constructors
    public Movie() {}

    public Movie(String title, Genre genre, int age_limit, int duration, String description, String image_path, String trailer_path) {
        this.title = title;
        this.genre = genre;
        this.age_limit = age_limit;
        this.duration = duration;
        this.description = description;
        this.image_path = image_path;
        this.trailer_path = image_path;
    }

    // Getters and Setters
    public int getMovieId() { return movie_id; }
    public void setMovieId(int movie_id) { this.movie_id = movie_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public int getAgeLimit() { return age_limit; }
    public void setAgeLimit(int age_limit) { this.age_limit = age_limit; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImagePath() { return image_path; }
    public void setImagePath(String image_path) { this.image_path = image_path; }

    public String getTrailerPath() { return trailer_path; }
    public void setTrailer_path(String trailer_path) { this.trailer_path = trailer_path; }

    // Enum defining different genres for a movie
    public enum Genre {
        HORROR, ROMANCE, ACTION, FANTASY, SCIENCE_FICTION, EPIC, DRAMA, MUSIC, OTHER;

        @Override
        @JsonValue
        public String toString() {
            // Replace underscores with spaces and capitalize the first letter of each word
            String formattedName = name().replace("_", " ").toLowerCase();
            return formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);
        }
    }
}