package by.guru13.models.clinic.animals;

/**
 * класс котов
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Cat extends Pet {
    public Cat(String nick) {
        super(nick);
    }

    public Cat(String nick, String petClass, String sex, int age) {
        super(nick, petClass, sex, age);
    }
}