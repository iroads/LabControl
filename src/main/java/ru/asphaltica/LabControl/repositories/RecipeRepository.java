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
   //List<Recipe> findAllByUnit();
//   @Query(
//           value = "SELECT  r.id, create_date, modify_date, mix_type, mix_layer, mix_traffic, stone_coarse1, stone_coarse2, stone_coarse3, stone_coarse4, stone_coarse5, stone_coarse6, stone_fine1, stone_fine2, powder, bitumen, additive1, additive2, pp0063, pp0125, pp025, pp05, pp1, pp2, pp4, pp56, pp8, pp112, pp16, pp224, pp315, gravity_mix_bulk, gravity_mix_maximum, water_resistance, track_depth, unit_id, u.name FROM recipe r LEFT JOIN UNIT u ON r.unit_id=u.id WHERE (r.create_date BETWEEN ?1 and  ?2) AND (r.mix_type=?3) AND (u.name=?4)",
//           nativeQuery = true)
//   List<Recipe> findAllRecipeJoinUnit(LocalDateTime startDate, LocalDateTime endDate, String mixType, String unitName);
}
