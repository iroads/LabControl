package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.RecipeRepository;
import ru.asphaltica.LabControl.repositories.UnitRepository;

import java.util.Date;
import java.util.List;

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



    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
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
