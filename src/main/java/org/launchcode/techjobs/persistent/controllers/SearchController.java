package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.techjobs.persistent.controllers.ListController.columnChoices;

@Controller //-- marks this class as a controller in the Spring MVC framework, handling web requests --//
@RequestMapping("search") //-- maps all requests with "/search" URL path to this controller --//
public class SearchController {

    @Autowired //-- automatically injects an instance of JobRepository into this controller --//
    private JobRepository jobRepository;

    @RequestMapping("") //-- maps the base "/search" URL to this method --//
    public String search(Model model) {

        //-- adds the list of search options to the model, making it available to the search view --//
        model.addAttribute("columns", columnChoices);
        return "search"; //-- returns the "search.html" template for rendering --//
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results") //-- maps POST requests of "/search/results" to this method, handling form submission --//
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Job> jobs; //-- variable to store the search results --//

        //-- checks if the search term is 'all' or empty, meaning the user wants to find every job --//
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = jobRepository.findAll(); // Retrieves all jobs from the repository.
        } else {

            //-- otherwise, searches for jobs based on the specified type and term --//
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
        }

        //-- adds the necessary attributes to the model for the search results view --//
        model.addAttribute("columns", columnChoices); // Adds the search column options to the model.
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm); // Sets a dynamic title for the page based on the search criteria.
        model.addAttribute("jobs", jobs); // Adds the search result jobs to the model.

        return "search"; //-- redirects to the "search.html" template displaying the search results --//
    }
}