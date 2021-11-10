package com.disney.disney.service.serviceImpl;

import com.disney.disney.dto.GenreDTO;
import com.disney.disney.entity.GenreEntity;
import com.disney.disney.exception.ParamNotFound;
import com.disney.disney.mapper.GenreMapper;
import com.disney.disney.repository.GenreRepository;
import com.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto) {
        // Operacion de guardado en 3 pasos:
        // Lo convierto a Entity
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        //Lo guardo y me devuelve la entidad guardada
        GenreEntity entitySaved = genreRepository.save(entity);
        //Convierto la entidad guardada en un dto
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    public GenreDTO update(Long id, GenreDTO DTO) {
        Optional<GenreEntity> entity = this.genreRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id genre not valid");
        }
        this.genreMapper.genreEntityRefreshValues(entity.get(), DTO);
        GenreEntity entitySaved = this.genreRepository.save(entity.get());
        GenreDTO result = this.genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities);
        return result;
    }

    public void delete(Long id) {
        this.genreRepository.deleteById(id);
    }
}
