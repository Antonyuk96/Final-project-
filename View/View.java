package View;

import controller.control;
import Model.Animal;
import services.counter;
import services.validator;

import java.util.List;
import java.util.Scanner;

public class ViewAnimal {
    public static Animalcontrol animalController;

    public ViewAnimal(Animalcontrol animalController) {
        this.Animalcontrol = animalController;
    }

    public static void run() throws RuntimeException {
        try (Scanner in = new Scanner(System.in, "123");
             Counter counter = new Counter()) {       // счётчик

            boolean flag = true;
//            int id;
            while (flag) {
                System.out.println("Команды:\n" +
                        "1-Ввести животное \n" +
                        "2-Показать всех животных \n" +
                        "3-Показать данные животного по ID \n" +
                        "4-Обучить животное новым командам\n" +
                        "5-Список команд,которым животное обучено(ID)\n" +
                        "6-Удалить животное из списка \n" +
                        "7-Количество животных \n" +
                        "Выйти \n");

                String command = in.next();
                try {
                    switch (command) {
                        case "1":           //Завести новое животное
                            System.out.println("Выбрать  животное:\n" +
                                    "1-кот\n" +
                                    "2-собака\n" +
                                    "3-хомяк\n" +
                                    "4-лошадь\n" +
                                    "5-верблюд\n" +
                                    "6-осёл\n");


                            String animalType = in.next();
                            System.out.println("Введите имя : ");
                            String name = in.next();
                            String birthday = inputBirthday();

                            switch (animalType) {
                                case "1":
                                    animalController.addCat(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                case "2":
                                    animalController.addDog(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                case "3":
                                    animalController.addHamster(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                case "4":
                                    animalController.addHorse(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                case "5":
                                    animalController.addCamel(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                case "6":
                                    animalController.addDonkey(counter.getSum(), name, birthday);
                                    counter.add();
                                    break;
                                default:
                                    System.out.println("такого у нас не предусмотрено");
                                    break;
                            }
                            break;
                        case "2": //  все животные
                            List<Animal> animals = animalController.getAll();
                            for (int i = 0; i < animals.toArray().length; i++) {
                                System.out.println(animals.get(i).toString());
                            }
                            break;
                        case "3": //данные животного по ID
                            System.out.println("Введите id:");
                            int id = Integer.parseInt(in.next());
                            System.out.println(animalController.getAnimal(id).toString());
                            break;
                        case "4": //обучить животное новым командам
                            System.out.println("ID животного: ");
                            int ID = Integer.parseInt(in.next());
                            System.out.println("Ввести команду: ");
                            String commandForAnimal = in.next();
                            animalController.addCommand(ID, commandForAnimal);
                            System.out.println("Добавлено");
                            break;
                        case "5":  //увидеть список команд, которое выполняет животное(ID)
                            System.out.println("ID животного: ");
                            int Id = Integer.parseInt(in.next());
                            System.out.println(animalController.getCommands(Id));
                            break;
                        case "6": //удалить животное
                            System.out.println("ID животного: ");
                            id = Integer.parseInt(in.next());
                            animalController.deleteAnimal(id);
                            System.out.println("Удалено");
                            break;
                        case "7":
                            System.out.println(counter.getSum());
                            break;
                        case "8":
                            System.out.println("Выход");
                            flag = false;
                            break;
                        default:
                            System.out.println(" Варианта нет");
                            break;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static String inputBirthday() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            Validator validator = new Validator();
            while (true) {
                System.out.println("Дата рождения в формате yyyy-mm-dd");
                String date = in.next();
                if (validator.isValidDate(date)) {
                    String birthday = date;
                    return birthday;
                }
            }
        } catch (Exception e) {
            System.out.println("Введённая дата некорректна" + e.getMessage());
        }
        return null;
    }
}