package by.guru13.store;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.models.clinic.animals.Dog;
import by.guru13.models.clinic.animals.Pet;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 18.08.2015.
 */
public class Main {
    public static void main(String[] args) {
//        JdbcStorageClients store = new JdbcStorageClients();
        ClientCashe clientCashe = ClientCashe.getInstance();
        List<Client> clients = (ArrayList<Client>) clientCashe.values();
        for (Client client : clients){
            System.out.println(client.getName());
        }
        Address address = new Address();
        address.setApartment(23);
        address.setCity("polor");
        address.setHouse(34);
        address.setStreet("svoya");
        Pet pet = new Dog("joy", "dog", "male", 23);
        clientCashe.add(new Client("gig", "male", 23), address, pet);
//        store.addAddress(address);
//        store.addPet(pet);
    }
}
