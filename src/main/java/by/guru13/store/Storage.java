package by.guru13.store;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.models.clinic.animals.Pet;

import java.util.Collection;

/**
 * Created by ASUS on 15.08.2015.
 */
public interface Storage {


    public Collection<Client> values();

    public int add(final Client client);
    public int add(final Client client, Address address, Pet pet);

    public void edit(final Client client);

    public void delete(final int id);

    public Client get(final int id);

    public Collection<Client> findByName(final String name);

    public int generateId();

    public void close();
}
