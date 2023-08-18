package com.frilsa.spotifyreminder;

import com.frilsa.spotifyreminder.forms.Login;

public class SpotifyReminderApplication {
    
    public static void main(String[] args) {
        Login formLogin = new Login();
        formLogin.setVisible(true);
        
        System.out.println("INFO: Started Application");
    }
}