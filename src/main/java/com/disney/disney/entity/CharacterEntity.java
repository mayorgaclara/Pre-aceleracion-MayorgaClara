package com.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charactr")
@Getter
@Setter
@SQLDelete(sql = "UPDATE charactr SET deleted = true WHERE id=? and version=?")
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

    @ManyToMany(mappedBy = "charactrs", cascade = CascadeType.MERGE)
    private List<MovieEntity> movies = new ArrayList<>();
}
