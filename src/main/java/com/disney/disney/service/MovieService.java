package com.disney.disney.service;

import com.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieDTO getById(Long id);

    List<MovieDTO> getAllMovies();

    List<MovieDTO> getByFilters(String name, List<Long> genre, String order);

    MovieDTO update(Long id, MovieDTO dto);

    void delete(Long id);
}
