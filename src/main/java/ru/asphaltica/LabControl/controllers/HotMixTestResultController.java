package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.asphaltica.LabControl.models.*;
import ru.asphaltica.LabControl.services.BatchService;
import ru.asphaltica.LabControl.services.HotMixTestResultService;

@Controller
@RequestMapping("/results")
public class HotMixTestResultController {

    private final HotMixTestResultService hotMixTestResultService;
    private final BatchService batchService;

    @Autowired
    public HotMixTestResultController(HotMixTestResultService hotMixTestResultService, BatchService batchService) {
        this.hotMixTestResultService = hotMixTestResultService;
        this.batchService = batchService;
    }

    @GetMapping()
    public String showResultsList(Model model) {
        model.addAttribute("results", hotMixTestResultService.findAll());
        return "result/results_list";
    }

    @GetMapping("/new")
    public String newResult(@ModelAttribute("result") HotMixTestResult result) {
        return "new_step2";
    }

    @PostMapping()
    public String create(@ModelAttribute("result") HotMixTestResult result) {
        hotMixTestResultService.save(result);
        return "redirect:/results";
    }

    @GetMapping("/new_step1")
    public String newResultStep1(Model model,
                                 @ModelAttribute("batch") Batch batch,
                                @ModelAttribute("result") HotMixTestResult result) {
        model.addAttribute("batches", batchService.findAll());
        return "result/new_step1";
    }

    @GetMapping("/new_step2")
    public String newBatchStep2(Model model, @ModelAttribute("batch") HotMixTestResult result,
                                @ModelAttribute("authUser") User authUser) {
        //List<Plant> plantsOfAuthUser = plantService.findByUnitId(authUser.getUnit().getId());
        //model.addAttribute("plants", plantsOfAuthUser);
        model.addAttribute("batchSource", result.getBatchSource());
        model.addAttribute("result", result);
        return "result/new_step2";
    }
}
