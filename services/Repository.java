package services;

import Model.Animal;

import java.util.List;

public interface Repository <T>{

    List<Animal> getAll();
    T getById(int id);

    void add(Animal animal);

    void delete (Animal animal);
}