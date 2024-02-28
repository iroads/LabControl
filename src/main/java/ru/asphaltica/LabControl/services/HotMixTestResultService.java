package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.asphaltica.LabControl.models.HotMixTestResult;
import ru.asphaltica.LabControl.repositories.HotMixTestResultRepository;

import java.util.List;

@Service
public class HotMixTestResultService {


    private final HotMixTestResultRepository hotMixTestResultRepository;

    @Autowired
    public HotMixTestResultService(HotMixTestResultRepository hotMixTestResultRepository) {
        this.hotMixTestResultRepository = hotMixTestResultRepository;
    }

    public void save(HotMixTestResult result) {
        hotMixTestResultRepository.save(result);
    }

    public List<HotMixTestResult> findAll() {
        return hotMixTestResultRepository.findAll();
    }
}
