package OopGame;

import java.util.Scanner;

public class ColorGame {
	private static final int MIN_BET = 10;
	private static final int THINKING_PAUSE = 1500;
	
	 public void runGame() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("`*`*`*`*`* WELCOME TO COLOR GAME! *`*`*`*`*`");
	        System.out.println("Pick the color of your choice for a chance to win up to a triple jackpot!");
	        System.out.println("\nPS: In this game, you have a chance to win as well as lose,");
	        System.out.println("so make your decisions wisely. Enjoy!");
	        System.out.println("\n`*`*`*`*`* START *`*`*`*`*`");
	        System.out.print("\nEnter your name: ");
	        String playerName = sc.nextLine();
	        System.out.print("Enter your initial capital: ₱");
	        double initialCapital = sc.nextDouble();
	        sc.nextLine();
	        	if(initialCapital < MIN_BET) {
	        		System.out.println("You don't have enough money to play this game");
	        		initialCapital = 0;
	        	}
	        Player player = new Player(playerName, initialCapital);
	        Banker banker = new Banker(initialCapital);
 	        while (true) {
	            if (player.isBroke()) {
	                System.out.println("Game over!");
	                break;
	                
	            }
	            System.out.println("\nAvailable colors to bet on:");
	            for (int i = 0; i < Colors.colorOptions.length; i++) {
	                System.out.println((i + 1) + ". " + Colors.colorOptions[i]);
	            }
	            System.out.print("Select a color to bet on (1-6): ");
	            int colorChoice = sc.nextInt();
	            sc.nextLine();
	            if (colorChoice < 1 || colorChoice > Colors.colorOptions.length) {
	                System.out.println("Invalid color choice. Please select a valid color.");
	                continue;
	            }
	            System.out.print("Enter the amount you want to bet (Minimum bet is " + MIN_BET + "): ");
	            double betAmount = sc.nextDouble();
	            sc.nextLine();
	            if (betAmount < MIN_BET || betAmount > player.getCapital()) {
	                System.out.println("Invalid bet amount. Try again.");
	                continue;
	            }
	            if (betAmount > player.getCapital()) {
	                System.out.println("You don't have enough capital to place this bet.");
	            }
	            System.out.println("\n`*`*`*`*`* WINNING COLORS! *`*`*`*`*`");
	            int[] winningColors = Colors.getRandomWinningColors();
	            for (int i = 0; i < 3; i++) {
	                System.out.println();
	                System.out.println("                " + Colors.colorOptions[winningColors[i]].toUpperCase());
	                System.out.println();
	                pause(THINKING_PAUSE);
	            }
	            System.out.println("The winning colors are: " + Colors.colorOptions[winningColors[0]] + ", " +
	            		Colors.colorOptions[winningColors[1]] + ", " + Colors.colorOptions[winningColors[2]]);
	            int matchingColors = 0;
	            for (int i = 0; i < 3; i++) {
	                if (colorChoice == winningColors[i] + 1) {
	                    matchingColors++;
	                }
	            }
	            double winnings = 0;
	            if (matchingColors == 1) {
	                winnings = betAmount;
	            } else if (matchingColors == 2) {
	                winnings = betAmount * 2;
	            } else if (matchingColors == 3) {
	                winnings = betAmount * 3;
	            }
	            if (winnings > 0) {
	                player.setCapital(player.getCapital() + winnings);
	                banker.deductFromBalance(winnings);
	                System.out.println("Congratulations! You won ₱" + winnings);
	            } else {
	            	
	                player.setCapital(player.getCapital() - betAmount);
	                banker.addToBalance(betAmount);
	                System.out.println("Sorry, you lost ₱" + betAmount);
	            }
	            System.out.println("\n`*`*`*`*`* CURRENT BALANCE *`*`*`*`*`");
	            System.out.println("Your current balance: ₱" + player.getCapital());
	            System.out.print("Do you want to continue betting (Y/N)? ");
	            String continueBetting = sc.nextLine();
	            if (!continueBetting.equalsIgnoreCase("Y")) {
	                System.out.println("Thank you for playing Color Game!  You're leaving the game with " + player.getCapital() + " pesos.");
	                break;
	            }
	        }
	        sc.close();
	    }
	 private void pause(int milliseconds) {
	        try {
	            Thread.sleep(milliseconds);
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	    }
}
	
