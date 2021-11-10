package com.disney.disney.controller;

import com.disney.disney.dto.CharacterDTO;
import com.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAll(
            @RequestParam(value= "name", required = false) String characterName,
            @RequestParam(value= "movie", required = false) Long characterMovie) {

        if (characterName != null) {
            return ResponseEntity.ok().body(characterService.getCharactersByName(characterName));
        }
        if (characterMovie != null) {
            return ResponseEntity.ok().body(characterService.getCharactersByMovie(characterMovie));
        }
        List<CharacterDTO> icons = characterService.getAllCharacters();
        return ResponseEntity.ok().body(icons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(characterService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO genreDTO) {
        CharacterDTO result = this.characterService.update(id, genreDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
