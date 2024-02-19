package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.repositories.UnitRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> finaAll() {
        List<Unit> units = unitRepository.findAll();
        return units;
    }

    public Unit findById(int id) {
        return unitRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(int id) {
        unitRepository.deleteById(id);
    }

    @Transactional
    public void save(Unit unit) {
        unitRepository.save(unit);
    }

    @Transactional
    public void update(int id, Unit updatedUnit) {
        updatedUnit.setId(id);
        unitRepository.save(updatedUnit);
    }

}
