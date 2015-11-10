package com.example;

/**
 * Created by Felix on 11/9/15.
 */
public class Player {
    //class global variables

    public String name = "";       //players name
    int rating = 0;         //value from 1-5, rating of how good the player is
    int team = 0;           //team number the player belongs to

    //Player object constructor for player
    Player(String pName, int pRating){
        //set values for player created
        name = pName;
        rating = pRating;
    }

    //Player object constructor for player with a team designated
    Player(String pName, int pRating, int pTeam){
        //set values for player created
        name = pName;
        rating = pRating;
        team = pTeam;
    }

    //getter methods

    public String getName(){
        return name;
    }
}
