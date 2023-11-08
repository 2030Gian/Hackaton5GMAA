package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.example.demo.repositories.TypeGroupRepository;
import com.example.demo.entities.TypeGroup;

@Service
@Transactional
public class TypeGroupService {

    @Autowired
    private TypeGroupRepository typeGroupRepository;

    public void create(TypeGroup typeGroup){
        typeGroupRepository.save(typeGroup);
    }

    public List<TypeGroup> read() {
        return typeGroupRepository.findAll();
    }

    
}

