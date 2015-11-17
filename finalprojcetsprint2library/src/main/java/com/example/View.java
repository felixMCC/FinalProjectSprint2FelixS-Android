package com.example;

import java.util.*;

/**
 * Created by Felix on 11/9/15.
 */
public class View {
    ArrayList viewPlayers;
    Iterator<Player> itPlayer;       //create iterator for Array List

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
        itPlayer = viewPlayers.iterator();
        while(itPlayer.hasNext()){

            //get player from iterator object
            Player tempPlayer = itPlayer.next();
            //print player info
            printToUser("Player name: \t" + tempPlayer.getName() + "\n");
            printToUser("Player rating: " + tempPlayer.getRating());
            printToUser("Player team: " + tempPlayer.getTeam() + "\n");

        }
    }

    //print teams
    public void printTeams(Team[] tArray){
        printToUser("\nPrinting Teams:");
        //go through team list
        for(int cnt = 1; cnt < tArray.length; cnt++ ){
            printToUser("\nTEAM " + cnt + ": ");
            //send player array to be printed
            iterateThroughPlayerArray(tArray[cnt].getPlayerArray());
        }

    }

    //iterates through an array of Players and pritns out info
    private void iterateThroughPlayerArray(Player[] pArray){
        for(int cnt = 0; cnt < pArray.length; cnt++){
            //print player info
            printToUser("Player name: \t" + pArray[cnt].getName() + "\n");
            printToUser("Player rating: " + pArray[cnt].getRating());
            printToUser("Player team: " + pArray[cnt].getTeam() + "\n");
        }
    }
}
