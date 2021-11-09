package com.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    private String imagen;
    private String name;
    private Long age;
    private Long weight;
    private String story;
    private List<MovieDTO> movies;
}
