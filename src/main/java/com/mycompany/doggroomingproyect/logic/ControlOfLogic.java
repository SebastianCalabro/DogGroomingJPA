package com.mycompany.doggroomingproyect.logic;

import com.mycompany.doggroomingproyect.persistence.ControlOfPersistence;
import java.util.List;

public class ControlOfLogic {
    ControlOfPersistence cop = new ControlOfPersistence();

    public void save(String name, String breed, String color, String observations, String owName, String owPhone, boolean alergic, boolean speAtention) {
        //creating owner
        Owner ow = new Owner();
        ow.setName(owName);
        ow.setPhoneOwner(owPhone);
        //creating dog
        Dog dog = new Dog();
        dog.setName(name);
        dog.setBreed(breed);
        dog.setColor(color);
        dog.setAlergic(alergic);
        dog.setSpecialAtention(speAtention);
        dog.setObservations(observations);
        dog.setDogOwner(ow);
        //Going to control of persistence to save in DB
        cop.save(ow,dog);
    }

    public List<Dog> getDogs() {
        return cop.getDogs();
    }

    public void deleteDog(int customerId) {
        cop.deleteDog(customerId);
    }

    public Dog getDog(int customerId) {
        return cop.getDog(customerId);
    }

    public void editDog(Dog d, String name, String breed, String color, String observations, String owName, String owPhone, boolean alergic, boolean speAtention) {
        d.setName(name);
        d.setBreed(breed);
        d.setColor(color);
        d.setObservations(observations);
        d.getDogOwner().setName(owName);
        d.getDogOwner().setPhoneOwner(owPhone);
        d.setAlergic(alergic);
        d.setSpecialAtention(speAtention);
        cop.editDog(d);
    }
    
    
}
