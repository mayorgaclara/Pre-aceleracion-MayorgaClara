package com.disney.disney.service;

import com.disney.disney.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    List< CharacterDTO> getAllCharacters();

    CharacterDTO getById(Long id);

    CharacterDTO update(Long id, CharacterDTO dto);

    List<CharacterDTO> getByFilters(String name, Long age, List<Long> movies, String order);

    void delete(Long id);
}
