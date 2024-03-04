package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.*;
import ru.asphaltica.LabControl.repositories.BatchRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BatchService {
    private final BatchRepository batchRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public List<Batch> findAll() {
        List<Batch> batches = batchRepository.findAll();
        return batches;
    }

    public List<Batch> findAllByOwnUnit(Unit creatorUnit) {
        List<Batch> batches = batchRepository.findAllByOwnUnit(creatorUnit);
        return batches;
    }


    @Transactional
    public void save(Batch batch, User batchCreator, Plant ownPlant) {
        batch.setBatchCreator(batchCreator);
        batch.setOwnUnit(batchCreator.getUnit());
        batch.setOwnPlant(ownPlant);
        batchRepository.save(batch);
    }

    @Transactional
    public void update(int id, Batch updatedBatch) {
        Batch batchToBeUpdated = batchRepository.findById(id).get();
        updatedBatch.setId(id);
        updatedBatch.setBatchCreator(batchToBeUpdated.getBatchCreator());
        updatedBatch.setOwnUnit(batchToBeUpdated.getOwnUnit());
        updatedBatch.setOwnPlant(batchToBeUpdated.getOwnPlant());
//        updatedUser.setUnit(userToBeUpdated.getUnit()); //чтобы не терялась связь при обновлении
        updatedBatch.setCreateDate(batchToBeUpdated.getCreateDate());
        batchRepository.save(updatedBatch);
    }

    @Transactional
    public void deleteById(int id) {
        batchRepository.deleteById(id);
    }

    public Batch findById(int id) {
        return batchRepository.findById(id).orElse(null);
    }
}
