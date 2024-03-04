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

import java.util.List;

@Controller
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;
    private final PlantService plantService;

    @Autowired
    public BatchController(BatchService batchService, PlantService plantService) {
        this.batchService = batchService;
        this.plantService = plantService;
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
        model.addAttribute("batches", batchService.findAll());
        //Найти все партии филиала в котором работает сотрудник
        Unit unit = authUser.getUnit();
        model.addAttribute("batches", batchService.findAllByOwnUnit(unit));
        return "/batch/index";
    }

    @GetMapping("/new")
    public String newPlant(Model model, @ModelAttribute("batch") Batch batch, @ModelAttribute("authUser") User authUser) {
        List<Plant> plantsOfAuthUser = plantService.findByUnitId(authUser.getUnit().getId());
        model.addAttribute("plants", plantsOfAuthUser);
        return "batch/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("batch") Batch batch, @ModelAttribute("authUser") User authUser) {
        Plant ownPlant = plantService.findById(batch.getPlantId());
        batchService.save(batch, authUser, ownPlant);
        return "redirect:/batches";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("batch", batchService.findById(id));
        return "batch/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("batch") Batch batch) {
        batchService.update(id, batch);
        return "redirect:/batches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        batchService.deleteById(id);
        return "redirect:/batches";
    }
}
