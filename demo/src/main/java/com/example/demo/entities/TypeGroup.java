package com.example.demo.entities;
import jakarta.persistence.*;
import java.util.Set;
@Entity
@Table(name = "type_group")
public class TypeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeGroup", cascade = CascadeType.ALL)
    private Set<Group> groups;
    
    public TypeGroup() {};

    public TypeGroup(String name) {
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

