package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Group;
import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
// trae a todos las personas de la base de datos
// retorna una lista de personas [{},{},{},{}]
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

// Busca a una persona por su identificador 
// una vez encotrado lo retorna en un tipo de Person que almacena los datos de una persona
// El orElse se utiliza si en caso no se encontró ningun valor por lo que devuelve un nulo    
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

// Se envia una nueva persona a la base de datos y se retorna una tipo de persona que se envio
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

// Busca una a una persona primero por su identificador id
// si no le encuentra retorna una persona vacio para que no haya conflictos de puntos nulos
// Una vez que lo a encotrado realiza una obtencion de todos los grupos en los que esta la 
// persona


    public Set<Group> getPersonGroups(Long id) {
        return personRepository.findById(id).orElse(null).getGroups();
    }


}

