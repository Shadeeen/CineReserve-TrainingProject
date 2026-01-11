package com.example.finalproject.repository;

import com.example.finalproject.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository  extends JpaRepository<Hall,Long> {
}
