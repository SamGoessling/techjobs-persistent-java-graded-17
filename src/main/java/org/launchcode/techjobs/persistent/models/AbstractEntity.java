package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

//-- @MappedSuperclass annotation specifies that this class is a mapped superclass --//
//-- provides mapping information for its subclasses. --//
@MappedSuperclass
public abstract class AbstractEntity {

    //-- @Id marks this field as the primary key. --//
    //-- @GeneratedValue indicates that the ID should be generated automatically. --//
    @Id
    @GeneratedValue
    private int id;

    //-- @NotBlank ensures the name field is not null or empty --//
    //-- @Size specifies the maximum length of the name field to be 255 characters --//
    @NotBlank(message = "Must include name.")
    @Size(max = 255, message = "Name can only be 255 characters long.")
    private String name;

    //-- Getter for the ID --//
    public int getId() {
        return id;
    }

    //-- Getter for the name --//
    public String getName() {
        return name;
    }

    //-- Setter for the name --//
    //-- allows changing the name of the entity --//
    public void setName(String name) {
        this.name = name;
    }

    //-- Overrides the toString method to return the entity's name --//
    //-- Useful for debugging or logging --//
    @Override
    public String toString() {
        return name;
    }

    //-- Overrides the equals method to compare entities based on their ID --//
    //-- crucial for entity comparison, especially in collections --//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Checks if the objects are the same.
        if (o == null || getClass() != o.getClass()) return false; // Checks if the object is null or not of the same class.
        AbstractEntity that = (AbstractEntity) o; // Casts the Object to AbstractEntity for comparison.
        return id == that.id; // Compares the IDs for equality.
    }

    //-- Overrides the hashCode method --//
    //-- important for the efficient operation of collections such as HashMap and HashSet --//
    @Override
    public int hashCode() {

        //-- generates a hash code based on the entity's ID --//
        return Objects.hash(id);
    }

}
