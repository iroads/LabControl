package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Batch;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.services.RecipeService;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UnitService unitService;

    @Autowired
    public RecipeController(RecipeService recipeService, UnitService unitService) {
        this.recipeService = recipeService;
        this.unitService = unitService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/index";
    }

    @GetMapping("/new")
    public String newRecipe(Model model, @ModelAttribute("recipe") Recipe recipe) {
        model.addAttribute("mixTypes", MixType.values());
        model.addAttribute("mixLayers", MixLayer.values());
        model.addAttribute("mixTraffics", MixTraffic.values());
        model.addAttribute("units", unitService.findAll());
        return "recipe/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @PostMapping()
    public String create(@ModelAttribute("recipe") Recipe recipe) {
        recipeService.save(recipe, recipe.getUnitId());
        return "redirect:/recipes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("mixTypes", MixType.values());
        model.addAttribute("mixLayers", MixLayer.values());
        model.addAttribute("mixTraffics", MixTraffic.values());
        model.addAttribute("units", unitService.findAll());
        return "recipe/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("recipe") Recipe recipe,
                         @PathVariable("id") int id) {
        recipeService.update(id, recipe);
        return "redirect:/recipes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        recipeService.deleteById(id);
        return "redirect:/recipes";
    }
}
