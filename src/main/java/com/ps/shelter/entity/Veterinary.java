package com.ps.shelter.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "s_vet")
public class Veterinary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(length = 10)
    private String phone;

    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "s_dog_vet",
            joinColumns = { @JoinColumn(name = "dog_id") },
            inverseJoinColumns = { @JoinColumn(name = "vet_id") }
    )
    private  List<Dog> dogList;

    public Veterinary() {
    }

    public Veterinary(String name, String address, @Length(max = 10) String phone, @Email String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
