package blackjack.domain;

import java.util.Objects;

public class Card {
	private final CardLetter cardLetter;
	private final CardSuit cardSuit;

	public Card(CardLetter cardLetter, CardSuit cardSuit) {
		this.cardLetter = cardLetter;
		this.cardSuit = cardSuit;
	}

	public int getScore() {
		return cardLetter.getLetterScore();
	}

	public String getCardName() {
		return cardLetter.getCardName();
	}

	public String getType() {
		return cardSuit.getValue();
	}

	public boolean isAce() {
		return cardLetter == CardLetter.ACE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return cardLetter == card.cardLetter && cardSuit == card.cardSuit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardLetter, cardSuit);
	}
}
