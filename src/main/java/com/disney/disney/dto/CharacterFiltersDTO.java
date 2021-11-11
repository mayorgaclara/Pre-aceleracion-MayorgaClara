package com.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CharacterFiltersDTO {

    private String name;
    private Long age;
    private List<Long> movies;
    private String order;

    public CharacterFiltersDTO(String name, Long age, List<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }
    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
