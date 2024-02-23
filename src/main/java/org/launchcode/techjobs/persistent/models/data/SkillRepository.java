package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//-- @Repository annotation indicates that this interface is a Spring Data repository --//
//-- spring will automatically implement this interface at runtime --//
@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
    //-- this interface extends CrudRepository, providing generic CRUD operations for Skill entities --//
    //-- Skill: The type of entity to operate on --//
    //-- Integer: The type of the id field of the entity --//

    //-- by extending CrudRepository, SkillRepository inherits methods to manage Skill entities, --//
    //-- including basic CRUD operations (create, read, update, delete) and pagination capabilities --//

    //-- implementing the interface is not required. Spring Data JPA automatically creates a concrete class that implements this interface --//
    //-- allows for direct autowiring of this repository in service classes or controllers to perform data operations --//
}
