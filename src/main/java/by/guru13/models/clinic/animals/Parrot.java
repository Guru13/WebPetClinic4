package by.guru13.models.clinic.animals;

/**
 * класс котов
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Parrot extends Pet {

    public Parrot(String nick) {
        super(nick);
    }

    public Parrot(String nick, String petClass, String sex, int age) {
        super(nick, petClass, sex, age);
    }
}