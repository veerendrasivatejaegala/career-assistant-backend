package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String javaSkill;
    private String dsaSkill;
    private String reactSkill;
    private String communicationSkill;

    private Integer score;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJavaSkill() { return javaSkill; }
    public void setJavaSkill(String javaSkill) { this.javaSkill = javaSkill; }

    // same for all fields
}