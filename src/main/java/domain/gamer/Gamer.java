package domain.gamer;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import exception.NameFormatException;

public abstract class Gamer {
	public static final int BUST_NUMBER = 22;
	public static final int BLACKJACK_NUMBER = 21;
	private static final int ACE_HIDDEN_SCORE = 10;
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final String NAME_PATTERN = "[a-zA-Z가-힣]*";

	private String name;
	private final List<Card> cards;
	private Money money;

	public Gamer(String name, String money) {
		if (isInvalidName(name)) {
			throw new NameFormatException(name);
		}

		this.name = name;
		cards = new ArrayList<>();
		this.money = new Money(money);
	}

	private boolean isInvalidName(String name) {
		return !name.matches(NAME_PATTERN);
	}

	public abstract boolean isDrawable();

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackJack() {
		return calculateScore() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_SIZE;
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

	public Money getMoney() {
		return money;
	}
}
