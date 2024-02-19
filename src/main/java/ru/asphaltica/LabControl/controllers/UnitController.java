package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.services.UserService;


@Controller
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;
    private final UserService userService;

    @Autowired
    public UnitController(UnitService unitService, UserService userService) {
        this.unitService = unitService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("units", unitService.finaAll());
        return "unit/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("unit", unitService.findById(id));
        model.addAttribute("users", userService.findByUnitId(id));
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
