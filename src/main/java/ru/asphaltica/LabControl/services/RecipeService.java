package ru.asphaltica.LabControl.services;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.repositories.RecipeRepository;
import ru.asphaltica.LabControl.repositories.UnitRepository;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UnitRepository unitRepository) {
        this.recipeRepository = recipeRepository;
        this.unitRepository = unitRepository;
    }

    public List<Recipe> findAllCustom(LocalDateTime startDate, LocalDateTime endDate, String mixType, String mixLayer, String mixTraffic, Unit unit) {
        List<Recipe> recipes = recipeRepository.findAll(Specification.allOf(
                (root, query, criterialBuilder) -> criterialBuilder.between(root.get("createDate"), startDate, endDate),
                (root, query, criterialBuilder) -> mixType == null ? null : criterialBuilder.equal(root.get("mixType"), MixType.findValueByType(mixType)),
                (root, query, criterialBuilder) -> mixLayer == null ? null : criterialBuilder.equal(root.get("mixLayer"), MixLayer.findValueByLayer(mixLayer)),
                (root, query, criterialBuilder) -> mixTraffic == null ? null : criterialBuilder.equal(root.get("mixTraffic"), MixTraffic.findValueByTraffic(mixTraffic)),
                (root, query, criterialBuilder) -> unit == null ? null : criterialBuilder.equal(root.get("unit"), unit)
        ), Sort.by("id"));
        return recipes;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Transactional
    public void save(Recipe recipe, int unitId) {
        recipe.setUnit(unitRepository.findById(unitId).get());
        recipeRepository.save(recipe);
    }

    @Transactional
    public void update(int id, Recipe updatedRecipe) {
        Recipe recipeToBeUpdated = recipeRepository.findById(id).get();
        updatedRecipe.setId(id);
        updatedRecipe.setUnit(recipeToBeUpdated.getUnit());
//        updatedUser.setUnit(userToBeUpdated.getUnit()); //чтобы не терялась связь при обновлении
        updatedRecipe.setCreateDate(recipeToBeUpdated.getCreateDate());
        recipeRepository.save(updatedRecipe);
    }

    @Transactional
    public void deleteById(int id) {
        recipeRepository.deleteById(id);
    }

    public Recipe findById(int id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
