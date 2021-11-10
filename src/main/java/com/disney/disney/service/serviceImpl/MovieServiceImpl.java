package com.disney.disney.service.serviceImpl;

import com.disney.disney.dto.MovieDTO;
import com.disney.disney.entity.MovieEntity;
import com.disney.disney.mapper.MovieMapper;
import com.disney.disney.repository.MovieRepository;
import com.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;


    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, false);
        return result;
    }

    public MovieDTO getById(Long id){
        return movieMapper.movieEntity2DTO(movieRepository.getById(id), true);
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = this.movieRepository.findAll();
        List<MovieDTO> result = this.movieMapper.movieEntityList2DTOList(entities, true);
        return result;
    }

    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

}
