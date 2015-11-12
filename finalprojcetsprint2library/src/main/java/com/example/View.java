package com.example;

import java.util.*;

/**
 * Created by Felix on 11/9/15.
 */
public class View {
    ArrayList viewPlayers;
    Iterator<Player> it;       //create iterator for Array List

    //setter methods

    //takes arraylist object and attaches it to the local one for use
    public void setPlayerList(ArrayList players){
        viewPlayers = players;
    }


    public void identify(){
        System.out.println("Im the View!");
    }

    //prints message to the console
    public void printToUser(String message){
        System.out.println(message);
    }

    //print player info
    public void printPlayersInfo(){
        //attach iterator object
        it = viewPlayers.iterator();
        while(it.hasNext()){

            //get player from iterator object
            Player tempPlayer = it.next();
            //print player info
            printToUser("Player name: \t" + tempPlayer.getName() + "\n");
            printToUser("Player rating: " + tempPlayer.getRating());
            printToUser("Player team: " + tempPlayer.getTeam() + "\n");

        }
    }
}
