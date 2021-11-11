package com.disney.disney.service.serviceImpl;

import com.disney.disney.dto.MovieDTO;
import com.disney.disney.dto.MovieFiltersDTO;
import com.disney.disney.entity.MovieEntity;
import com.disney.disney.exception.ParamNotFound;
import com.disney.disney.mapper.MovieMapper;
import com.disney.disney.repository.MovieRepository;
import com.disney.disney.repository.specifications.MovieSpecification;
import com.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreServiceImpl genreService;
    @Autowired
    private CharacterServiceImpl characterService;

    @Autowired
    private MovieSpecification movieSpecification;


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

    public MovieDTO update(Long id, MovieDTO DTO) {
        Optional<MovieEntity> entity = this.movieRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id movie not valid");
        }
        this.movieMapper.movieEntityRefreshValues(entity.get(), DTO);
        MovieEntity entitySaved = this.movieRepository.save(entity.get());
        MovieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public List<MovieDTO> getByFilters(String title, List<Long> genre, String order) {
        MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll(movieSpecification.getByFilter(movieFilters));
        List<MovieDTO> resultDTO = movieMapper.movieEntityList2DTOList(entityList, true);
        return resultDTO;
    }

    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

}
