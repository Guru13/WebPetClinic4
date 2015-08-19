package by.guru13.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ASUS on 15.08.2015.
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    private Settings(){
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("guru13.properties").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Settings getInstance(){
        return INSTANCE;
    }
    public String value(String key){
        return this.properties.getProperty(key);
    }
}