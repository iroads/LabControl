package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Plant;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    List<Plant> findByUnitId(int id);
    //List<Plant> findAllByUnit(Unit unit);
}
