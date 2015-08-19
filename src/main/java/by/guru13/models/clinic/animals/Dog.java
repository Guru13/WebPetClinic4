package by.guru13.models.clinic.animals;

/**
 * класс  собак
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Dog extends Pet {

    public Dog(String nick) {
        super(nick);
    }

    public Dog(String nick, String petClass, String sex, int age) {
        super(nick, petClass, sex, age);
    }
}