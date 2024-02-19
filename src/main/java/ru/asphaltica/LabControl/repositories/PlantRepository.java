package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Plant;
@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
