package com.example.stockers;

/**
 * Created by RyanMini on 3/2/17.
 */

public class Player {

    double value;
    int playerID;
    String name;
    String surname;
    String email;
    String password;

    public Player(int playerID, double value, String name, String surname, String email, String password){
        this.playerID = playerID;
        this.value = value;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Player(){
        this.value = 0;
        this.playerID = 0;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.password = null;
    }
}
