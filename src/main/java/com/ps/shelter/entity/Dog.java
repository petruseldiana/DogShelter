package com.ps.shelter.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity(name = "s_dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;

    private int ageInYears;

    private int ageInMonths;

    private String gender;

    private String status;

    @ManyToMany(mappedBy = "dogList")
    private  List<Veterinary> veterinaryList;

    public Dog() {
    }

    public Dog(String name, String breed, int ageInYears, int ageInMonths, String gender, String status) {
        this.name = name;
        this.breed = breed;
        this.ageInYears = ageInYears;
        this.ageInMonths = ageInMonths;
        this.gender = gender;
        this.status = status;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
