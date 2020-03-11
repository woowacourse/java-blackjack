package domain.gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardNumber;

public abstract class Gamer {
	private String name;
	private final List<Card> cards = new ArrayList<>();

	public Gamer(String name) {
		this.name = name;
	}

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public abstract boolean isDrawable();

	public static final int BUST_NUMBER = 22;
	public static final int ACE_HIDDEN_SCORE = 10;

	public int calculateWithAce() {
		int score = calculateScore();
		if (isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
			return score + ACE_HIDDEN_SCORE;
		}
		return score;
	}

	private int calculateScore() {
		return cards
			.stream()
			.map(Card::getCardNumber)
			.mapToInt(CardNumber::getScore)
			.sum();
	}

	public String getName() {
		return name;
	}

	public boolean isContainAce(){
		return cards.stream().anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
	}

	@Override
	public String toString() {
		return name + " : " + cards.stream()
			.map(Card::toString)
			.collect(Collectors.joining(", "));
	}
}
