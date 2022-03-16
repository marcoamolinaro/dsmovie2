package com.scm.dsmovie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.dsmovie2.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
