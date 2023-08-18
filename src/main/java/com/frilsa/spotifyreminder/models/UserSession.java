package com.frilsa.spotifyreminder.models;

public class UserSession {

    private static String id;
    private static String username;
    private static String password;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserSession.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSession.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserSession.password = password;
    }
}
