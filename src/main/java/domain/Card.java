package domain;

public class Card {

	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getPoint() {
		return rank.getPoint();
	}

	@Override
	public String toString() {
		return rank.getRank() + suit.getSuit();
	}
}
