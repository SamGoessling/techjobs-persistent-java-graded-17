package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    EmployerRepository employerRepository;

    //-- maps the base route "/employers" to this method, displaying all employers --//
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    //-- displays form to add a new employer --//
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    //-- processes the form for adding a new employer, validates input --//
    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        } else {
            employerRepository.save(newEmployer);
            return "redirect:";
        }
    }

    //-- displays a single employer details by ID --//
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional<Employer> optEmployer = employerRepository.findById(employerId);

        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);

            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
