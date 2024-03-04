package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Plant;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.services.PlantService;
import ru.asphaltica.LabControl.services.UnitService;

@Controller
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;
    private final UnitService unitService;

    @Autowired
    public PlantController(PlantService plantService, UnitService unitService) {
        this.plantService = plantService;
        this.unitService = unitService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("plants", plantService.findAll());
        return "plant/index";
    }

    @GetMapping("/set-unit/{id}")
    public String setUnit(@PathVariable("id") int id, @ModelAttribute("unit") Unit unit, Model model){
        Plant plant = plantService.findById(id);
        model.addAttribute("plant", plant);
        model.addAttribute("ownUnit", plant.getUnit());
        model.addAttribute("units", unitService.findAll());
        return "plant/set_unit";
    }

    @GetMapping("/new")
    public String newPlant(@ModelAttribute("plant") Plant plant) {
        return "plant/new";
    }

    @PatchMapping("/{id}/add")
    public String add(@PathVariable("id") int id, @ModelAttribute("unit") Unit unit) {
        plantService.addOwnUnit(id, unit);
        return "redirect:/plants";
    }

    @PostMapping()
    public String create(@ModelAttribute("plant") Plant plant) {
        plantService.save(plant);
        return "redirect:/plants";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("plant", plantService.findById(id));
        return "plant/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("plant") Plant plant,
                         @PathVariable("id") int id) {
        plantService.update(id, plant);
        return "redirect:/plants";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        plantService.deleteById(id);
        return "redirect:/plants";
    }


}
