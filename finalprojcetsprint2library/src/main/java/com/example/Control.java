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
        theView.printToUser("Iteration 1: Verify Model & View and random number.\n");
        theModel.identify();
        theView.identify();

        for(int cnt = 0; cnt < 3; cnt++){
            theView.printToUser("Random Number: " + theModel.randomNumber());
        }
        //main program loop
        do{

            theView.printToUser("Iteration 2: Create & print players.");
            //prompt user for total number of players
            promptForTotalNumberPlayers();

            //create players
            for(int cnt = 0; cnt < theModel.getNumberOfPlayers(); cnt++){
                createPlayer();
            }

            //print player info
            printPlayerInfo();

            theView.printToUser("Iteration 3: Create number of teams and print.");
            //prompt for number of teams
            createNumberOfTeams();

            //print teams
            theView.printTeams(theModel.getTeamsArray());
            theView.printToUser("Would you like to create brand new teams?");
        }while(theModel.checkYesNo());


    }

    //prompts user for total number of players
    private void promptForTotalNumberPlayers(){
        theView.printToUser("How many players will be playing?");
        try {
            //get the number of players from the user
            int tempTotalPlayers = Integer.parseInt(theModel.getUserInput());
            if(tempTotalPlayers > 0) {
                //set the total number of players in the model
                theModel.setNumberOfPlayers(tempTotalPlayers);
            }
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
            theView.printToUser("Please enter player's rating 1-5:\n(1 = novice, 5 = expert)");
            tempRating = Integer.parseInt(theModel.getUserInput());
            //create player object and store within the model
            theModel.createAndStorePlayer(tempName, tempRating);
        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            createPlayer();
        }


    }

    //creates teams
    private void createNumberOfTeams(){
        //prompt user for number of teams
        promptForNumberOfTeams();
        //prompt for team creation options 1 = full random 2 = fair teams
        promptTeamCreationMethod();
        //create teams
        theModel.createTeams();

    }

    //prompt user for the number of teams and validate values
    private void promptForNumberOfTeams(){
        //ask for number of players
        theView.printToUser("How many teams need to be created?");
        String tempStringTeams = theModel.getUserInput();   //store number of teams from user
        //send value to be verified
        if (theModel.validateTeams(tempStringTeams)){
            //number of teams is valid, do nothing (number of teams has been set inside the model)
        }else{
            //invalid number of teams, try again
            theView.printToUser("Invalid number of teams, please try again.");
            promptForNumberOfTeams();
        }
    }

    //prompt user for a way to create teams
    private void promptTeamCreationMethod(){
        theView.printToUser("How would you like to create teams?\n1 = Full Random, 2 = Fair Teams\n");
        String tempStringMethod = theModel.getUserInput();    //get user input
        //verity for valid integer
        if(theModel.verifyInteger(tempStringMethod) > 0){
            //store valid int in temp value to figure out choice
            int tempIntMethod = theModel.verifyInteger(tempStringMethod);
            //make sure user enters valid choice
            if(tempIntMethod == 1 || tempIntMethod == 2 ){
                //set method for creating teams 1 = full random 2 = fair teams
                theModel.setTeamCreationMethod(tempIntMethod);
            }else{
                //try again
                theView.printToUser("Invalid option, please try again.");
                promptTeamCreationMethod();
            }

        }else{
            theView.printToUser("Invalid option, please try again.");
            promptTeamCreationMethod();
        }

    }
    //print player info
    private void printPlayerInfo(){
        theView.printToUser("\nPrinting player info: \n");
        //pass the player list from the model to the view for printing
        theView.setPlayerList(theModel.getPlayerList());
        //tell the view to print the list
        theView.printPlayersInfo();
    }

}




