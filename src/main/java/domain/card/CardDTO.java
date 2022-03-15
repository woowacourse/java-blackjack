package domain.card;

public class CardDTO {
	private final Rank rank;
	private final Suit suit;

	public CardDTO(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
}
