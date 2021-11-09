package com.disney.disney.mapper;

import com.disney.disney.dto.CharacterDTO;
import com.disney.disney.dto.MovieDTO;
import com.disney.disney.entity.CharacterEntity;
import com.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto, boolean loadMovies) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImagen(dto.getImagen());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setStory(dto.getStory());
        if (loadMovies) {
            List<MovieEntity> movies = this.movieMapper.movieDTOList2EntityList(dto.getMovies());
            characterEntity.setMovies(movies);
        }
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        if (loadMovies) {
            List<MovieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(moviesDTO);
        }
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> characters) {
        List<CharacterEntity> entities = new ArrayList<>();
        for (CharacterDTO dto : characters) {
            entities.add(characterDTO2Entity(dto, false));
        }
        return entities;
    }
}
