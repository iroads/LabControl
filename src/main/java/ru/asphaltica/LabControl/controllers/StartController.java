package ru.asphaltica.LabControl.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.asphaltica.LabControl.security.PersonDetails;

@Controller
@RequestMapping("/")
public class StartController {
    @GetMapping()
    public String getAdminPage(Model model) {
        //Получение из сессии данных об аутентифицированном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("authUser", personDetails.getUser());
        return "start/start_page";
    }
}
