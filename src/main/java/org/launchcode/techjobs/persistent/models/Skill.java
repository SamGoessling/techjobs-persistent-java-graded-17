package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

//-- marks this class as a JPA entity. it will be mapped to a table in the database --//
@Entity

//-- inherits common properties like id and name from AbstractEntity. includes annotations for ID and generated value --//
public class Skill extends AbstractEntity {

    //-- ensures the description does not exceed 255 characters. provides a validation message if it does --//
    @Size(max = 255, message = "Description must not exceed 255 characters.")

    //-- a field to store a description of the skill --//
    private String description;

    //-- establishes a many-to-many relationship with the Job entity. the "mappedBy" attribute indicates that the "skills" field in the Job entity owns the relationship --//
    @ManyToMany(mappedBy = "skills")

    //-- a list to store the jobs associated with this skill. initialized to prevent null pointer exceptions --//
    private List<Job> jobs = new ArrayList<>();

    //-- constructor that takes a description parameter allowing for instantiation of Skill with a description --//
    public Skill(String description) {
        this.description = description;
    }

    //-- default constructor required by JPA --//
    public Skill() {}

    // Getter for the description field.
    public String getDescription() {
        return description;
    }

    // Setter for the description field, allowing the description to be changed.
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for the jobs list, returning the list of jobs associated with this skill.
    public List<Job> getJobs() {
        return jobs;
    }

    // Setter for the jobs list, allowing new jobs to be added to this skill.
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
