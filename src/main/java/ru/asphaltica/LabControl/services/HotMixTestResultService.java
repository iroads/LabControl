package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.HotMixTestResult;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.HotMixTestResultRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HotMixTestResultService {


    private final HotMixTestResultRepository hotMixTestResultRepository;

    @Autowired
    public HotMixTestResultService(HotMixTestResultRepository hotMixTestResultRepository) {
        this.hotMixTestResultRepository = hotMixTestResultRepository;
    }

    public HotMixTestResult findById(int id) {
        return hotMixTestResultRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(int id) {
        hotMixTestResultRepository.deleteById(id);
    }
    @Transactional
    public void save(HotMixTestResult result) {
        result.setVoids(result.getVoids());
        hotMixTestResultRepository.save(result);
    }

    public List<HotMixTestResult> findAll() {
        return hotMixTestResultRepository.findAll();
    }
}
