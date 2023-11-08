package com.example.demo.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.PersonDTO;
import com.example.demo.entities.Group;
import com.example.demo.entities.Person;
import com.example.demo.services.PersonService;


@Controller
@RequestMapping("/persons")
@CrossOrigin(origins = "*")
public class PersonController {


    // Inyectamos una instancia de PersonService para poder utilizarlo en 
    // en el controlador PersonController

    // Esto implica que si trabajamos con servicios necesitamos inyectar un servicio
    // tenemos que inicializarlo como si fuera un constructor y tambien tenemos que 
    // preguntarnos que no sea nulo
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        Assert.notNull(personService, "PersonService must not be null!");
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        List<PersonDTO> personDTOs = persons.stream().map(this::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(personDTOs);
    }


    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // vamos a agregar una persona a un grupo  
    // Necesitamos el id que nos estan pasando 
    // luego por medio de id
    // traemos todos los grupos en el que participa esta persona

    @GetMapping("/groups/{id}")
    public ResponseEntity<Set<Group>> getGroupsByPersonId(@PathVariable Long id) {
        Set<Group> groups = personService.getPersonGroups(id);
        return ResponseEntity.ok(groups);
    }
    
    // Creamos una persona pasamos una persona como se ha definido en el constructor
    // Creamos una variable temporal para guardar el tipo de persona que estamos creando 
    // retornamos que hemos creado exitosamente y la persona que hemos creado 


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }
}
