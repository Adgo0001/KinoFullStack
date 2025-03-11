package com.example.kinofullstack.repository;

import com.example.kinofullstack.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatresRepo extends JpaRepository<Theatre, Integer> {

}
