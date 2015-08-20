package by.guru13.store;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.models.clinic.animals.Cat;
import by.guru13.models.clinic.animals.Dog;
import by.guru13.models.clinic.animals.Parrot;
import by.guru13.models.clinic.animals.Pet;
import by.guru13.service.Settings;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ASUS on 15.08.2015.
 */
public class JdbcStorageClients implements Storage {

public Connection connection;



    public JdbcStorageClients() {
//        final Settings settings = Settings.getInstance();
        try {
//            connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
//            this.connection = connection;
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientsdb", "root", "root");
            if (!connection.isClosed()){
                System.out.println("successfull connection");
            }
        } catch (SQLException e) {
            System.out.println("could not connection");
//            throw new IllegalStateException();
        }
    }


    @Override
    public Collection<Client> values() {
        final List<Client> clients = new ArrayList<Client>();
        Statement statement;
        ResultSet rs;
        try {
            statement = this.connection.createStatement();
            rs = statement.executeQuery("SELECT c.uid, c.name, a.city, c.age,\n" +
                     "a.street, a.house, a.apartment,\n" +
                     "p.nick, p.class, p.sex, p.age \n" +
                     "FROM client c\n" +
                     "INNER JOIN address a ON c.address_id = a.uid\n" +
                     "INNER JOIN pet p ON c.pet_id = p.uid;");
            rs.first();
            while (rs.next()) {
                Address address = new Address(rs.getString("city"), rs.getString("street"), rs.getInt("house"), rs.getInt("apartment"));
                Pet pet = null;

                if (rs.getString("class").equals("dog")) {
                    pet = new Dog(rs.getString("nick"), rs.getString("class"), rs.getString("sex"), rs.getInt("age"));
                } else if (rs.getString("class").equals("cat")) {
                    pet = new Cat(rs.getString("nick"), rs.getString("class"), rs.getString("sex"), rs.getInt("age"));
                } else if (rs.getString("class").equals("nick")) {
                    pet = new Parrot(rs.getString("nick"), rs.getString("class"), rs.getString("sex"), rs.getInt("age"));
                }
                if (pet != null) {
                    clients.add(new Client(rs.getInt("uid"), rs.getString("name"), pet, rs.getString("sex"), rs.getInt("age"), address));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public int add(Client client) {
        return 0;
    }

    @Override
    public int add(Client client, Address address, Pet pet) {

        PreparedStatement statement;
        try {
          statement =   this.connection.prepareStatement("INSERT INTO client (uid, name, age, sex, pet_id, address_id)  VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, client.getId());
            statement.setString(2,client.getName());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getSex());
            statement.setInt(5, addPet(pet));
            statement.setInt(6, addAddress(address));
            statement.executeUpdate();
            ResultSet generatedKey = null;
            try {
                 generatedKey = statement.getGeneratedKeys();
                if (generatedKey.next()){
                    return generatedKey.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                generatedKey.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client.getId();
    }


    public int addAddress(Address address){
        PreparedStatement statement;
        try {
            statement = this.connection.prepareStatement("INSERT INTO address (city, street, house, apartment)  VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getHouse());
            statement.setInt(4, address.getApartment());
            statement.executeUpdate();
            ResultSet generatedKey ;
            try {
                generatedKey = statement.getGeneratedKeys();
                if (generatedKey.next()){
                    return generatedKey.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new address");
    }
    public int addPet(Pet pet){

        PreparedStatement statement;
        try {
            statement = this.connection.prepareStatement("INSERT INTO pet (nick, class, sex, age)  VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, pet.getNick());
            statement.setString(2,pet.getPetClass());
            statement.setString(3, pet.getSex());
            statement.setInt(4, pet.getAge());
            statement.executeUpdate();
            ResultSet generatedKey = null;
            try {
                generatedKey = statement.getGeneratedKeys();
                if (generatedKey.next()){
                    return generatedKey.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("could not create new pet");
    }

    @Override
    public void edit(Client client) {

    }

    @Override
    public void delete(int id) {
        PreparedStatement statement;
        Statement statement1;
        try {
            statement1 = this.connection.createStatement();
            ResultSet rs = statement1.executeQuery("SELECT * FROM client WHERE uid =" + id);
            int uidPet;
            int uidAddress;
            while (rs.next()){
                uidPet = rs.getInt("pet_id");
                uidAddress = rs.getInt("address_id");
                statement = this.connection.prepareStatement("DELETE FROM client WHERE uid = (?)");
                statement.setInt(1, id);
                statement.executeUpdate();

                statement = this.connection.prepareStatement("DELETE FROM pet WHERE uid = (?)");
                statement.setInt(1, uidPet);
                statement.executeUpdate();
                statement = this.connection.prepareStatement("DELETE FROM address WHERE uid = (?)");
                statement.setInt(1, uidAddress);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client get(int id) {
        Client clientN = null;
        for (Client client : values()){
            if (client.getId() == id){
                clientN = client;
            }
        }
        return clientN;
    }

    @Override
    public ArrayList<Client> findByName(String name) {
       ArrayList<Client> clients = new ArrayList<Client>();

        for (Client client : values()){
            if (client.getName().equals(name)){
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
