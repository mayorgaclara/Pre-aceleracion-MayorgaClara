package com.disney.disney.service;

import com.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieDTO getById(Long id);

    List<MovieDTO> getAllMovies();

    void delete(Long id);
}
