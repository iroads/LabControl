package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.*;
import ru.asphaltica.LabControl.security.PersonDetails;
import ru.asphaltica.LabControl.services.BatchService;
import ru.asphaltica.LabControl.services.PlantService;
import ru.asphaltica.LabControl.services.RecipeService;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.util.DateTimeUtil;
import ru.asphaltica.LabControl.util.enums.ChoosingType;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;
    private final PlantService plantService;
    private final RecipeService recipeService;
    private final UnitService unitService;

    @Autowired
    public BatchController(BatchService batchService, PlantService plantService, RecipeService recipeService, UnitService unitService) {
        this.batchService = batchService;
        this.plantService = plantService;
        this.recipeService = recipeService;
        this.unitService = unitService;
    }

    @ModelAttribute(name = "authUser")
    public User authUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUser();
    }

    @ModelAttribute(name = "mixTypes")
    public MixType[] mixTypes() {
        return MixType.values();
    }

    @ModelAttribute(name = "mixLayers")
    public MixLayer[] mixLayers() {
        return MixLayer.values();
    }

    @ModelAttribute(name = "mixTraffics")
    public MixTraffic[] mixTraffics() {
        return MixTraffic.values();
    }

    @ModelAttribute(name = "units")
    public List<Unit> units() {
        return unitService.findAll();
    }

    @ModelAttribute(name = "plants")
    public List<Plant> plants() {
        return plantService.findAll();
    }


    @GetMapping()
    public String index(Model model, @ModelAttribute("authUser") User authUser,
                        @RequestParam(value = "start_date") Optional<String> startDate,
                        @RequestParam(value = "end_date") Optional<String> endDate,
                        @RequestParam(value = "mix_type") Optional<String> mixType,
                        @RequestParam(value = "mix_layer") Optional<String> mixLayer,
                        @RequestParam(value = "mix_traffic") Optional<String> mixTraffic,
                        @RequestParam(value = "selected_unit_to_controller") Optional<Integer> selectedToControllerUnit,
                        @RequestParam(value = "selected_plant_to_controller") Optional<Integer> selectedToControllerPlant,
                        @RequestParam(value = "recipe_source") Optional<Integer> recipeSourceId) {

        String start = startDate.orElse(null);
        LocalDate localDateStart = DateTimeUtil.parseLocalDate(start);
        LocalDateTime localDateTimeStart = DateTimeUtil.atStartOfDayOrMin(localDateStart);

        String end = endDate.orElse(null);
        LocalDate localDateEnd = DateTimeUtil.parseLocalDate(end);
        LocalDateTime localDateTimeEnd = DateTimeUtil.atStartOfNextDayOrMax(localDateEnd);

        Recipe choseRecipe = recipeService.findById(recipeSourceId.orElse(0));


        //Unit unit = authUser.getUnit();
        Unit ownUnit = unitService.findById(selectedToControllerUnit.orElse(0));
        Plant ownPlant = plantService.findById(selectedToControllerPlant.orElse(0));
        model.addAttribute("batches", batchService.findAllCustom(choseRecipe, ownUnit, ownPlant,  localDateTimeStart, localDateTimeEnd));
        model.addAttribute("start_date", start);
        model.addAttribute("end_date", end);
        model.addAttribute("mix_type", mixType);
        model.addAttribute("mix_layer", mixLayer);
        model.addAttribute("mix_traffic", mixTraffic);
        model.addAttribute("selected_unit_from_controller", selectedToControllerUnit);
        model.addAttribute("selected_plant_from_controller", selectedToControllerPlant);
        model.addAttribute("choseRecipe", choseRecipe);
        return "/batch/index";
    }

    @GetMapping("/choose_recipe")
    public String getRecipeForFindBatch(Model model, @ModelAttribute("recipe") Recipe recipe) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("mix_type", Optional.empty());
        model.addAttribute("mix_layer", Optional.empty());
        model.addAttribute("mix_traffic", Optional.empty());
        model.addAttribute("selected_unit_from_controller", Optional.empty());
        model.addAttribute("choosingType", ChoosingType.choosingForFindBatch);
        model.addAttribute("ChoosingType", ChoosingType.class);
        return "recipe/index";
    }

    @GetMapping("/new_step1")
    public String newBatchStep1(Model model, @ModelAttribute("recipe") Recipe recipe,
                                @ModelAttribute("batch") Batch batch) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("mix_type", Optional.empty());
        model.addAttribute("mix_layer", Optional.empty());
        model.addAttribute("mix_traffic", Optional.empty());
        model.addAttribute("selected_unit_from_controller", Optional.empty());
        model.addAttribute("choosingType", ChoosingType.choosingForNewBatch);
        model.addAttribute("ChoosingType", ChoosingType.class);
        return "recipe/index";
    }

    @GetMapping("/new_step2")
    public String newBatchStep2(Model model, @ModelAttribute("batch") Batch batch,
                                @ModelAttribute("authUser") User authUser) {
        List<Plant> plantsOfAuthUser = plantService.findByUnitId(authUser.getUnit().getId());
        model.addAttribute("plants", plantsOfAuthUser);
        model.addAttribute("recipeSource", batch.getRecipeSource());
        model.addAttribute("batch", batch);
        return "batch/new_step2";
    }

    @PostMapping()
    public String create(@ModelAttribute("batch") Batch batch, @ModelAttribute("authUser") User authUser) {
        Plant ownPlant = plantService.findById(batch.getPlantId());
        batchService.save(batch, authUser, ownPlant);
        return "redirect:/batches";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, @ModelAttribute("authUser") User authUser) {
        Batch batch = batchService.findById(id);
        model.addAttribute("batch", batch);
        List<Plant> plantsOfAuthUser = plantService.findByUnitId(authUser.getUnit().getId());

        //Делаем так, чтобы в List с заводами первым был элемент который содержится в Партии
        //для того чтобы он был выбран по умолчанию, th:selected не работает
        for (int i = 0; i < plantsOfAuthUser.size(); i++) {
            if (plantsOfAuthUser.get(i).getId() == batch.getOwnPlant().getId() && i != 0) {
                Plant plant0 = plantsOfAuthUser.get(0);
                plantsOfAuthUser.set(0, plantsOfAuthUser.get(i));
                plantsOfAuthUser.set(i, plant0);
            }
        }

        model.addAttribute("plants", plantsOfAuthUser);
        return "batch/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("batch") Batch batch) {
        batch.setOwnPlant(plantService.findById(batch.getPlantId()));
        batchService.update(id, batch);
        return "redirect:/batches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        batchService.deleteById(id);
        return "redirect:/batches";
    }
}
