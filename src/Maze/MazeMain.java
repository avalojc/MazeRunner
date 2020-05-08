package Maze;

import java.util.Scanner;

public class MazeMain {

    public static Scanner scanner0 = new Scanner(System.in);
    public static Scanner scanner1 = new Scanner(System.in);
    public static String pitStatement =  "Watch out! There's a pit ahead, jump it? y/n";
    public static String wallStatement = "Sorry you hit a wall";
    public static String answer;
    public static String otheranswer;
    public static int moveCount;
    
    public static void main(String[] args) {
        Maze maze = new Maze();
        //insert welcome and instructions here
        welcome();
        maze.printMap();
        loopingGame(maze);
        thanksForPlaying();
    }
    //loop for the game
    public static void loopingGame(Maze maze) {
        String move;
        for(move = scanner0.nextLine(); !move.equals("stop"); move = scanner0.nextLine()) {
            switch(move) {
                case "u": {
                    optionsU("U", maze);
                    break;
                }
                case "d": {
                    optionsD("D", maze);
                    break;
                }
                case "l": {
                    optionsL("L", maze);
                    break;
                }
                case "r": {
                    optionsR("R", maze);
                    break;
                }
                default:
                    System.out.println("That's not a valid move. Please type r/l/d/u.");
                    break;
            }
            moveCounter();
            maze.printMap();
            if(moveCount==100){
                break;
            }
            if(maze.didIWin()){
                youWon();
                break;
            }
        }
    }

    //options for each direction
    public static void optionsR(String direction, Maze maze) {
        if(maze.isThereAPit(direction)){
            movingPit(direction, maze);
        }else if(!maze.isThereAPit(direction)) {
            movingInfoRight(maze);
        }
    }
    public static void optionsL(String direction, Maze maze) {
        if(maze.isThereAPit(direction)){
            movingPit(direction, maze);
        }else if(!maze.isThereAPit(direction)) {
            movingInfoLeft(maze);
        }
    }
    public static void optionsU(String direction, Maze maze) {
        if(maze.isThereAPit(direction)){
            movingPit(direction, maze);
        }else if(!maze.isThereAPit(direction)) {
            movingInfoUp(maze);
        }
    }
    public static void optionsD(String direction, Maze maze) {
        if(maze.isThereAPit(direction)){
            movingPit(direction, maze);
        }else if(!maze.isThereAPit(direction)) {
            movingInfoDown(maze);
        }
    }

    //what to do if there is a pit
    public static void movingPit(String move, Maze maze) {
        if(maze.isThereAPit(move.toUpperCase())){
            pitLogic(maze, move.toUpperCase());
        }
    }

    //moving logic
    public static void movingInfoUp(Maze maze) {
        if (maze.canIMoveUp()) {
            maze.moveUp();
        } else {
            System.out.println(wallStatement);
        }
    }
    public static void movingInfoDown(Maze maze) {
        if (maze.canIMoveDown()) {
            maze.moveDown();
        }else {
            System.out.println(wallStatement);
        }
    }
    public static void movingInfoLeft(Maze maze) {
        if (maze.canIMoveLeft()) {
            maze.moveLeft();
        }else {
            System.out.println(wallStatement);
        }
    }
    public static void movingInfoRight(Maze maze) {
        if (maze.canIMoveRight()) {
            maze.moveRight();
        }else {
            System.out.println(wallStatement);
        }
    }

    //pit prompt for jump
    public static void pitLogic(Maze maze, String movementString) {
        System.out.println(pitStatement);
        otheranswer = scanner1.next().toUpperCase();
        switch (otheranswer){
            case "Y": {
                jumpPit(movementString, maze);
                break;
            }
            case "N": {
                System.out.println("You stay put.");
//                thanksForPlaying();
//                String oof = "oof";
//                return oof;
                //DEATH IS A WORK IN PROGRESS
                break;
            }
            default:{
                System.out.println("Please try valid move y/n");
                pitLogic(maze, movementString);
                break;
            }
        }
        //return movementString;
    }

    //pit jump action
    public static void jumpPit (String assignedDirection, Maze maze){
        maze.jumpOverPit(assignedDirection);
    }

    public static void moveCounter(){
        moveCount++;
        if(moveCount==50){
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        }
        if(moveCount==75){
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        }
        if(moveCount==90){
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        }
        if(moveCount==100){
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
        }
    }



    //texts to display
    public static void welcome(){
        //welcome txt
        System.out.println(
            "\n" +
            " /$$      /$$           /$$                                             /$$\n" +
            "| $$  /$ | $$          | $$                                            | $$\n" +
            "| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$ | $$\n" +
            "| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$| $$\n" +
            "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$|__/\n" +
            "| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/    \n" +
            "| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$ /$$\n" +
            "|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/|__/\n" +
            "                                                                           "
        );
    }
    public static void thanksForPlaying(){
        System.out.println(
            "\n" +
            " /$$$$$$$$ /$$                           /$$                        /$$$$$$                                    /$$                     /$$                    \n" +
            "|__  $$__/| $$                          | $$                       /$$__  $$                                  | $$                    |__/                    \n" +
            "   | $$   | $$$$$$$   /$$$$$$  /$$$$$$$ | $$   /$$  /$$$$$$$      | $$  \\__//$$$$$$   /$$$$$$         /$$$$$$ | $$  /$$$$$$  /$$   /$$ /$$ /$$$$$$$   /$$$$$$ \n" +
            "   | $$   | $$__  $$ |____  $$| $$__  $$| $$  /$$/ /$$_____/      | $$$$   /$$__  $$ /$$__  $$       /$$__  $$| $$ |____  $$| $$  | $$| $$| $$__  $$ /$$__  $$\n" +
            "   | $$   | $$  \\ $$  /$$$$$$$| $$  \\ $$| $$$$$$/ |  $$$$$$       | $$_/  | $$  \\ $$| $$  \\__/      | $$  \\ $$| $$  /$$$$$$$| $$  | $$| $$| $$  \\ $$| $$  \\ $$\n" +
            "   | $$   | $$  | $$ /$$__  $$| $$  | $$| $$_  $$  \\____  $$      | $$    | $$  | $$| $$            | $$  | $$| $$ /$$__  $$| $$  | $$| $$| $$  | $$| $$  | $$\n" +
            "   | $$   | $$  | $$|  $$$$$$$| $$  | $$| $$ \\  $$ /$$$$$$$/      | $$    |  $$$$$$/| $$            | $$$$$$$/| $$|  $$$$$$$|  $$$$$$$| $$| $$  | $$|  $$$$$$$\n" +
            "   |__/   |__/  |__/ \\_______/|__/  |__/|__/  \\__/|_______/       |__/     \\______/ |__/            | $$____/ |__/ \\_______/ \\____  $$|__/|__/  |__/ \\____  $$\n" +
            "                                                                                                    | $$                     /$$  | $$               /$$  \\ $$\n" +
            "                                                                                                    | $$                    |  $$$$$$/              |  $$$$$$/\n" +
            "                                                                                                    |__/                     \\______/                \\______/ \n"
        );
    }
    public static void youWon(){
        System.out.println(
            "\n" +
            " /$$     /$$ /$$$$$$  /$$   /$$       /$$      /$$  /$$$$$$  /$$   /$$\n" +
            "|  $$   /$$//$$__  $$| $$  | $$      | $$  /$ | $$ /$$__  $$| $$$ | $$\n" +
            " \\  $$ /$$/| $$  \\ $$| $$  | $$      | $$ /$$$| $$| $$  \\ $$| $$$$| $$\n" +
            "  \\  $$$$/ | $$  | $$| $$  | $$      | $$/$$ $$ $$| $$  | $$| $$ $$ $$\n" +
            "   \\  $$/  | $$  | $$| $$  | $$      | $$$$_  $$$$| $$  | $$| $$  $$$$\n" +
            "    | $$   | $$  | $$| $$  | $$      | $$$/ \\  $$$| $$  | $$| $$\\  $$$\n" +
            "    | $$   |  $$$$$$/|  $$$$$$/      | $$/   \\  $$|  $$$$$$/| $$ \\  $$\n" +
            "    |__/    \\______/  \\______/       |__/     \\__/ \\______/ |__/  \\__/\n"
        );
    }
}


