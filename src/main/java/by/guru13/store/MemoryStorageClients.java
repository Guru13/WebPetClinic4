package by.guru13.store;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.models.clinic.animals.Pet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ASUS on 15.08.2015.
 */
public class MemoryStorageClients implements Storage {

    private final AtomicInteger ids = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();


    @Override
    public Collection<Client> values() {
        return this.clients.values();
    }

    @Override
    public int add(Client client) {
        this.clients.put(client.getId(), client);
        return client.getId();
    }

    @Override
    public int add(Client client, Address address, Pet pet) {
        return 0;
    }

    @Override
    public void edit(Client client) {
        this.clients.replace(client.getId(), client);
    }

    @Override
    public void delete(int id) {
        this.clients.remove(id);
    }

    @Override
    public Client get(int id) {
        return this.clients.get(id);
    }

    @Override
    public Collection<Client> findByName(String name) {
        ArrayList<Client> clientsFounded = new ArrayList<Client>();
        for (Client client : clients.values()) {
            if (client.getName().equals(name)) {
                clientsFounded.add(client);
            }
        }
        return clientsFounded;
//        throw new IllegalStateException(String.format("Client's name %s is not found", name));
    }

    @Override
    public int generateId() {
        return this.ids.incrementAndGet();
    }

    @Override
    public void close() {

    }
}
