package com.frilsa.spotifyreminder.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLConnector {

    private static SQLConnector instance;
    private Connection connection;

    private SQLConnector() {
        String url = "jdbc:mysql://localhost/spotify_reminder?allowPublicKeyRetrieval=true&useSSL=false";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal! " + e.getMessage());
            this.closeConnection();
            System.exit(0);
        }
    }

    public static SQLConnector getInstance() {
        if (instance == null) {
            instance = new SQLConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public final void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
