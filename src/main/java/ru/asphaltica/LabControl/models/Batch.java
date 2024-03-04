package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="batch")
public class Batch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name="quantity")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User batchCreator;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit ownUnit;

    @Transient
    private int plantId;

    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant ownPlant;
    //private Recipe recipe;
}
