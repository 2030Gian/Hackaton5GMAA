package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TypeGroup;

public interface TypeGroupRepository extends JpaRepository<TypeGroup,Long> {

    
}
