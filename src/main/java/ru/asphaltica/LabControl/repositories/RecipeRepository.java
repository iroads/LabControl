package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
