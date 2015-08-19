package by.guru13.models.clinic;

import by.guru13.models.clinic.animals.Pet;

/**
 * клиент поликлиники
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Client {
    private int id;
    private String name;
    private Pet pet;
    private String sex;
    private int age;
    private Address address;

    public Client(){

    }
    public Client(String name, String sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public Client(int id, String name, Pet pet, String sex, int age, Address address) {
        this.id = id;
        this.name = name;
        this.pet = pet;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }
    public Client( String name, Pet pet, String sex, int age, Address address) {
        this.name = name;
        this.pet = pet;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }

    public Client(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * возвращает объект питомца
     *
     * @return pet
     */
    public Pet getPet() {
        return this.pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * @return идентификационное имя клиента
     */
    public int getId() {
        return this.id;
    }

    /**
     * устанавливает идентификационное имя клиента
     *
     * @param id имя клиента
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (getId() != client.getId()) return false;
        if (getAge() != client.getAge()) return false;
        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null) return false;
        if (getPet() != null ? !getPet().equals(client.getPet()) : client.getPet() != null) return false;
        if (getSex() != null ? !getSex().equals(client.getSex()) : client.getSex() != null) return false;
        return !(address != null ? !address.equals(client.address) : client.address != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPet() != null ? getPet().hashCode() : 0);
        result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nick='" + name + '\'' +
                ", pet=" + pet.getNick() +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}