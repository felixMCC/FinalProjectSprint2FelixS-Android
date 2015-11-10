package com.example;
import java.util.*;

/**
 * Created by Felix on 11/9/15.
 */
public class Model {
    //create random number generator object
    Random generator = new Random(System.currentTimeMillis());
    int randomLimit = 12;                       //range for random number from 0 to randomLimit
    Scanner input = new Scanner(System.in);     //used to get user input
    int numberOfPlayers = 0;                    //number of players for game
    ArrayList players = new ArrayList();        //holds all player objects
    Iterator it = players.iterator();           //create iterator for Array List

    //getter methods

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    //setter methods
    public void setNumberOfPlayers(int nPlayers){
        numberOfPlayers = nPlayers;
    }

    //identify that this is the view class
    public void identify(){
        System.out.println("Im the Model!");

    }

    //generate a random number
    public int randomNumber(){
        //get random number from generator
        int randomNum = generator.nextInt(randomLimit);
        return randomNum;
    }

    //gets input from user
    public String getUserInput(){
        String temp = input.nextLine();
        System.out.println("echo: " + temp);
        return temp;
    }

    //creates and stores player
    public void createAndStorePlayer(String pName, int pRating){
        //create player object
        Player player = new Player(pName, pRating);
        players.add(player);

    }

    //print player info
    public void printPlayersInfo(){
        while(it.hasNext()){
            Object tempPlayer = it.next();
            
        }
    }
}
