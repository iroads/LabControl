package ru.asphaltica.LabControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.asphaltica.LabControl.models.Plant;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {

}
