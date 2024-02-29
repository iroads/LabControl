package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.services.PlantService;
import ru.asphaltica.LabControl.services.RecipeService;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.services.UserService;
import ru.asphaltica.LabControl.util.enums.HttpMethod;


@Controller
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;
    private final UserService userService;
    private final PlantService plantService;

    @Autowired
    public UnitController(UnitService unitService, UserService userService, PlantService plantService) {
        this.unitService = unitService;
        this.userService = userService;
        this.plantService = plantService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("units", unitService.findAll());
        return "unit/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("unit", unitService.findById(id));
        model.addAttribute("users", userService.findByUnitId(id));
        model.addAttribute("plants", plantService.findByUnitId(id));
        return "unit/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        unitService.deleteById(id);
        return "redirect:/units";
    }

    @GetMapping("/new")
    public String newUnit(@ModelAttribute("unit") Unit unit) {
        return "unit/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("unit") Unit unit) {
        unitService.save(unit);
        return "redirect:/units";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("unit", unitService.findById(id));

        return "unit/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("unit") Unit unit,
                         @PathVariable("id") int id) {
        unitService.update(id, unit);
        return "redirect:/units";
    }
}
