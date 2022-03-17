package com.scm.dsmovie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scm.dsmovie2.dto.MovieDTO;
import com.scm.dsmovie2.dto.ScoreDTO;
import com.scm.dsmovie2.entities.Movie;
import com.scm.dsmovie2.entities.Score;
import com.scm.dsmovie2.entities.User;
import com.scm.dsmovie2.repositories.MovieRepository;
import com.scm.dsmovie2.repositories.ScoreRepository;
import com.scm.dsmovie2.repositories.UserRepository;

@Service
public class ScoreService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.saveAndFlush(movie);
		
		return new MovieDTO(movie);
		
	}
	

	/*
	 * @Transactional(readOnly = true) public Page<MovieDTO> findAll(Pageable
	 * pageable) { Page<Movie> result = movieRepository.findAll(pageable);
	 * 
	 * Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
	 * 
	 * return page; }
	 * 
	 * @Transactional(readOnly = true) public MovieDTO findById(Long id) { Movie
	 * result = movieRepository.findById(id).get();
	 * 
	 * MovieDTO dto = new MovieDTO(result);
	 * 
	 * return dto; }
	 */
}
