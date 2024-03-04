package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "position")
    private String position;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;
    @OneToMany(mappedBy = "batchCreator")
    private List<Batch> batches;

    public String getFullName(){
        String fullName = this.name + " " + this.surname;
        return fullName;
    }
}
