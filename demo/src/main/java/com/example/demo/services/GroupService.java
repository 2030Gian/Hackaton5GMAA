package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Group;
import com.example.demo.entities.Person;
import com.example.demo.repositories.GroupRepository;

@Service
public class GroupService {


    // para poder aplicar operaciones en la base de datos usamos el @Autowired
    // de esta manera nosotros hemos inyectado y ahora podemos utilizar la operaciones
    // que nos ofrece el Jpa, findById(id_name), finAll(), deleteById()
    @Autowired
    private GroupRepository groupRepository;


    // Realizamos una inyeccion del servicio PersonService
    // una vez que realizamos esta inyeccion 
    @Autowired
    private PersonService personService;



    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Set<Person> getGroupPersons(Long id) {
        return groupRepository.findById(id).orElse(null).getPersons();
    }



    // Me tienen que pasar el indentificador de un grupo 
    // una vez que tengo el identificador del grupo lo que voy a hacer es 
    // traerlo a memoria con la funcion finById(id).orElse(null)




    // Añadimos a una persona a un grupo especifico si es que esta persona aun no esta en la 
    // base de datos
    public Group addPersonToGroup(Long id, Long personId) {

        // obtenemos el grupo que tiene el este id 

        Group group = groupRepository.findById(id).orElse(null);

        // si grupo no es nullo entonces traemos las personas que tienen todos estos grupos 


        // verificamos que grupo no sea nullo 

        if (group != null) {


            // entramos a grupo 
            // Traemos todas las personas que estan en este grupo 
            // traemos temporalmente a todas las personas asociadas al grupo 
            // una ves que lo hemos traido
            //
            //anhadimos si es que aun no esta presente la persona que tiene el identificador de este 

            //persona

            group.getPersons().add(personService.getPersonById(personId));

            return groupRepository.save(group);

        } else {
            return null;
        }
    }
}
