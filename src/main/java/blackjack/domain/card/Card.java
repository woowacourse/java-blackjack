package blackjack.domain.card;

import java.util.Objects;

public class Card implements Comparable<Card> {

	private final Suit suit;
	private final Denomination denomination;

	public Card(Suit suit, Denomination denomination) {
		this.suit = suit;
		this.denomination = denomination;
	}

	public String getInformation() {
		return denomination.getName() + suit.getName();
	}

	public int getScore() {
		return denomination.getScore();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof Card)) {
			return false;
		}
		Card card = (Card)o;
		return suit == card.suit && denomination == card.denomination;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, denomination);
	}

	@Override
	public int compareTo(Card o) {
		return Integer.compare(o.getScore(), this.getScore());
	}
}
