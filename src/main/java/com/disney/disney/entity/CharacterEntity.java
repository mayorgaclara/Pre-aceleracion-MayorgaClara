package com.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character")
@Getter
@Setter
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String name;

    private Long age;

    private Long weight;

    private String story;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();
}
