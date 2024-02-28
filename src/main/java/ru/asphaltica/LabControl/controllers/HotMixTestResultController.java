package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.asphaltica.LabControl.models.HotMixTestResult;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.services.HotMixTestResultService;

@Controller
@RequestMapping("/results")
public class HotMixTestResultController {

    private final HotMixTestResultService hotMixTestResultService;

    @Autowired
    public HotMixTestResultController(HotMixTestResultService hotMixTestResultService) {
        this.hotMixTestResultService = hotMixTestResultService;
    }

    @GetMapping()
    public String showResultsList(Model model) {
        model.addAttribute("results", hotMixTestResultService.findAll());
        return "result/results_list";
    }

    @GetMapping("/new")
    public String newResult(@ModelAttribute("result") HotMixTestResult result) {
        return "result/create_new_result";
    }

    @PostMapping()
    public String create(@ModelAttribute("result") HotMixTestResult result) {
        hotMixTestResultService.save(result);
        return "redirect:/results";
    }
}
