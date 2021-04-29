package com.util;

import org.postgresql.Driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum ConnectionUtil {
    INSTANCE;

    static Properties prop;
    static{

        try{
            Class.forName("org.postgresql.Driver");
            InputStream inputStream;
            prop = new Properties();
            String propFileName = "config.properties";

            inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static final String URL = prop.getProperty("URL");
    public static final String USERNAME = prop.getProperty("USERNAME");
    public static final String PASSWORD = prop.getProperty("PASSWORD");

    public final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
