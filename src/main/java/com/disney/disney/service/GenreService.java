package com.disney.disney.service;

import com.disney.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    void delete(Long id);

    GenreDTO update(Long id, GenreDTO dto);
}
