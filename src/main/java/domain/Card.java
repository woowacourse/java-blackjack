package domain;

public class Card {
	private final Suit suit;
	private final Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuitName() {
		return suit.getName();
	}

	public String getRankName() {
		return rank.getName();
	}
}
