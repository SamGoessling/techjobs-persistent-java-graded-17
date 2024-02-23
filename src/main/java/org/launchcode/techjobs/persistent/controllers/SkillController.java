package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller //-- marks this class as a Spring MVC controller, responsible for handling web requests --//
@RequestMapping("skills") //-- maps all web requests that start with "/skills" to methods in this controller --//
public class SkillController {

    @Autowired //-- this annotation is used to automatically inject the SkillRepository instance --//
    private SkillRepository skillRepository;

    @GetMapping("/") //-- maps GET requests for "/skills/" to this method --//
    public String index(Model model) {
        //-- adds all skills found in the skillRepository to the model under the attribute "skills" --//
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index"; //-- returns the view template located at "src/main/resources/templates/skills/index.html" --//
    }

    @GetMapping("add") //-- maps GET requests for "/skills/add" to this method --//
    public String displayAddSkillForm(Model model) {
        //-- adds a new, empty Skill object to the model to be filled out from the form --//
        model.addAttribute(new Skill());
        return "skills/add"; //-- returns the view template for adding a new skill --//
    }

    @PostMapping("add") //-- maps POST requests for "/skills/add" to this method, handling form submission --//
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, Errors errors, Model model) {
        //-- checks if there are errors in the form (e.g., missing required fields) --//
        if (errors.hasErrors()) {
            return "skills/add"; //-- if there are errors, re-render the form to show them --//
        } else {
            skillRepository.save(newSkill); //-- if there are no errors, save the new skill to the database --//
            return "redirect:"; //-- redirects to "/skills/", displaying the list of skills --//
        }
    }

    @GetMapping("view/{skillId}") //-- maps GET requests for "/skills/view/{skillId}" to this method --//
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        //-- attempts to find the skill with the given ID in the database --//
        Optional<Skill> optSkill = skillRepository.findById(skillId);

        if (optSkill.isPresent()) { //-- checks if the skill was found --//
            Skill skill = optSkill.get(); //-- gets the skill object if present --//
            model.addAttribute("skill", skill); //-- adds the found skill to the model to be displayed --//
            return "skills/view"; //-- returns the view template for displaying a single skill --//
        } else {
            return "redirect:../"; //-- if the skill is not found, redirects to the list of skills --//
        }
    }

}