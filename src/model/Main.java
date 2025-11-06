package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String playAgain = "y";
		// precondition: numPlayers will be 2-10 inclusive (per spec)
		System.out.print("How many players? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline
		Game game = new Game(numPlayers);
        while (playAgain.equals("y")) {
             game.play();

            // prompt to play another game
            System.out.print("\nPlay another game? <y or n> ");
            playAgain = scanner.nextLine();
        }

        scanner.close();
    }

}
