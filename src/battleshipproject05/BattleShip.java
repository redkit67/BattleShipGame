/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipproject05;

/**
 *
 * @author mehmetcanseyhan
 */
import java.util.*;

public class BattleShip {
    static Scanner K = new Scanner(System.in);
    static String red="\033[31m";
    static String reset="\u001B[0m";
    private static void InitBoard() { //10x10 boardgame for battleship initializer
        String board_2D[][] = new String [10][10];
        // BattleshipTitle
        System.out.println("-------- Batleship Game --------");
        int indexY = 0;
        int indexX;
        System.out.println("\n\t\tYour Board\t\t\t\t\t\tComputer's Board");
        for (String[] row : board_2D) { //red color for even rows
            if (indexY % 2 == 0) {
                System.out.printf(red+"% 2d",indexY);
                System.out.print(" |");
                for (String column : board_2D[0]) {
                    System.out.print("   |");
                }
                System.out.print("\t\t");
                System.out.printf(red+"% 2d",indexY);
                System.out.print(" |");
                indexY++;
                for (String column : board_2D[0]) {
                    System.out.print("   |");
                }
            } else {
                System.out.printf("% 2d",indexY);
                System.out.print(" |");
                for (String column : board_2D[0]) {
                    System.out.print("   |");
                }
                System.out.print("\t\t");
                System.out.printf("% 2d",indexY);
                System.out.print(" |");
                indexY++;
                for (String column : board_2D[0]) {
                    System.out.print("   |");
                }
            }
            System.out.println("\n---|–––|–––|–––|–––|–––|–––|–––|–––|–––|–––|\t\t---|–––|–––|–––|–––|–––|–––|–––|–––|–––|–––|");
        }
        System.out.print("   |");
        for (indexX = 0; indexX <10; indexX++) {
            System.out.printf("% 1d", indexX);
            System.out.print(" |");
        }
        System.out.print("\t\t");
        System.out.print("   |");
        for (indexX = 0; indexX <10; indexX++) {
            System.out.printf("% 1d", indexX);
            System.out.print(" |");
        }
        System.out.println("\n");
    }
    
    public static int IAx() {
        int posX;
        posX = (int) (Math.random()*10);
        return posX;
    }
    
    public static int IAy() {
        int posY;
        posY = (int) (Math.random()*10);
        return posY;
    }
    
    public static String[][] CompPos() {
        String CompXY [][] = new String[10][10];
        int x;
        int y;
        for (int i = 0; i < 10; i++) {
            x = IAx();
            y = IAy();
            while (CompXY[y][x] != null) {
                x = IAx();
                y = IAy();
            }
            CompXY[y][x]="@";
        }
        return CompXY;
    }
    
    public static String[][] UserPos() {
        String UserXY [][] = new String[10][10];
        int x;
        int y;
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter X Y coordinates: ");
            while (!K.hasNextInt())K.next();
                x = K.nextInt();
            while (!K.hasNextInt())K.next();
                y = K.nextInt();
            while ((x < 0 || x > 9) || (y < 0 || y > 9)) {
                System.out.println("Those coordinates are invalid, select other coordinates!");
                System.out.print("Enter X Y coordinates: ");
                while (!K.hasNextInt())K.next();
                x = K.nextInt();
                while (!K.hasNextInt())K.next();
                y = K.nextInt();
            }
            while (UserXY[y][x] != null) {
                System.out.println("Those coordinates are already in use, select other coordinates!");
                System.out.print("Enter X Y coordinates: ");
                while (!K.hasNextInt())K.next();
                x = K.nextInt();
                while (!K.hasNextInt())K.next();
                y = K.nextInt();
            }
            UserXY[y][x]="@";
        }
        System.out.println("\n");
        return UserXY;
    }
    
    public static boolean CompTurn(String[][] CompHits, String[][] UserXY_Ships) {
        boolean HIT = false;
        int x;
        int y;
        do {
            x = IAx();
            y = IAy();
        } while (CompHits[y][x] != null);
        if (UserXY_Ships[y][x] != null) {
            UserXY_Ships[y][x] = "#";
            CompHits[y][x] = "@";
            HIT = true;
        } else {
            CompHits[y][x] = "X";
        }
        return HIT;
    }
    
    public static boolean UserTurn(String[][] UserHits, String[][] CompXY_Ships) {
        boolean HIT = false;
        int x;
        int y;
        do {
            System.out.println("Enter X Y coordinates to attack:");
            while (!K.hasNextInt())K.next();
            x = K.nextInt();
            while (!K.hasNextInt())K.next();
            y = K.nextInt();
        } while ((x < 0 || x > 9) || (y < 0 || y > 9)||UserHits[y][x] != null);
        if (CompXY_Ships[y][x] != null) {
            UserHits[y][x] = "#";
            HIT = true;
        } else {
            UserHits[y][x] = "X";
        }
        return HIT;
    }
    public static void Board(String[][] UserXY_Ships, String[][] CompHits, String[][] UserHits, String[][] CompXY_Ships) {
        String board_2D[][] = new String [10][10];
        int indexY;
        int indexX;
        System.out.println("\n\t\tYour Board\t\t\t\t\t\tComputer's Board");
        for (indexY = 0; indexY < board_2D.length; indexY++) { //red color for even rows
            if (indexY % 2 == 0) {
                System.out.printf(red+"% 2d",indexY);
                System.out.print(" |");
                for (indexX = 0; indexX < board_2D[0].length; indexX++) {
                    if (CompHits[indexY][indexX] != null && UserXY_Ships[indexY][indexX] != null) {
                        System.out.print(" # |");
                    } else if (UserXY_Ships[indexY][indexX] != null) {
                        System.out.print(" @ |");
                    } else if (CompHits[indexY][indexX] != null) {
                        System.out.print(" X |");
                    } else {
                        System.out.print("   |");
                    }
                }
                System.out.print("\t\t");
                System.out.printf(red+"% 2d",indexY);
                System.out.print(" |");
                for (indexX = 0; indexX < board_2D[0].length; indexX++) {
                    if (UserHits[indexY][indexX] != null && CompXY_Ships[indexY][indexX] != null) {
                        System.out.print(" # |");
                    } else if (UserHits[indexY][indexX] != null) {
                        System.out.print(" X |");
                    } else {
                        System.out.print("   |");
                    }
                }
            } else {
                System.out.printf("% 2d",indexY);
                System.out.print(" |");
                for (indexX = 0; indexX < board_2D[0].length; indexX++) {
                    if (CompHits[indexY][indexX] != null && UserXY_Ships[indexY][indexX] != null) {
                        System.out.print(" # |");
                    } else if (UserXY_Ships[indexY][indexX] != null) {
                        System.out.print(" @ |");
                    } else if (CompHits[indexY][indexX] != null) {
                        System.out.print(" X |");
                    } else {
                        System.out.print("   |");
                    }
                }
                System.out.print("\t\t");
                System.out.printf("% 2d",indexY);
                System.out.print(" |");
                for (indexX = 0; indexX < board_2D[0].length; indexX++) {
                    if (UserHits[indexY][indexX] != null && CompXY_Ships[indexY][indexX] != null) {
                        System.out.print(" # |");
                    } else if (UserHits[indexY][indexX] != null) {
                        System.out.print(" X |");
                    } else {
                        System.out.print("   |");
                    }
                }
            }
            System.out.println("\n---|–––|–––|–––|–––|–––|–––|–––|–––|–––|–––|\t\t---|–––|–––|–––|–––|–––|–––|–––|–––|–––|–––|");
        }
        System.out.print("   |");
        for (indexX = 0; indexX <10; indexX++) {
            System.out.printf("% 1d", indexX);
            System.out.print(" |");
        }
        System.out.print("\t\t");
        System.out.print("   |");
        for (indexX = 0; indexX <10; indexX++) {
            System.out.printf("% 1d", indexX);
            System.out.print(" |");
        }
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        InitBoard();
        game();
    }

    public static void game() {
        int Turn = 1;
        int CompCounter = 0;
        int UserCounter = 0;
        boolean GameResult = false;
        String[][] CompXY_Ships = CompPos();
        String[][] CompHits = new String [10][10];
        String[][] UserXY_Ships = UserPos();
        String[][] UserHits = new String [10][10];
        while (CompCounter < 10 && UserCounter < 10) {
            Board(UserXY_Ships, CompHits, UserHits, CompXY_Ships);
            if (UserTurn(UserHits, CompXY_Ships)) UserCounter++;
            if (CompTurn(CompHits, UserXY_Ships)) CompCounter++;
            System.out.println("Turn: "+Turn+"\nRival Hits: "+CompCounter + "\nUser Hits: " + UserCounter);
            Turn++;
        }
        Board(UserXY_Ships, CompHits, UserHits, CompXY_Ships);
        if (UserCounter == 10) GameResult = true;
        if (CompCounter == 10) GameResult = false;
        if (GameResult) System.out.println("USER WINS!");
        else System.out.println("COMPUTER WINS!");
        System.exit(0);
    }
}
