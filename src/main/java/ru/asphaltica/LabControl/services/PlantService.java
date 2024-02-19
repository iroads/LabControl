package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.asphaltica.LabControl.models.Plant;
import ru.asphaltica.LabControl.repositories.PlantRepository;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> finaAll() {
        return plantRepository.findAll();
    }
}
