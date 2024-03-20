package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.*;
import ru.asphaltica.LabControl.repositories.BatchRepository;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.time.LocalDateTime;
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
        List<Batch> batches = batchRepository.findAll(Sort.by("id"));
        return batches;
    }

    public List<Batch> findAllCustom(Recipe recipe, Unit unit, Plant plant, LocalDateTime startDate, LocalDateTime endDate) {
    //public List<Batch> findAllCustom(, String mixType, String mixLayer, String mixTraffic, Unit unit) {
        List<Batch> batches = batchRepository.findAll(Specification.allOf(
                (root, query, criterialBuilder) -> criterialBuilder.between(root.get("createDate"), startDate, endDate),
                //(root, query, criterialBuilder) -> mixType == null ? null : criterialBuilder.equal(root.get("mixType"), MixType.findValueByType(mixType)),
                //(root, query, criterialBuilder) -> mixLayer == null ? null : criterialBuilder.equal(root.get("mixLayer"), MixLayer.findValueByLayer(mixLayer)),
                //(root, query, criterialBuilder) -> mixTraffic == null ? null : criterialBuilder.equal(root.get("mixTraffic"), MixTraffic.findValueByTraffic(mixTraffic)),
                (root, query, criterialBuilder) -> unit == null ? null : criterialBuilder.equal(root.get("ownUnit"), unit),
                (root, query, criterialBuilder) -> plant == null ? null : criterialBuilder.equal(root.get("ownPlant"), plant),
                (root, query, criterialBuilder) -> recipe == null ? null : criterialBuilder.equal(root.get("recipeSource"), recipe)
        ), Sort.by("id"));
        return batches;
    }

//    public List<Batch> findAllByOwnUnit(Unit creatorUnit) {
//        List<Batch> batches = batchRepository.findAllByOwnUnit(creatorUnit);
//        return batches;
//    }

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
        //Чтобы не терялись связи при обновлении
        updatedBatch.setBatchCreator(batchToBeUpdated.getBatchCreator());
        updatedBatch.setOwnUnit(batchToBeUpdated.getOwnUnit());
        updatedBatch.setRecipeSource(batchToBeUpdated.getRecipeSource());
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
