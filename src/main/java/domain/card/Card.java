package domain.card;

public class Card {

	private final Denomination denomination;
	private final Suits suit;

	public Card(Denomination denomination, Suits suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public int getScore() {
		return denomination.getScore();
	}

	public String getPoint() {
		return denomination.getPoint();
	}

	public String getSuit() {
		return suit.getName();
	}

	public String getCardName() {
		return denomination.getPoint() + suit.getName();
	}
}
