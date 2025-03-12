package com.example.kinofullstack.Repository;

import com.example.kinofullstack.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatresRepo extends JpaRepository<Theatre, Integer> {

}
