package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Unit")
public class Unit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "unit")
    private List<User> users;
    @OneToMany(mappedBy = "unit")
    private List<Plant> plants;

}
