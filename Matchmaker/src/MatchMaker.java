import java.util.Scanner;
import java.util.Random;

public class MatchMaker {

    /** gets the number of players and returns it
     * 
     * @return numPlayers — the number of players
     */
    public static int getNumPlayers(Scanner input) {

        boolean quit = false;
        int playerNum = 0;

        while (!quit) {
            System.out.print("How many players are there? (At least 3): ");
            if (input.hasNextInt()) { // check if integer entered
                playerNum = input.nextInt();
                if (playerNum >= 3 && playerNum <= 10) { // check if integer at least 3
                    quit = true;
                } else {
                    System.out.println("Expected value between 3 and 10.");
                    input.nextLine();
                }
            } else {
                System.out.println("Expected value between 3 and 10.");
                input.nextLine();
            }
        }
        return playerNum;
    }

    public static String[] getPlayerNames(Scanner input, String[] playerNames) {
        String userInput = input.nextLine();
        for (int i = 0; i < playerNames.length; i++) {

            System.out.print("Name of Player " + (i + 1) + ":");
            userInput = input.nextLine();
            playerNames[i] = userInput;

        }

        return playerNames;
    }

    public static String[] getPlayerQuestions(Scanner input, String[] playerNames) {
        String userInput = input.nextLine();
        String[] playerQuestions = new String[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {

            System.out.print(playerNames[i] + ", enter a question for the other players:");
            playerQuestions[i] = input.nextLine();

            for (int j = 0; j < 50; j++) {
                System.out.println();
            }

        }
        return playerQuestions;

    }

    public static String[][] getPlayerAnswers(Scanner input, int numPlayers, String[] playerNames,
        String[] playerQuestions) {
        String[][] playerAnswers = new String[numPlayers][numPlayers];
        // String userInput = input.nextLine();

        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < numPlayers; j++) {
                if (j == i) {
                    continue;
                }
                System.out.println(playerNames[j] + ", enter an Answer to the next question:");
                System.out.println(playerQuestions[i]);
                playerAnswers[i][j] = input.nextLine();
                for (int h = 0; h < 50; h++) {
                    System.out.println();
                }
            }

        }
        return playerAnswers;
    }


    /*
     * TODO: finish question ranking system
     * 
     * 
     */
    public static String[] getPlayerQuestionrank(Scanner input, int numPlayers,
        String[] playerNames, String[] playerQuestions, String[][] playerAnswers) {
        String[] playerQuestionRank = new String[numPlayers];
        int g = 1;
        
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Okay, " + playerNames[i]
                + "—go ahead and pick your favorite answer!\n(type the number of the one you like the most):");
            for (int j = 0; j < numPlayers; j++) {
                if (playerAnswers[i][j] != null) {
                    
                    System.out.print(g + ")"); 
                    System.out.println(playerAnswers[i][j]);
                    g++;
                }
                
            } 
            g = 1;
            playerQuestionRank[i] = input.nextLine();
            for (int h = 0; h < 50; h++) {
                System.out.println();
            }
        }
        return playerQuestionRank;
    }

    public static String[] RandomizeArray(String[] arr) {
        String[] randomizedArray = new String[arr.length];
        System.arraycopy(arr, 0, randomizedArray, 0, arr.length);
        Random rgen = new Random();

        for (int i = 0; i < randomizedArray.length; i++) {
            int randPos = rgen.nextInt(randomizedArray.length);
            String tmp = randomizedArray[i];
            randomizedArray[i] = randomizedArray[randPos];
            randomizedArray[randPos] = tmp;
        }

        return randomizedArray;
    }

    public static void getPairedPlayersEven(Scanner input, String[] randomPlayerNames, String[] partnerActions) {
       Random randGen = new Random();
        for (int i = 0; i < randomPlayerNames.length; i += 2) {
            System.out.println("Hey " + randomPlayerNames[i] + " and " + randomPlayerNames[i+1] + ", here's what you have to do:");
            input.nextLine();
            System.out.println(partnerActions[randGen.nextInt(partnerActions.length)]);
            System.out.println();
        }
        return;
    }
    
    public static void getPairedPlayersOdd(Scanner input, String[] randomPlayerNames, String[] partnerActions, String[] aloneActions) {
        Random randGen = new Random();
        String oddPlayerOut = randomPlayerNames[randomPlayerNames.length - 1];
         for (int i = 0; i < randomPlayerNames.length - 2; i += 2) {
             
             System.out.println("Hey " + randomPlayerNames[i] + " and " + randomPlayerNames[i+1] + ", here's what you have to do:");
             input.nextLine();
             System.out.println(partnerActions[randGen.nextInt(partnerActions.length)]);
             System.out.println();
             }
         System.out.println("Oh, and of course we can't forget about " + oddPlayerOut + "...");
         input.nextLine();
         System.out.println("Let's have " + oddPlayerOut + " " + aloneActions[randGen.nextInt(aloneActions.length)]);
             return;
         }
         
     
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numPlayers = 0;

        numPlayers = getNumPlayers(input);

        String[] playerNames = new String[numPlayers]; // change int value to numPlayers

        playerNames = getPlayerNames(input, playerNames); // player names in an array

        System.out.print("Ready to play Match Maker?\nLet's Start! (Press Enter)");

        // System.out.print(java.util.Arrays.toString(playerNames)); //test if array is correct

        String[] playerQuestions = new String[numPlayers];

        playerQuestions = getPlayerQuestions(input, playerNames);

        // System.out.print(java.util.Arrays.toString(playerQuestions)); //test if questions are
        // correct

        String[][] playerAnswers = new String[numPlayers][numPlayers];

        playerAnswers = getPlayerAnswers(input, numPlayers, playerNames, playerQuestions);

        // System.out.println(java.util.Arrays.toString(playerAnswers)); //test if questions are
        // correct

        // check compatibility

        String[] playerQuestionRank = new String[numPlayers];

        playerQuestionRank =
            getPlayerQuestionrank(input, numPlayers, playerNames, playerQuestions, playerAnswers);
        
        System.out.println("Okay... generating the best matches... (Press Enter)");
        input.nextLine();
        
        System.out.println("Ooh! These are some interesting answers! (Press Enter)");
        input.nextLine();
        
        
        
    //pairing players
        
        String[] randomPlayerNames = new String[playerNames.length];
        
        randomPlayerNames = RandomizeArray(playerNames);
        
        // System.out.println(java.util.Arrays.toString(randomPlayerNames)); Check if array is random
        
        String[] partnerActions = new String[] {"Hold hands for the next round!", 
            "Stare into your partner's eyes for the next 30 seconds!", "Hug it out for 15 seconds!", 
            "Whisper sweet nothings into your partner's ear!", "Give your partner a peck on the cheek!", 
            "Massage each other's backs!", "Give 2 Compliments to your partner!"
        };
        
        String[] aloneActions = new String[] {"sit facing a corner for the next round!", "attempt to do a cartwheel!", 
            "steal whoever's partner they choose and do that action with them!", 
            "join a partnership and do what they're doing, but make it extremely awkward."};
            
        
        
        if (numPlayers % 2 == 0) {
            getPairedPlayersEven(input, randomPlayerNames, partnerActions);
        }
        
        else {
            getPairedPlayersOdd(input, randomPlayerNames, partnerActions, aloneActions);
        }
      
        System.out.println();
        System.out.print("Thanks for playing!");
    }
}
