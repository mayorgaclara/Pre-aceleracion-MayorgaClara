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
public class MovieMapper {


    @Autowired
    private CharacterMapper characterMapper;


    public MovieEntity movieDTO2Entity(MovieDTO dto, boolean loadCharacters) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImagen(dto.getImagen());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setCreationDate(dto.getCreationdate());
        movieEntity.setQualification(dto.getQualification());
        movieEntity.setGenreId(dto.getGenreId());
        if (loadCharacters) {
            List<CharacterEntity> characters = this.characterMapper.characterDTOList2EntityList(dto.getCharacters());
            movieEntity.setCharacters(characters);
        }
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitle(entity.getTitle());
        dto.setCreationdate(entity.getCreationDate());
        dto.setQualification(entity.getQualification());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters) {
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntityList2DTOList((List<CharacterEntity>)entity.getCharacters(), false);
            dto.setCharacters(characterDTOS);
        }

        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }


    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> movies) {
        List<MovieEntity> entities = new ArrayList<>();
        for (MovieDTO dto : movies) {
            entities.add(movieDTO2Entity(dto, false));
        }
        return entities;
    }

}
