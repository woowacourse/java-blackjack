package domain.gamer;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;

public abstract class Gamer {
	public static final int BLACKJACK_NUMBER = 21;
	public static final int BLACKJACK_CARD_SIZE = 2;

	private String name;
	private final List<Card> cards;
    protected Score score;

    public Gamer(String name) {
        this.name = name;
        this.score = new Score();
      		cards = new ArrayList<>();

    }
  
	public abstract boolean isDrawable();

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
    score.calculateScore(this.cards, isContainAce());
	}

	public boolean isBlackJack() {
		return score.getScore() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_SIZE;
	}

	public boolean isContainAce() {
		return cards.stream()
			.anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}
}
