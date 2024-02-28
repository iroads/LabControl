package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.HotMixTestResult;
@Repository
public interface HotMixTestResultRepository extends JpaRepository<HotMixTestResult, Integer> {
}
