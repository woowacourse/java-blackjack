package blackjack.domain;

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
}
