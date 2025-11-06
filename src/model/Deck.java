package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	public ArrayList<Card> deck;
	
	public void initializeDeck() {     
		deck = new ArrayList<Card>();
		
        deck.add(new Card(Rank.DEUCE, Suit.CLUBS));
        deck.add(new Card(Rank.THREE, Suit.CLUBS));
        deck.add(new Card(Rank.FOUR, Suit.CLUBS));
        deck.add(new Card(Rank.FIVE, Suit.CLUBS));
        deck.add(new Card(Rank.SIX, Suit.CLUBS));
        deck.add(new Card(Rank.SEVEN, Suit.CLUBS));
        deck.add(new Card(Rank.EIGHT, Suit.CLUBS));
        deck.add(new Card(Rank.NINE, Suit.CLUBS));
        deck.add(new Card(Rank.TEN, Suit.CLUBS));
        deck.add(new Card(Rank.JACK, Suit.CLUBS));
        deck.add(new Card(Rank.QUEEN, Suit.CLUBS));
        deck.add(new Card(Rank.KING, Suit.CLUBS));
        deck.add(new Card(Rank.ACE, Suit.CLUBS));
        
        deck.add(new Card(Rank.DEUCE, Suit.DIAMONDS));
        deck.add(new Card(Rank.THREE, Suit.DIAMONDS));
        deck.add(new Card(Rank.FOUR, Suit.DIAMONDS));
        deck.add(new Card(Rank.FIVE, Suit.DIAMONDS));
        deck.add(new Card(Rank.SIX, Suit.DIAMONDS));
        deck.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
        deck.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
        deck.add(new Card(Rank.NINE, Suit.DIAMONDS));
        deck.add(new Card(Rank.TEN, Suit.DIAMONDS));
        deck.add(new Card(Rank.JACK, Suit.DIAMONDS));
        deck.add(new Card(Rank.QUEEN, Suit.DIAMONDS));
        deck.add(new Card(Rank.KING, Suit.DIAMONDS));
        deck.add(new Card(Rank.ACE, Suit.DIAMONDS));

        deck.add(new Card(Rank.DEUCE, Suit.HEARTS));
        deck.add(new Card(Rank.THREE, Suit.HEARTS));
        deck.add(new Card(Rank.FOUR, Suit.HEARTS));
        deck.add(new Card(Rank.FIVE, Suit.HEARTS));
        deck.add(new Card(Rank.SIX, Suit.HEARTS));
        deck.add(new Card(Rank.SEVEN, Suit.HEARTS));
        deck.add(new Card(Rank.EIGHT, Suit.HEARTS));
        deck.add(new Card(Rank.NINE, Suit.HEARTS));
        deck.add(new Card(Rank.TEN, Suit.HEARTS));
        deck.add(new Card(Rank.JACK, Suit.HEARTS));
        deck.add(new Card(Rank.QUEEN, Suit.HEARTS));
        deck.add(new Card(Rank.KING, Suit.HEARTS));
        deck.add(new Card(Rank.ACE, Suit.HEARTS));

        deck.add(new Card(Rank.DEUCE, Suit.SPADES));
        deck.add(new Card(Rank.THREE, Suit.SPADES));
        deck.add(new Card(Rank.FOUR, Suit.SPADES));
        deck.add(new Card(Rank.FIVE, Suit.SPADES));
        deck.add(new Card(Rank.SIX, Suit.SPADES));
        deck.add(new Card(Rank.SEVEN, Suit.SPADES));
        deck.add(new Card(Rank.EIGHT, Suit.SPADES));
        deck.add(new Card(Rank.NINE, Suit.SPADES));
        deck.add(new Card(Rank.TEN, Suit.SPADES));
        deck.add(new Card(Rank.JACK, Suit.SPADES));
        deck.add(new Card(Rank.QUEEN, Suit.SPADES));
        deck.add(new Card(Rank.KING, Suit.SPADES));
        deck.add(new Card(Rank.ACE, Suit.SPADES));
    }

	public void shuffle() {
		Collections.shuffle(deck);		
	}

	public Card dealCard() {
		Card card = deck.remove(0);
		return card;
	}
}
