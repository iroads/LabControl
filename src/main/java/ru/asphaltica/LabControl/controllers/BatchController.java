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

import java.util.List;

@Controller
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;
    private final PlantService plantService;
    private final RecipeService recipeService;

    @Autowired
    public BatchController(BatchService batchService, PlantService plantService, RecipeService recipeService) {
        this.batchService = batchService;
        this.plantService = plantService;
        this.recipeService = recipeService;
    }

    @ModelAttribute(name = "authUser")
    public User authUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUser();
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("authUser") User authUser) {
        //Найти все партии
        //model.addAttribute("batches", batchService.findAll());
        //Найти все партии филиала в котором работает сотрудник
        Unit unit = authUser.getUnit();
        model.addAttribute("batches", batchService.findAllByOwnUnit(unit));
        return "/batch/index";
    }

    @GetMapping("/new_step1")
    public String newBatchStep1(Model model, @ModelAttribute("recipe") Recipe recipe,
                                @ModelAttribute("batch") Batch batch) {
        model.addAttribute("recipes", recipeService.findAll());
        return "batch/new_step1";
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
