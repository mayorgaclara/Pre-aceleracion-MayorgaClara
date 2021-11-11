package com.disney.disney.service.serviceImpl;

import com.disney.disney.dto.CharacterDTO;
import com.disney.disney.dto.CharacterFiltersDTO;
import com.disney.disney.entity.CharacterEntity;
import com.disney.disney.exception.ParamNotFound;
import com.disney.disney.mapper.CharacterMapper;
import com.disney.disney.repository.CharacterRepository;
import com.disney.disney.repository.specifications.CharacterSpecification;
import com.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterSpecification characterSpecification;


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

    @Override
    public List<CharacterDTO> getByFilters(String name, Long age, List<Long> movies, String order) {
        CharacterFiltersDTO characterFiltersDTO = new  CharacterFiltersDTO(name, age, movies, order);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(characterFiltersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2DTOList(entities, true);
        return dtos;
    }


    public void delete(Long id) {
        this.characterRepository.deleteById(id);
    }


}
