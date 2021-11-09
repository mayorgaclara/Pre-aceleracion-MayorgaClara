package com.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String imagen;
    private String title;
    private LocalDate Creationdate;
    private Long qualification;
    private Long genreId;
    private List<CharacterDTO> characters;
}
