package com.scm.dsmovie2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.dsmovie2.dto.MovieDTO;
import com.scm.dsmovie2.dto.ScoreDTO;
import com.scm.dsmovie2.services.ScoreService;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {
	
	/*
	 * @Autowired private ScoreService scoreService;
	 * 
	 * @GetMapping public Page<MovieDTO> findAll(Pageable pageable) { return
	 * movieService.findAll(pageable); }
	 */
	
	@Autowired 
	private ScoreService scoreService;

	
	@PutMapping
	public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
		MovieDTO movieDTO = scoreService.saveScore(dto);
		return movieDTO;
	}
}
