package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Plant;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.PlantRepository;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> findAll() {
        return plantRepository.findAll();

    }

    public Plant findById(int id) {
        return plantRepository.findById(id).orElse(null);
    }

    public List<Plant> findByUnitId(int id) {
        return plantRepository.findByUnitId(id);
    }

    @Transactional
    public void save(Plant plant) {
        plantRepository.save(plant);
    }

    @Transactional
    public void update(int id, Plant updatedPlant) {
        Plant plantToBeUpdated = plantRepository.findById(id).get();

        updatedPlant.setId(id);
        updatedPlant.setUnit(plantToBeUpdated.getUnit()); //чтобы не терялась связь при обновлении
        plantRepository.save(updatedPlant);
    }

    @Transactional
    public void addOwnUnit(int id, Unit unit) {
        Plant plant = plantRepository.findById(id).orElse(null);
        plant.setUnit(unit);
        plantRepository.save(plant);
    }

    @Transactional
    public void deleteById(int id) {
        plantRepository.deleteById(id);
    }

}
