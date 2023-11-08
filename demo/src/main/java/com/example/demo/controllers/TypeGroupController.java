package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.entities.TypeGroup;
import com.example.demo.services.TypeGroupService;

import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/typeGroups")
@CrossOrigin(origins = "*")
public class TypeGroupController {

    @Autowired
    private TypeGroupService typeGroupService;

    @GetMapping
    private ResponseEntity<List<TypeGroup>> readTypeGroup(){
        List<TypeGroup> typeGroups= typeGroupService.read();
        return new ResponseEntity<>(typeGroups,HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<String> addTypeGroup(@RequestBody TypeGroup typeGroup){
        typeGroupService.create(typeGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }



    
}


