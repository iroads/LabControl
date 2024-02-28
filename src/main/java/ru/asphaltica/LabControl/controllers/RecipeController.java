package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.services.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/index";
    }


    @PostMapping()
    public String create() {
        Recipe recipe = new Recipe();
        Mineral mineral = new Mineral();
        mineral.setManufacturer("ООО Битум");
        recipe.setPowder(mineral);
        recipeService.save(recipe);
        return "redirect:/users";
    }
}
