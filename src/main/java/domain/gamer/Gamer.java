package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {
	private static final int BUST_NUMBER = 22;
	private static final int ACE_HIDDEN_SCORE = 10;

	private String name;
	private final List<Card> cards = new ArrayList<>();
	private Money money;

	public Gamer(String name, String money) {
		this.name = name;
		this.money = new Money(money);
	}

	public abstract boolean isDrawable();

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackJack() {
		return calculateScore() == 21 && cards.size() == 2;
	}

	public int calculateScore() {
		int score = calculateScoreWithoutAce();

		if (isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
			return score + ACE_HIDDEN_SCORE;
		}

		return score;
	}

	private int calculateScoreWithoutAce() {
		return cards.stream()
			.map(Card::getCardNumber)
			.mapToInt(CardNumber::getScore)
			.sum();
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
