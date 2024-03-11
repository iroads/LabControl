package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Batch;
import ru.asphaltica.LabControl.models.Unit;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

    List<Batch> findAllByBatchCreator_Unit(Unit unit);
    List<Batch> findAllByOwnUnit(Unit unit);

}
