package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.techjobs.persistent.models.JobData;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */

//-- marks this class as a controller where MVC controller logic is implemented --//
@Controller

//-- maps HTTP requests to "/list" URL to the methods in this controller --//
@RequestMapping(value = "list")
public class ListController {

    //-- injects the JobRepository bean automatically --//
    @Autowired
    private JobRepository jobRepository;

    //-- injects the EmployerRepository bean automatically --//
    @Autowired
    private EmployerRepository employerRepository;

    //-- injects the SkillRepository bean automatically --//
    @Autowired
    private SkillRepository skillRepository;

    //-- static map to store column choices for the UI --//
    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        //-- initializes column choices with predefined values --//
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("skill", "Skill");
    }

    //-- default method that maps to the base "/list" URL --//
    @RequestMapping("")
    public String list(Model model) {

        //-- adds all skills and employers to the model to be available for rendering by the view --//
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());

        //-- returns the name of the template to be rendered (list.html) --//
        return "list";
    }

    //-- maps to "/list/jobs" URL to display jobs filtered by column and value --//
    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {

        //-- declares an iterable collection of jobs --//
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")){

            //-- if the column to filter by is "all" fetch all jobs from the repository --//
            jobs = jobRepository.findAll();
            model.addAttribute("title", "All Jobs");
        } else {

            //-- otherwise, filter jobs by the specified column and value --//
            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }

        //-- adds the jobs to the model --//
        model.addAttribute("jobs", jobs);

        //-- returns the name of the template to be rendered (list-jobs.html) --//
        return "list-jobs";
    }
}