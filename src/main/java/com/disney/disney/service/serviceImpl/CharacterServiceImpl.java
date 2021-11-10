package com.disney.disney.service.serviceImpl;

import com.disney.disney.dto.CharacterDTO;
import com.disney.disney.entity.CharacterEntity;
import com.disney.disney.exception.ParamNotFound;
import com.disney.disney.mapper.CharacterMapper;
import com.disney.disney.repository.CharacterRepository;
import com.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;


    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto, true);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, true);
        return result;
    }

    public CharacterDTO getById(Long id){
        return characterMapper.characterEntity2DTO(characterRepository.getById(id), true);

    }

    public CharacterDTO update(Long id, CharacterDTO DTO) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Id character not valid");
        }
        this.characterMapper.characterEntityRefreshValues(entity.get(), DTO);
        CharacterEntity entitySaved = this.characterRepository.save(entity.get());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public List<CharacterDTO> getCharactersByName(String name) {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterEntity> entitiesByName = new ArrayList<>();
        for(CharacterEntity character: entities ) {
            if (character.getName().equals(name)){
                entitiesByName.add(character);
            }
        }
        return characterMapper.characterEntityList2DTOList(entitiesByName, true);
    }

    public List<CharacterDTO> getCharactersByMovie(Long movieId) {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterEntity> entitiesByMovie = new ArrayList<>();
        for(CharacterEntity character: entities ) {
            if (character.getMovies().equals(movieId)){
                entitiesByMovie.add(character);
            }
        }
        return characterMapper.characterEntityList2DTOList(entitiesByMovie, true);
    }

    public void delete(Long id) {
        this.characterRepository.deleteById(id);
    }


}
