package com.disney.disney.service;

import com.disney.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    List< CharacterDTO> getAllCharacters();

    CharacterDTO getById(Long id);

    CharacterDTO update(Long id, CharacterDTO dto);

    List<CharacterDTO> getCharactersByName(String name);

    List<CharacterDTO> getCharactersByMovie(Long movieId);

    void delete(Long id);
}
