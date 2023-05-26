package com.eptexcoatings.assignment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class FileName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CsvRecord> records = new HashSet<>();
}
