package by.guru13.models.clinic.animals;

/**
 * абстрактный класс описывает животное
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public abstract class Pet {
    /**
     * имя животного
     */
    protected String nick;
    protected String sex;
    protected int age;
    protected String petClass;



    public Pet() {
    }

    public Pet(String nick) {
        this.nick = nick;
    }

    public Pet(String nick,String petClass, String sex, int age) {
        this.petClass = petClass;
        this.nick = nick;
        this.sex = sex;
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Устанавливаем имя питомца
     *
     * @param nick имя питомца
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPetClass() {
        return petClass;
    }

    public void setPetClass(String petClass) {
        this.petClass = petClass;
    }

    /**
     * Возращает имя питомца
     *

     * @return имя питомца
     */
    public String getNick() {
        return this.nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (getAge() != pet.getAge()) return false;
        if (getNick() != null ? !getNick().equals(pet.getNick()) : pet.getNick() != null) return false;
        if (getSex() != null ? !getSex().equals(pet.getSex()) : pet.getSex() != null) return false;
        return !(getPetClass() != null ? !getPetClass().equals(pet.getPetClass()) : pet.getPetClass() != null);

    }

    @Override
    public int hashCode() {
        int result = getNick() != null ? getNick().hashCode() : 0;
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getPetClass() != null ? getPetClass().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nick='" + nick + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", petClass='" + petClass + '\'' +
                '}';
    }
}