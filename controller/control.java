package controller;

import Model.*;
import services.animalrepo;
import view.ViewAnimal;

import java.util.List;

public class control {
    private static animalrepo animalRepository;
    List<Animal> animals;

    public control(animalrepo animalRepository) {

        this.animalrepo = animalRepository;
        this.animals = animalRepository.getAll();

    }


    public static void start() throws exception {

        ViewAnimal.run();
    }


    public void addcat(int id, String name, String birthday) {
        Cat cat = new cat(id, name, birthday);
        animals.add(cat);
    }

    public void adddog(int id, String name, String birthday) {
        Dog dog = new dog(name, birthday);
        animals.add(dog);
    }

    public void addhamster(int id, String name, String birthday) {
        Hamster hamster = new hamster(name, birthday);
        animals.add(hamster);
    }

    public void addhorse(int id, String name, String birthday) {
        Horse horse = new horse(name, birthday);
        animals.add(horse);
    }

    public void adddonkey(int id, String name, String birthday) {
        Donkey donkey = new donkey(name, birthday);
        animals.add(donkey);
    }

    public void addcamel(int id, String name, String birthday) {
        Camel camel = new camel(id, name, birthday);
        animals.add(camel);
    }

    public List<Animal> getAll() {
        return animals;
    }

    public Animal getAnimal(int id) {
        return searchForID(id);
    }

    public void addcommand(int id, String command) 
        Animal item = searchForID(id);
        animals.remove(item);
        item.addcommand(command);
        animals.add(item);

    }

    public Animal searchForID(int id) {
        for (int i = 0; i < animals.size(); i++) {
            if (id == animals.get(i).getId()) {
                Animal animal = animals.get(i);
                return animal;
            }
        }
        return null;
    }

    public String getcommands(int id) {
        return searchForID(id).getAnimalcommands();
    }

    public void deleteAnimal(int id) {
        animals.remove(searchForID(id));
    }
}