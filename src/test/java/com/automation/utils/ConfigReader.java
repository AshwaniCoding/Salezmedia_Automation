package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    public static void initConfig(){
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfigValue(String key){
        return properties.getProperty(key);
    }

    public static void setConfigValue(String key, String value){
        properties.setProperty(key, value);
    }

    public static void writeIntoProperties(String fileName, String key, String value){
        String folderPath = "src/test/resources/config/";
        try (FileInputStream input = new FileInputStream(folderPath+fileName)) {
            // Load existing properties
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.setProperty(key, value);

        try(FileOutputStream output = new FileOutputStream(folderPath + fileName)){
            properties.store(output, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
