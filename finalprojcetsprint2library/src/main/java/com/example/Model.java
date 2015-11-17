package com.example;
import java.util.*;

/**
 * Created by Felix on 11/9/15.
 */
public class Model {
    //create and seed random number generator object
    Random generator = new Random(System.currentTimeMillis());
    int randomLimit = 12;                           //range for random number from 0 to randomLimit
    Scanner input = new Scanner(System.in);         //used to get user input
    private int numberOfPlayers = 0;                //number of players for game
    private int numberOfTeams = 0;
    private int teamCreationMethod = 0;             //1 = Full Random, 2 = Fair Teams
    private int playersInEachTeamEvenly = 0;        //How many players per team will fit in evenly
    ArrayList players = new ArrayList();            //holds all player objects
    private Team[] teamsArray;                      //holds all team objects
    Iterator<Player> itPlayers;                            //create iterator for Array List


    //getter methods

    public Team[] getTeamsArray(){
        return teamsArray;
    }

    public int getTeamCreationMethod(){return teamCreationMethod;}

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public ArrayList getPlayerList(){ return players; }

    public int getNumberOfTeams(){ return numberOfTeams;}

    public int getRandomLimit(){ return randomLimit;}

    //setter methods

    public void setTeamCreationMethod(int choice){
        teamCreationMethod = choice;
    }

    public void setNumberOfPlayers(int nPlayers){
        numberOfPlayers = nPlayers;
    }

    public void setNumberOfTeams(int nTeams){
        numberOfTeams = nTeams;
    }

    public void setRandomLimit(int temp){
        //limit is exclusive for upper value, so adding one to it
        randomLimit = temp+1;
    }


    //identify that this is the view class
    public void identify(){
        System.out.println("Im the Model!");

    }

    //generate a random number
    public int randomNumber(){
        System.out.println("Inside randomNumber()\nRandom Limit: " + getRandomLimit());
        //get random number from generator (from 0 - randomLimit)
        int randomNum = generator.nextInt(randomLimit);
        if(randomNum == 0){
            //if random number is zero, return 1
            return 1;
        }else{
            //else return random number generated
            return randomNum;
        }

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

    //verifies integer input
    public int verifyInteger(String value){
        try{
            int temp = Integer.parseInt(value);
            return temp;
        }catch(NumberFormatException e){
            return -1;
        }
    }

    //validate team values and set number of teams
    public boolean validateTeams(String tempTeamValue){
        //if input from user is valid integer then set up teams
        if(verifyInteger(tempTeamValue) > 0){
            //if number of teams is valid int and greater than 0, parse string to int
            int tempIntTeams = verifyInteger(tempTeamValue);
            //if the number of teams is larger than the number of players
            if(tempIntTeams > getNumberOfPlayers()){
                //invalid number of teams, try again
                return false;
            }else{
                setNumberOfTeams(tempIntTeams);        //store number of teams in model
                return true;
            }

        }else{
            //value entered by user was invalid
            return false;
        }
    }

    //separates players into teams given by user
    public void createTeams(){
        System.out.println("Number of Players: " + numberOfPlayers + " divided by Number of teams: " + numberOfTeams + "\nResult:" + (numberOfPlayers / numberOfTeams));

        //set random number max value (so that it picks a team from 1 to the number input by the user)
        setRandomLimit(getNumberOfTeams());

        switch (getTeamCreationMethod()){
            case 1:
                //full random
                createFullRandomTeams();
                break;
            case 2:
                //fair teams
                break;
            default:
                //full random
                break;
        }
    }

    //iterates through the players and randomly places them on a team
    public void createFullRandomTeams(){
        //figure out how many players will fit into each team evenly
        playersInEachTeamEvenly = getNumberOfPlayers() / getNumberOfTeams();

        //create team objects and arrays
        createTeamObjects();

        //attach iterator to players Array List
        attachIterator();

        //place players into random teams
        for(int cnt = 0; cnt < getNumberOfPlayers(); cnt++){
            randomlyPlacePlayerInTeam();
        }


    }

    //creates team objects and arrays
    private void createTeamObjects(){
        //create array to hold team objects (+1 to help with numbering schema)
        teamsArray = new Team[getNumberOfTeams()+1];
        //figure out size of the array holding players
        int teamArraySize = getNumberOfPlayers() / 2;   //A team will have at most 1/2 the players

        for(int cnt = 0; cnt < getNumberOfTeams(); cnt++){
            //starting from position 1 in the array, set array objects
            teamsArray[cnt+1] = new Team(cnt +1, teamArraySize, playersInEachTeamEvenly);
        }

        //testing, print teams created
        System.out.println("Teams requested by user: " + getNumberOfTeams() + "\nTeams created (teamsArray Size): " + teamsArray.length);
        for(int cnt2 = 1; cnt2 < teamsArray.length; cnt2++){
            System.out.println("Team Number: " + teamsArray[cnt2].getTeamNumber() + "teamsArray Position: " + cnt2);
        }
    }

    //randomly laces players into team objects
    private void randomlyPlacePlayerInTeam() {
        System.out.println("\nInside randomlyPlacePlayerInTeam()");
        //shuffle player Array List
        Collections.shuffle(players);

        //get a random team number
        int tempTeamChoice = randomNumber();
        int tempCurrentNumberOfPlayers = teamsArray[tempTeamChoice].getCurrentNumberOfPlayers();
        int tempFitPlayersEvenly = teamsArray[tempTeamChoice].getFitPlayersEvenly();
        System.out.println("Comparisson for placing team will be: ");
        System.out.println("Team Choice " + tempTeamChoice + "\n Current number of players: " + tempCurrentNumberOfPlayers + " Number of Players that fit evenly: " + tempFitPlayersEvenly);
        //check to see if the number of players that fit evenly in this team has been reached
        if( tempCurrentNumberOfPlayers != tempFitPlayersEvenly){
            //if team still has room

            Player tempPlayer = itPlayers.next();
            //set player team number
            tempPlayer.setTeam(tempTeamChoice);
            //get player and place into team number
            teamsArray[tempTeamChoice].addPlayerToTeam(tempPlayer);
            System.out.println("Team number: " + tempTeamChoice + " Player: " + tempPlayer.getName());
            //remove player
            itPlayers.remove();
            System.out.println("removed player");
            //increase count of players in team
            teamsArray[tempTeamChoice].incrementNumberOfPlayers();
            System.out.println("Team now has " + teamsArray[tempTeamChoice].getCurrentNumberOfPlayers() + " players. Max players Evenly: " + teamsArray[tempTeamChoice].getFitPlayersEvenly());
        }else{
            System.out.println("Need to find team that has an opening.");
            Player tempPlayer2 = itPlayers.next();
            //else select a different team that has not reached the max number of players that fit evenly
            teamsArray[getTeamNumberNotFullYet()].addPlayerToTeam(tempPlayer2);
            System.out.println("Team number: " + tempTeamChoice + " Player: " + tempPlayer2.getName());
            //remove player
            itPlayers.remove();
            System.out.println("removed player");
            //increase count of players in team
            teamsArray[tempTeamChoice].incrementNumberOfPlayers();
            System.out.println("Team now has " + teamsArray[tempTeamChoice].getCurrentNumberOfPlayers() + " Max players Evenly: " + teamsArray[tempTeamChoice].getFitPlayersEvenly());
        }


    }

    //returns a team number iteratively to fit a new player into
    private int getTeamNumberNotFullYet(){
        //iterate through teams
        for(int cnt=1; cnt<= getNumberOfTeams(); cnt++){
            //find a team that hasnt reached its max number of players that can fit evenly
            if(teamsArray[cnt].getCurrentNumberOfPlayers() != teamsArray[cnt].getFitPlayersEvenly()){
                return cnt;
            }
        }
        return 0;
    }

    private void attachIterator(){
        itPlayers = players.iterator();
    }
}











