package com.mycompany.doggroomingproyect.persistence;

import com.mycompany.doggroomingproyect.logic.Dog;
import com.mycompany.doggroomingproyect.logic.Owner;
import com.mycompany.doggroomingproyect.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlOfPersistence {
    OwnerJpaController owJpa = new OwnerJpaController();
    DogJpaController dogJpa = new DogJpaController();

    public void save(Owner ow, Dog dog) {
        //creating the owner
        owJpa.create(ow);

        //creating the dog
        dogJpa.create(dog);
    }

    public List<Dog> getDogs() {
        return dogJpa.findDogEntities();
    }

    public void deleteDog(int customerId) {
        try {
            dogJpa.destroy(customerId);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlOfPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Dog getDog(int customerId) {
        return dogJpa.findDog(customerId);
    }

    public void editDog(Dog d) {
        try {
            dogJpa.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(ControlOfPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            owJpa.edit(d.getDogOwner());
        } catch (Exception ex) {
            Logger.getLogger(ControlOfPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
