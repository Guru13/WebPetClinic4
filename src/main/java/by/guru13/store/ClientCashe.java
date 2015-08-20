package by.guru13.store;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.models.clinic.animals.Pet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by ASUS on 15.08.2015.
 */
public class ClientCashe implements Storage {
    private final static  ClientCashe INSTANCE = new ClientCashe();
    private  Storage storage = new JdbcStorageClients();

    private ClientCashe() {

    }

    public static synchronized ClientCashe getInstance(){

        return INSTANCE;
    }

    @Override
    public Collection<Client> values() {
      return storage.values();
    }

    @Override
    public int add(Client client) {

        return this.storage.add(client);
    }

    @Override
    public int add(Client client, Address address, Pet pet) {
        return this.storage.add(client, address, pet);
    }

    @Override
    public void edit(Client client) {
        this.storage.edit(client);
    }

    @Override
    public void delete(int id) {
        this.storage.delete(id);
    }

    @Override
    public Client get(int id) {
        return this.storage.get(id);
    }

    @Override
    public Collection<Client> findByName(String name) {
        return this.storage.findByName(name);
    }

    @Override
    public int generateId() {
        return this.storage.generateId();
    }

    @Override
    public void close() {
        storage.close();
    }
}
