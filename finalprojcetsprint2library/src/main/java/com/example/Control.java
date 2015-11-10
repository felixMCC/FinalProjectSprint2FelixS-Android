package com.example;

/**
 * Created by Felix on 11/9/15.
 */
public class Control {
    //MVC objects for paradigm
    Model theModel = new Model();
    View theView = new View();

    //main program
    public void runProgram(){
        //make sure I can reference the Model and View
        theModel.identify();
        theView.identify();

        for(int cnt = 0; cnt < 10; cnt++){
            theView.printToUser("Random Number: " + theModel.randomNumber());
        }

        //prompt user for total number of players
        promptForTotalNumberPlayers();

        //create players
        for(int cnt = 0; cnt < theModel.getNumberOfPlayers(); cnt++){
            createPlayer();
        }

    }

    //prompts user for total number of players
    private void promptForTotalNumberPlayers(){
        theView.printToUser("How many players will be playing?");
        try {
            //get the number of players from the user
            int tempTotalPlayers = Integer.parseInt(theModel.getUserInput());
            //set the total number of players in the model
            theModel.setNumberOfPlayers(tempTotalPlayers);
        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            promptForTotalNumberPlayers();
        }
    }

    //prompts user for player info and creates player objects
    private void createPlayer(){
        try{
            //temp variables
            String tempName = "";
            int tempRating = 0;
            theView.printToUser("Please enter player's name:");
            tempName = theModel.getUserInput();
            theView.printToUser("Please enter player's rating 1-5:\n(1 = novice, 5 = expert");
            tempRating = Integer.parseInt(theModel.getUserInput());
            //create player object and store within the model
            theModel.createAndStorePlayer(tempName, tempRating);
        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            createPlayer();
        }



    }

}
