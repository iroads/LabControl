package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Batch;
import ru.asphaltica.LabControl.models.Recipe;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.security.PersonDetails;
import ru.asphaltica.LabControl.services.RecipeService;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.util.DateTimeUtil;
import ru.asphaltica.LabControl.util.enums.ChoosingType;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.SimpleFormatter;

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
    public String index(Model model,
                        @RequestParam(value = "start_date") Optional<String> startDate,
                        @RequestParam(value = "end_date") Optional<String> endDate,
                        @RequestParam(value = "mix_type") Optional<String> mixType,
                        @RequestParam(value = "mix_layer") Optional<String> mixLayer,
                        @RequestParam(value = "mix_traffic") Optional<String> mixTraffic,
                        @RequestParam(value = "selected_unit_to_controller") Optional<Integer> selectedToControllerUnit,
                        @RequestParam(value = "choosing_type_to_controller") Optional<ChoosingType> choosingTypeToController) {

        String start = startDate.orElse(null);
        LocalDate localDateStart = DateTimeUtil.parseLocalDate(start);
        LocalDateTime localDateTimeStart = DateTimeUtil.atStartOfDayOrMin(localDateStart);

        String end = endDate.orElse(null);
        LocalDate localDateEnd = DateTimeUtil.parseLocalDate(end);
        LocalDateTime localDateTimeEnd = DateTimeUtil.atStartOfNextDayOrMax(localDateEnd);

        String mixTypeS = mixType.map(t -> t.isEmpty() ? null : t).orElse(null);
        String mixLayerS = mixLayer.map(t -> t.isEmpty() ? null : t).orElse(null);
        String mixTrafficS = mixTraffic.map(t -> t.isEmpty() ? null : t).orElse(null);
        int unitId = selectedToControllerUnit.orElse(0);

        model.addAttribute("recipes",
                recipeService.findAllCustom(localDateTimeStart, localDateTimeEnd, mixTypeS, mixLayerS, mixTrafficS, unitService.findById(unitId)));
        model.addAttribute("start_date", start);
        model.addAttribute("end_date", end);
        model.addAttribute("mix_type", mixType);
        model.addAttribute("mix_layer", mixLayer);
        model.addAttribute("mix_traffic", mixTraffic);
        model.addAttribute("selected_unit_from_controller", selectedToControllerUnit);
        model.addAttribute("choosingType", choosingTypeToController.isPresent() ? choosingTypeToController.get() : ChoosingType.nothing);
        model.addAttribute("ChoosingType", ChoosingType.class);
        return "recipe/index";
    }


    @GetMapping("/new")
    public String newRecipe(@ModelAttribute("recipe") Recipe recipe) {
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id,
                       @ModelAttribute("mixTypes") MixType[] mixTypes,
                       @ModelAttribute("mixLayers") MixLayer[] mixLayers,
                       @ModelAttribute("mixTraffics") MixTraffic[] mixTraffics) {
        model.addAttribute("recipe", recipeService.findById(id));
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
