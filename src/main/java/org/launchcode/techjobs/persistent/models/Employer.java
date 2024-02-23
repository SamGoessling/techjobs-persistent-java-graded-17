package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

//-- marks this class as a JPA entity to be mapped to a database table --//
@Entity

//-- inherits common properties like id and name from "AbstractEntity" --//
public class Employer extends AbstractEntity {

    //-- validation constraint that ensures the location field is not blank --//
    @NotBlank

    //-- ensures the location does not exceed 255 characters --//
    @Size(max = 255, message = "Location can only be 255 characters long.")

    //-- stores the location of the employer --//
    private String location;

    //-- specifies a one-to-many relationship with the Job entity --//
    @OneToMany

    //-- specifies the foreign key column in the Job table --//
    @JoinColumn(name = "employer_id")

    //-- list of jobs associated with this employer --//
    private List<Job> jobs = new ArrayList<>();

    public Employer(String location) {

        //-- constructor that sets the location of the employer --//
        this.location = location;
    }

    //-- default constructor required by JPA --//
    public Employer() {}

    //-- getter for location --//
    public String getLocation() {
        return location;
    }

    //-- setter for location --//
    public void setLocation(String location) {
        this.location = location;
    }

    //-- getter for the list of jobs --//
    public List<Job> getJobs() {
        return jobs;
    }

    //-- setter for the list of jobs allowing modification of the associated jobs --//
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
