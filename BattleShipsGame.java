import java.util.Random;

import java.util.Scanner; // you must import Scanner to use it

public class BattleShipsGame {

    private Scanner input = new Scanner(System.in); //This line creates a Scanner for you to use

    private int board[][] = new int[10][10];

    private int computerGuesses[][] = new int[10][10];

    private String lastMessage = "";

    private String computerLastMessage = "";

    private Random rand = new Random();

    public void checkEndGame(){

        int yourShips = countYourShips();

        int computerShips = countComputerShips();

        if(yourShips == 0 || computerShips == 0){
            clearConsole();
            String whoWon = yourShips == 0 ? "COMPUTER WINS." : "YOU WIN!";
            System.out.println(whoWon);
            System.exit(1);
        }
    }




    public void computersTurn(){

        int  x;
        int  y;

        while(true){
           x = rand.nextInt(10);
           y = rand.nextInt(10);

           // don't guess the same spot
           if(computerGuesses[x][y] == 0){
            break;
           }
        }

        computerGuesses[x][y] = 1;

        switch (board[x][y]) {
            case 1:
                computerLastMessage = "The Computer sunk one of your ships!";
                board[x][y] = 5;
                break;
            case 2:
                computerLastMessage = "The Computer sunk one of its own ships";
                board[x][y] = 3;
                break;
            default:
                computerLastMessage = "Computer missed";
                break;
        }

        checkEndGame();
       
    }



    public void clearConsole() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public String getComputerLastMessage(){
        return computerLastMessage;
    }

    public String getLastMessage() {  
        return lastMessage; 
    }  


    public void yourTurn(){

        System.out.println("YOUR TURN");
        System.out.println("Enter X coordinate: ");
        int x = input.nextInt();
        System.out.println("Enter Y coordinate: ");
        int y = input.nextInt();

        if(x >= board.length || x < 0 || y < 0 || y >= board.length){
            lastMessage = "Invalid coordinates: 10 > x > -1 and 10 > y > -1";
            return;
        }

        if(board[x][y] == 3 || board[x][y] == 4 || board[x][y] == 5 ){
            lastMessage = "You missed";
            return;
        }

        if(board[x][y] == 1){
            lastMessage = "Oh no, you sunk your own ship :(";
            board[x][y] = 5;
        } 

        if(board[x][y] == 2){
            lastMessage = "Boom! You sunk the ship!";
            board[x][y] = 3;
        } 

                // hit
        if(board[x][y] == 0 ){
            lastMessage = "You missed";
            board[x][y] = 4;
        }

        checkEndGame();
    }



    public void generateComputerShips(){

        System.out.println( "Computer is deploying ships");

        int computerShips = 0;

        while(computerShips < 5){

            int  x = rand.nextInt(10);
            int  y = rand.nextInt(10);

            if(board[x][y] != 0){
                continue;
            }

            System.out.println( (computerShips + 1) + ". ship DEPLOYED");

            board[x][y] = 2;
            computerShips++;
        }

        System.out.println("------------------------------");
    }


    private int countOccurrences(int num){

        int count = 0;

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == num){
                    count++;
                }
            }
        }

        return count;
    }


    public int countYourShips(){
        return countOccurrences(1);
    }

    public int countComputerShips(){
        return countOccurrences(2);
    }




    public void printBoard(){

        System.out.println("   0123456789  ");

        for(int x=0; x<board.length; x++){
            System.out.print(x + " |");
            for(int y=0; y<board[0].length; y++){

                int value = board[x][y];
                String output = " ";

                if(value == 1){
                    output = "@";
                } 

                // computer ship
                if(value == 2){
                    //output = "$";
                    output = " ";
                } 

                // hit computer ship
                if(value == 3){
                    output = "!";
                }

                // miss
                if(value == 4){
                    output = "-";
                }

                // player ship destroyed 
                if(value == 5){
                    output = "x";
                }

                System.out.print("" + output);
            }
            System.out.println("| " + x);
        }
        System.out.println("   0123456789  ");

        System.out.println("\nYour ships: " + countYourShips() + " | Computer Ships: " + countComputerShips());
        System.out.println("---------------------------------");
    }


    public void generatePlayerShips(){

        int playerShipsCount = 1;

        System.out.println("Deploy your ships: ");

        while(playerShipsCount <= 5){


            System.out.print("Enter X coordinate for your ship " +playerShipsCount +": ");
            int x = input.nextInt();
        
            System.out.print("Enter Y coordinate for your ship " + playerShipsCount+ ": ");
            int y = input.nextInt();

            if(x < 0 || x >= board.length || y<0 || y >= board.length || board[x][y] != 0){
                System.out.println("Location is invalid. Please try again.");
                continue;
            }

            board[x][y] = 1;
            playerShipsCount++;

        }


    }
    


    public static void main(String[] args) {
        // ...
        /* 
        Here is some sample code where you ask the user to enter in the coordinates for where to place a ship
        */

        BattleShipsGame bsg = new BattleShipsGame();

        bsg.generatePlayerShips();

        bsg.generateComputerShips();

        while(true){

            bsg.clearConsole();
            System.out.println( "BATTLESHIP: THE TACTICAL COMBAT GAME\n");

            bsg.computersTurn();

            String lastMessage = bsg.getLastMessage();
            String computerLastMessage = bsg.getComputerLastMessage();

            System.out.println(lastMessage);
            System.out.println("");
            System.out.println( "COMPUTER'S TURN");
            System.out.println(computerLastMessage);
            System.out.println("");
            bsg.printBoard();
            System.out.println("");

            bsg.yourTurn();

        }

    }



}







