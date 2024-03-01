package ru.asphaltica.LabControl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.repositories.RecipeRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    @Transactional
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Transactional
    public void update(int id, Recipe updatedRecipe) {
        //Recipe recipeToBeUpdated = recipeRepository.findById(id).get();
        updatedRecipe.setId(id);
//        updatedUser.setUnit(userToBeUpdated.getUnit()); //чтобы не терялась связь при обновлении
//        //Проверяем если пароль в форме пришел пустой то не меняем его
//        if (!updatedUser.getPassword().isEmpty()) {
//            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
//        } else {
//            updatedUser.setPassword(userToBeUpdated.getPassword());
//        }
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
