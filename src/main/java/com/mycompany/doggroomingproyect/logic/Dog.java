package com.mycompany.doggroomingproyect.logic;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dog implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int customerNum;
    private String name;
    private String breed;
    private String color;
    private boolean alergic;
    private boolean specialAtention;
    private String observations;
    @OneToOne
    private Owner dogOwner;

    public Dog() {
    }

    public Dog(int customerNum, String name, String breed, String color, boolean alergic, boolean specialAtention, String observations, Owner dogOwner) {
        this.customerNum = customerNum;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.alergic = alergic;
        this.specialAtention = specialAtention;
        this.observations = observations;
        this.dogOwner = dogOwner;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlergic() {
        return alergic;
    }

    public void setAlergic(boolean alergic) {
        this.alergic = alergic;
    }

    public boolean isSpecialAtention() {
        return specialAtention;
    }

    public void setSpecialAtention(boolean specialAtention) {
        this.specialAtention = specialAtention;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Owner getDogOwner() {
        return dogOwner;
    }

    public void setDogOwner(Owner dogOwner) {
        this.dogOwner = dogOwner;
    }
    
    
}
