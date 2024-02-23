package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//-- marks this interface as a Repository component in the Spring application context --//
//-- spring Data JPA will provide the implementation automatically at runtime --//
@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
    //-- inherits CRUD (Create, Read, Update, Delete) operations for Employer entities from CrudRepository --//
    //-- employer: the entity type the repository manages --//
    //-- integer: the type of the entity ID --//

    //-- by extending CrudRepository, EmployerRepository inherits several methods for working with Employer persistence --//
    //-- including methods for saving, deleting, and finding Employer entities --//

    //-- no need to implement the interface. Spring Data JPA generates the implementation dynamically at runtime --//
}