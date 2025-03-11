package com.example.kinofullstack.repository;

import com.example.kinofullstack.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Show, Integer> {
}
