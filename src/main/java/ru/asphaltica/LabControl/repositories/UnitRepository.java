package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Unit;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
