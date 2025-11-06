package model;

import java.util.ArrayList;

public class Game {
	private Deck deck;
	private ArrayList<Player> players;
	private Card[] communityCards = new Card[5];

	public Game(int numPlayers) {
		players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player());
		}
	}

	@SuppressWarnings("null")
	public void play() {
		start();
		double pot = players.size() * 2;

		// deal community cards
		for (int i = 0; i < 5; i++) {
			communityCards[i] = deck.dealCard();
		}

		// deal cards to players
		dealCards();

		// show community cards
		System.out.print("\nCommunity Cards: ");
		for (Card card : communityCards) {
			System.out.print(card + " ");
		}
		System.out.println("\n\n==========================================");

		// show balances and best hands ,, determine winner(s)
		ArrayList<Player> winners = new ArrayList<>();
		ArrayList<PokerHand> winningHands = new ArrayList<>();
		int numWinners = 0;
		ArrayList<Integer> winningPlayerNumbers = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			player.balance = player.balance - 2;
			player.calcBestHand();
			System.out.printf("Player %d: $%.1f - %s %s\n", i + 1, player.getBalance(), player.hand.get(0),
					player.hand.get(1));
			System.out.printf("     Best hand: %s      - %s\n", player.bestHand,
					player.bestHand.calcHandRank().toString());
			if (winningHands.size() == 0) {
				winners.add(player);
				winningHands.add(player.bestHand);
				winningPlayerNumbers.add(i + 1);
				numWinners = numWinners + 1;
			} else if (player.bestHand.compareTo(winningHands.get(0)) > 0) {
				winners.clear();
				winningHands.clear();
				winningPlayerNumbers.clear();
				winners.add(player);
				winningHands.add(player.bestHand);
				winningPlayerNumbers.add(i + 1);
				numWinners = 1;
			} else if (player.bestHand.compareTo(winningHands.get(0)) == 0) {
				winners.add(player);
				winningHands.add(player.bestHand);
				winningPlayerNumbers.add(i + 1);
				numWinners = numWinners + 1;
			}
		}

		// print winners and distribute pot
		if (winners.size() == 1) {
			Player winner = winners.get(0);
			winner.balance += pot;
			System.out.println("Winner: Player " + winningPlayerNumbers.get(0) + " $" + winner.getBalance());
			System.out.println("==========================================");
			System.out.println("    " + winner.bestHand.calcHandRank().toString() + " " + winner.bestHand);
		} else {
			System.out.println("\nWinning hands (tie)");
			double splitPot = pot / winners.size();
			for (int i = 0; i < winners.size(); i++) {
				Player winner = winners.get(i);
				winner.balance += splitPot;
				System.out.println(winningHands.get(i).toString() + " " + winningHands.get(i).calcHandRank().toString()
						+ " Player " + winningPlayerNumbers.get(i) + " $" + winner.getBalance());
			}
		}

		winners.clear();
		winningHands.clear();
		winningPlayerNumbers.clear();

	}

	public void start() {
		for (Player player : players) {
			player.clearHand();
		}
		deck = new Deck();
		deck.initializeDeck();
		deck.shuffle();
	}

	private void dealCards() {
		for (Player player : players) {
			for (int i = 0; i < 2; i++) { // 2 cards per player
				player.addCard(deck.dealCard());
			}
			for (Card card : communityCards) { // include community cards
				player.addCard(card);
			}
		}
	}

}
