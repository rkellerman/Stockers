package com.example.stockers;

import java.io.Serializable;

/**
 * Created by RyanMini on 3/2/17.
 */

public class Player implements Serializable{

    double value;
    int playerID;
    String name;
    String surname;
    String email;
    String password;

    /**
     * Overloaded Constructor that initializes Player.
     * @param playerID
     * Unique integer to define user
     * @param value
     * Net worth of user
     * @param name
     * First name of user
     * @param surname
     * Last name of user
     * @param email
     * Email of user
     * @param password
     * Password of user
     */
    public Player(int playerID, double value, String name, String surname, String email, String password){

        this.playerID = playerID;
        this.value = value;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor that initializes Player with default values.
     */
    public Player(){
        this.value = 0;
        this.playerID = 0;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.password = null;
    }
}
