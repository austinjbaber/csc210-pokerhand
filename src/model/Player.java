package model;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand = new ArrayList<>();
    double balance = 100;
    private ArrayList<PokerHand> allHands = new ArrayList<>();
    PokerHand bestHand;
    
    public Player() {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

	public double getBalance() {
		return balance;
	}

	public void clearHand() {
		hand = new ArrayList<>();
		allHands = new ArrayList<>();
	}

	public void addCard(Card dealtCard) {
		hand.add(dealtCard);
	}
	
    public void allHandCombinations(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7) {
        allHands.add(new PokerHand(c1, c2, c3, c4, c5));
        allHands.add(new PokerHand(c1, c2, c3, c4, c6));
        allHands.add(new PokerHand(c1, c2, c3, c4, c7));
        allHands.add(new PokerHand(c1, c2, c3, c5, c6));
        allHands.add(new PokerHand(c1, c2, c3, c5, c7));
        allHands.add(new PokerHand(c1, c2, c3, c6, c7));
        allHands.add(new PokerHand(c1, c2, c4, c5, c6));
        allHands.add(new PokerHand(c1, c2, c4, c5, c7));
        allHands.add(new PokerHand(c1, c2, c4, c6, c7));
        allHands.add(new PokerHand(c1, c2, c5, c6, c7));
        allHands.add(new PokerHand(c1, c3, c4, c5, c6));
        allHands.add(new PokerHand(c1, c3, c4, c5, c7));
        allHands.add(new PokerHand(c1, c3, c4, c6, c7));
        allHands.add(new PokerHand(c1, c3, c5, c6, c7));
        allHands.add(new PokerHand(c1, c4, c5, c6, c7));
        allHands.add(new PokerHand(c2, c3, c4, c5, c6));
        allHands.add(new PokerHand(c2, c3, c4, c5, c7));
        allHands.add(new PokerHand(c2, c3, c4, c6, c7));
        allHands.add(new PokerHand(c2, c3, c5, c6, c7));
        allHands.add(new PokerHand(c2, c4, c5, c6, c7));
        allHands.add(new PokerHand(c3, c4, c5, c6, c7));
    }
    
    public void calcBestHand() {
    	
    	Card c1 = hand.get(0);
    	Card c2 = hand.get(1);
    	Card c3 = hand.get(2);
    	Card c4 = hand.get(3);
    	Card c5 = hand.get(4);
    	Card c6 = hand.get(5);
    	Card c7 = hand.get(6);
    	
        allHandCombinations(c1, c2, c3, c4, c5, c6, c7);
		
        PokerHand bestHand = null;
        
        for (PokerHand currentHand : allHands) {
            if (bestHand == null || currentHand.compareTo(bestHand) > 0) {
                bestHand = currentHand;
            }
        }
        
        this.bestHand = bestHand;
		
	}
    
    public String bestRank() {
    	return bestHand.calcHandRank().toString();
    }

    public void showBestHand() {
    	
    	Card c1 = hand.get(0);
    	Card c2 = hand.get(1);
    	Card c3 = hand.get(2);
    	Card c4 = hand.get(3);
    	Card c5 = hand.get(4);
    	Card c6 = hand.get(5);
    	Card c7 = hand.get(6);
    	
        allHandCombinations(c1, c2, c3, c4, c5, c6, c7);
		
        PokerHand bestHand = null;
        
        for (PokerHand currentHand : allHands) {
            if (bestHand == null || currentHand.compareTo(bestHand) > 0) {
                bestHand = currentHand;
            }
        }
        String bestRank = bestHand.calcHandRank().toString();
		
		System.out.println("Best hand: " + bestHand + "    - " + bestRank);
		
	}
}