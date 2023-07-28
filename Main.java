import controller.control;
import services.animalrepo;
import View.ViewAnimal;

public class Main {
    public static void main(String[] args) throws exception {
        animalrepo myFarm = new animalrepo();
        control animalController = new control(myFarm);
        ViewAnimal viewAnimal = new ViewAnimal(animalController);
        control.start();
   }
}