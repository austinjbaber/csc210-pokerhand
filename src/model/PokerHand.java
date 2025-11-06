package model;

// @author: Austin Baber
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * When sorted, a list of PokerHand objects will be in ascending order.
 */

public class PokerHand implements Comparable<PokerHand> {

	private Card[] cards;

	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
		cards = new Card[] { c1, c2, c3, c4, c5 };
		Arrays.sort(cards);
	}

	@Override
	public int compareTo(PokerHand other) {

		HandRank thisRank = calcHandRank();
		HandRank otherRank = other.calcHandRank();

		int rankComparison = thisRank.compareTo(otherRank);
		if (rankComparison != 0) {
			return rankComparison; // -1 for loss, 1 for win
		}

		if (isStraightFlush() && other.isStraightFlush()) {
			// compare highest card in straight flushes
			return this.cards[4].compareTo(other.cards[4]); // -1 or 1
		} else if (hasFourOfAKind() && other.hasFourOfAKind()) {
			// compare ranks
			int thisFourRank = getFourOfAKindRank();
			int otherFourRank = other.getFourOfAKindRank();
			if (thisFourRank != otherFourRank) {
				return Integer.compare(thisFourRank, otherFourRank); // -1 or 1
			}
			// if four-of-a-kind ranks are equal, compare the remaining card
			int thisRemainingRank;
			int otherRemainingRank;
			if (thisFourRank == this.cards[0].getRank().getValue()) { // indicates last card is unique
				thisRemainingRank = this.cards[4].getRank().getValue();
			} else { // indicates first card is unique
				thisRemainingRank = this.cards[0].getRank().getValue();
			}
			if (otherFourRank == this.cards[0].getRank().getValue()) { // indicates last card is unique
				otherRemainingRank = other.cards[4].getRank().getValue();
			} else { // indicates first card is unique
				otherRemainingRank = other.cards[0].getRank().getValue();
			}
			return Integer.compare(thisRemainingRank, otherRemainingRank); // -1 0 or 1
		} else if (isFullHouse() && other.isFullHouse()) {
			int thisThreeOfAKindRank = getThreeOfAKindRank();
			int otherThreeOfAKindRank = other.getThreeOfAKindRank();
			int thisPairRank = getFullHousePairRank();
			int otherPairRank = other.getFullHousePairRank();
			// compare three-of-a-kind ranks first
			int threeOfAKindComparison = Integer.compare(thisThreeOfAKindRank, otherThreeOfAKindRank);
			if (threeOfAKindComparison != 0) {
				return threeOfAKindComparison; // -1 or 1 (0 should theoretically be impossible)
			} else { // compare pair ranks
				return Integer.compare(thisPairRank, otherPairRank); // -1 0 or 1
			}
		} else if (isStraightFlush() && other.isStraightFlush()) {
			return Integer.compare(cards[4].getRank().getValue(), other.cards[4].getRank().getValue()); // compare
																										// highest card
		} else if (isFlush() && other.isFlush()) {
			return compareRemainingCards(other); // look at all 5 ranks
		} else if (isStraight() && other.isStraight()) {
			return compareRemainingCards(other); // look at all 5 ranks
		} else if (hasThreeOfAKind() && other.hasThreeOfAKind()) {
			int thisThreeOfAKindRank = getThreeOfAKindRank();
			int otherThreeOfAKindRank = other.getThreeOfAKindRank();
			if (thisThreeOfAKindRank != otherThreeOfAKindRank) { // should theoretically not be able to occur
				return Integer.compare(thisThreeOfAKindRank, otherThreeOfAKindRank); // -1 or 1
			}
			return compareRemainingCards(other);
		} else if (hasTwoPair() && other.hasTwoPair()) {
			int thisPairRank = getFirstPairRank();
			int otherPairRank = other.getFirstPairRank();
			int thisSecondPairRank = getSecondPairRank();
			int otherSecondPairRank = other.getSecondPairRank();
			if (thisSecondPairRank != otherSecondPairRank) { // compare high pair first
				return Integer.compare(thisSecondPairRank, otherSecondPairRank); // -1 or 1
			} else if (thisPairRank != otherPairRank) {
				return Integer.compare(thisPairRank, otherPairRank); // -1 or 1
			} else { // compare remaining card
				return compareRemainingCards(other); // -1 0 or 1
			}
		} else if (hasOnePair() && other.hasOnePair()) {
			int thisPairRank = getFirstPairRank();
			int otherPairRank = other.getFirstPairRank();
			if (thisPairRank != otherPairRank) { // compare ranks
				return Integer.compare(thisPairRank, otherPairRank); // -1 or 1
			} else {
				return compareRemainingCards(other); // -1 0 or 1
			}
		} else {
			return compareRemainingCards(other); // high card
		}
	}

	HandRank calcHandRank() {
		if (isRoyalFlush()) {
			return HandRank.RoyalFlush;
		} else if (isStraightFlush()) {
			return HandRank.StraightFlush;
		} else if (hasFourOfAKind()) {
			return HandRank.FourOfAKind;
		} else if (isFullHouse()) {
			return HandRank.FullHouse;
		} else if (isFlush()) {
			return HandRank.Flush;
		} else if (isStraight()) {
			return HandRank.Straight;
		} else if (hasThreeOfAKind()) {
			return HandRank.ThreeOfAKind;
		} else if (hasTwoPair()) {
			return HandRank.TwoPair;
		} else if (hasOnePair()) {
			return HandRank.Pair;
		} else {
			return HandRank.HighCard;
		}
	}

	private boolean isRoyalFlush() {
		if (isStraightFlush() && cards[4].getRank().getValue() == 14) { // iff ace high
			return true;
		}
		return false;
	}

	private boolean isStraightFlush() {
		return (isStraight() && isFlush());
	}

	private boolean hasFourOfAKind() {
		// only need to check if adjacent cards have the same rank
		for (int i = 0; i <= 1; i++) {
			if (cards[i].getRank() == cards[i + 1].getRank() && cards[i].getRank() == cards[i + 2].getRank()
					&& cards[i].getRank() == cards[i + 3].getRank()) {
				return true; // four cards of the same rank found
			}
		}
		return false;
	}

	private int getFourOfAKindRank() {
		// only need to check the first and last cards
		if (cards[0].getRank() == cards[1].getRank()) {
			return cards[4].getRank().getValue(); // non-four-of-a-kind card is the last card
		} else {
			return cards[0].getRank().getValue(); // non-four-of-a-kind card is the first card
		}
	}

	private boolean isFullHouse() {
		if (hasThreeOfAKind()) {
			int threeOfAKindRank = getThreeOfAKindRank();
			if (hasTwoPair()) { // full house technically also has 2 pair hehe
				int pairRank = getFullHousePairRank();
				if (threeOfAKindRank != pairRank) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isFlush() {
		Suit firstSuit = cards[0].getSuit();
		for (int i = 1; i < 5; i++) {
			if (cards[i].getSuit() != firstSuit) {
				return false; // not all same suit
			}
		}
		return true; // loop completes on a flush
	}

	private boolean isStraight() {
		for (int i = 0; i < 4; i++) {
			if (cards[i].getRank().getValue() + 1 != cards[i + 1].getRank().getValue()) {
				return false; // not a consecutive sequence
			}
		}
		return true; // loop completes on a straight
	}

	private boolean hasThreeOfAKind() {
		return (cards[0].getRank() == cards[1].getRank() && cards[1].getRank() == cards[2].getRank()) || // 1+2+3
				(cards[1].getRank() == cards[2].getRank() && cards[2].getRank() == cards[3].getRank()) || // 2+3+4
				(cards[2].getRank() == cards[3].getRank() && cards[3].getRank() == cards[4].getRank()); // 3+4+5
	}

	private int getThreeOfAKindRank() {
		for (int i = 0; i < 3; i++) {
			if (cards[i].getRank() == cards[i + 1].getRank() && cards[i].getRank() == cards[i + 2].getRank()) {
				return cards[i].getRank().getValue(); // return rank
			}
		}
		return -1; // no three-of-a-kind found
	}

	private int getFullHousePairRank() {
		int threeOfAKindRank = getThreeOfAKindRank();
		for (int i = 0; i < 5; i++) {
			int rank = cards[i].getRank().getValue();
			if (rank != threeOfAKindRank) { // first card that isn't in threeOfAKind
				return rank;
			}
		}
		return -1; // if 5 of a kind i suppose
	}

	private boolean hasTwoPair() {
		int firstPairRank = getFirstPairRank();
		if (firstPairRank == -1) { // no pair found
			return false;
		}
		for (int i = 0; i < 4; i++) {
			if (cards[i].getRank() == cards[i + 1].getRank() && cards[i].getRank().getValue() != firstPairRank) { // found
																													// second
																													// pair
				return true;
			}
		}
		return false; // 0 or 1 pairs
	}

	private boolean hasOnePair() {
		return (cards[0].getRank() == cards[1].getRank()) || // 1 + 2
				(cards[1].getRank() == cards[2].getRank()) || // 2 + 3
				(cards[2].getRank() == cards[3].getRank()) || // 3 + 4
				(cards[3].getRank() == cards[4].getRank()); // 4 + 5
	}

	private int getFirstPairRank() {
		int pairRank = -1;
		for (int i = 0; i < 4; i++) {
			if (cards[i].getRank() == cards[i + 1].getRank()) { // found first pair
				pairRank = cards[i].getRank().getValue();
				break;
			}
		}
		return pairRank; // return value of rank
	}

	private int getSecondPairRank() {
		int firstPairRank = getFirstPairRank();
		int secondPairRank = -1;
		if (firstPairRank == -1) { // no pair found
			return -1;
		}
		for (int i = 0; i <= 3; i++) {
			if (cards[i].getRank() == cards[i + 1].getRank() && cards[i].getRank().getValue() != firstPairRank) { // found
																													// second
																													// pair
				secondPairRank = cards[i].getRank().getValue();
				break;
			}
		}
		return secondPairRank;
	}

	private int compareRemainingCards(PokerHand other) {
		ArrayList<Integer> thisRemainingRanks = getRemainingRanks(); // get ranks of this hand
		ArrayList<Integer> otherRemainingRanks = other.getRemainingRanks(); // get ranks of other hand

		for (int i = thisRemainingRanks.size() - 1; i >= 0; i--) { // iterate over returned ArrayList
			int comparison = Integer.compare(thisRemainingRanks.get(i), otherRemainingRanks.get(i));
			if (comparison != 0) {
				return comparison; // -1 or 1
			}
		}
		return 0; // tie
	}

	private ArrayList<Integer> getRemainingRanks() {
		ArrayList<Integer> remainingRanks = new ArrayList<>();
		if (hasThreeOfAKind()) {
			int threeOfAKindRank = getThreeOfAKindRank();
			for (int i = 0; i < 5; i++) {
				int rank = cards[i].getRank().getValue();
				if (rank != threeOfAKindRank) {
					remainingRanks.add(rank);
				}
			}
		} else if (hasTwoPair()) {
			for (int i = 0; i <= 4; i++) {
				if (!isCardPartOfPair(cards[i])) {
					remainingRanks.add(cards[i].getRank().getValue());
				}
			}
		} else if (hasOnePair()) {
			for (int i = 0; i < 5; i++) {
				if (!isCardPartOfPair(cards[i])) {
					remainingRanks.add(cards[i].getRank().getValue());
				}
			}
		} else { // compare all 5 ranks
			for (int i = 0; i <= 4; i++) {
				remainingRanks.add(cards[i].getRank().getValue());
			}
		}
		Collections.sort(remainingRanks);
		return remainingRanks;
	}

	private boolean isCardPartOfPair(Card card) {
		return card.getRank().getValue() == getFirstPairRank() || card.getRank().getValue() == getSecondPairRank();
	}
	
	@Override
	public String toString() {
	    StringBuilder hand = new StringBuilder();
	    for (int i = 0; i < cards.length; i++) {
	        hand.append(cards[i]);
	        if (i < cards.length - 1) {
	        	hand.append(" ");
	        }
	    }
	    return hand.toString();
	}


}