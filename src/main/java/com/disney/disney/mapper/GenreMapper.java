package com.disney.disney.mapper;

import com.disney.disney.dto.GenreDTO;
import com.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImagen(dto.getImagen());
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setName(entity.getName());
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
        List<GenreDTO> dtos = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }

    public void genreEntityRefreshValues(GenreEntity entity, GenreDTO DTO) {
        entity.setImagen(DTO.getImagen());
        entity.setName(DTO.getName());
    }
}
