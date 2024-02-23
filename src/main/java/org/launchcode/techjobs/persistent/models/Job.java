package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import java.util.List;

@Entity // Marks this class as a JPA entity, meaning it will be mapped to a table in the database.
public class Job extends AbstractEntity {
    // Inherits id and name fields from AbstractEntity class, including getters, setters, and annotations.

    @ManyToOne // Indicates a many-to-one relationship between Job and Employer entities.
    private Employer employer; // Each job is associated with one employer.

    @ManyToMany // Indicates a many-to-many relationship between Job and Skill entities.
    private List<Skill> skills; // A job can have many skills, and a skill can be associated with many jobs.

    public Job() {
        // Default constructor required by JPA.
    }

    public Job(Employer anEmployer, List<Skill> someSkills) {
        super(); // Calls the constructor of AbstractEntity, even though it's empty, to follow best practices.
        this.employer = anEmployer; // Initializes the employer field with the provided employer.
        this.skills = someSkills; // Initializes the skills field with the provided list of skills.
    }

    // Getter for the employer field.
    public Employer getEmployer() {
        return employer;
    }

    // Setter for the employer field. Allows changing the employer of this job.
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    // Getter for the skills field.
    // Returns the list of skills associated with this job.
    public List<Skill> getSkills() {
        return skills;
    }

    // Setter for the skills field.
    // Allows updating the list of skills associated with this job.
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}