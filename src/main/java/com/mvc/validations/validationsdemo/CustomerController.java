package com.mvc.validations.validationsdemo;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller()
public class CustomerController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String getForm(Model model) {

        model.addAttribute("costumer", new CostumerValidation());

        return "CostumerForm";

    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("costumer") CostumerValidation costumerValidation,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "CostumerForm";
        return "CostumerConfirmation";

    }

}
