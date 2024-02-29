package ru.asphaltica.LabControl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asphaltica.LabControl.models.Unit;
import ru.asphaltica.LabControl.models.User;
import ru.asphaltica.LabControl.security.PersonDetails;
import ru.asphaltica.LabControl.services.UnitService;
import ru.asphaltica.LabControl.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UnitService unitService;


    @Autowired
    public UserController(UserService userService, UnitService unitService) {
        this.userService = userService;
        this.unitService = unitService;
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("unit") Unit unit) {
        model.addAttribute("users", userService.findAll());
        //Получение из сессии данных об аутентифицированном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        //model.addAttribute("authUser", personDetails.getUsername());
        return "user/index";
    }
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/new")
    public String newUnit(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("unit") Unit unit, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("ownUnit", user.getUnit());
        model.addAttribute("units", unitService.findAll());
        return "user/show";
    }

    @GetMapping("/set-unit/{id}")
    public String setUnit(@PathVariable("id") int id, @ModelAttribute("unit") Unit unit, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("ownUnit", user.getUnit());
        model.addAttribute("units", unitService.findAll());
        return "user/set_unit";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}/free")
    public String free(@PathVariable("id") int id) {
        userService.free(id);
        return "redirect:/users";
    }

    @PatchMapping("/{id}/add")
    public String add(@PathVariable("id") int id, @ModelAttribute("unit") Unit unit) {
        userService.addOwnUnit(id, unit);
        return "redirect:/users";
    }

//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }

}
