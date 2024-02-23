package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    JobRepository jobRepository;

    //-- displays the main index page with a list of all jobs --//
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    //-- displays the form to add a new job --//
    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("job", new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    //-- processes the form for adding a new job, validates input --//
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
            Optional<Employer> optEmployer = employerRepository.findById(employerId);
            if (optEmployer.isPresent()) {
                Employer employer = optEmployer.get();
                newJob.setEmployer(employer);
            }

            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

            jobRepository.save(newJob);

            return "redirect:";
        }
    }

    //-- displays details of a single job by ID --//
    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = optJob.get();
            model.addAttribute("job", job);
        }
        return "view";
    }
}