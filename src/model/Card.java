package model;

public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	@Override
	public int compareTo(Card other) {
		return this.rank.compareTo(other.rank);
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return rank.getValue();
	}
	
	public boolean equals(Card other) {
		return this.rank == other.rank;
	}
	
	@Override
    public String toString() {
        String suitSymbol;
        String rankSymbol;
        switch (suit) {
            case CLUBS:
                suitSymbol = "\u2663"; // club
                break;
            case DIAMONDS:
                suitSymbol = "\u2666"; // diamond
                break;
            case HEARTS:
                suitSymbol = "\u2665"; // heart
                break;
            case SPADES:
                suitSymbol = "\u2660"; // spade
                break;
            default:
                suitSymbol = "?"; // if no suit for some reason
        }
        
        switch (rank) {
        case DEUCE:
            rankSymbol = "2";
            break;
        case THREE:
            rankSymbol = "3";
            break;
        case FOUR:
            rankSymbol = "4";
            break;
        case FIVE:
            rankSymbol = "5";
            break;
        case SIX:
            rankSymbol = "6";
            break;
        case SEVEN:
            rankSymbol = "7";
            break;
        case EIGHT:
            rankSymbol = "8";
            break;
        case NINE:
            rankSymbol = "9";
            break;
        case TEN:
            rankSymbol = "10";
            break;
        case JACK:
            rankSymbol = "J";
            break;
        case QUEEN:
            rankSymbol = "Q";
            break;
        case KING:
            rankSymbol = "K";
            break;
        case ACE:
            rankSymbol = "A";
            break;
        default:
            rankSymbol = "?"; // if no rank for some reason
    }
        return rankSymbol + suitSymbol;
    }
}