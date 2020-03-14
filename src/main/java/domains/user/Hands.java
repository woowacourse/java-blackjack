package domains.user;

import java.util.ArrayList;
import java.util.List;

import domains.card.Card;
import domains.card.Deck;

public class Hands {
	private static final int ACE_SCORE_CHANGE_POINT = 11;
	private static final int ACE_EXTRA_SCORE = 10;
	private static final int BURST_SCORE = 21;
	private static final String DELIMITER = ",";

	private List<Card> hands;
	private int score;
	private boolean hasAce;

	{
		this.score = 0;
		this.hasAce = false;
	}

	Hands(Deck deck) {
		this.hands = deck.initialDraw();
	}

	public Hands(List<Card> hands) {
		this.hands = hands;
	}

	int size() {
		return hands.size();
	}

	void draw(Deck deck) {
		hands.add(deck.draw());
	}

	public int score() {
		score = hands.stream()
			.peek(this::checkAce)
			.mapToInt(Card::score)
			.sum();
		determineAceScore();
		return this.score;
	}

	private void determineAceScore() {
		if (score <= ACE_SCORE_CHANGE_POINT && hasAce) {
			score += ACE_EXTRA_SCORE;
		}
	}

	private void checkAce(Card card) {
		if (card.isAce()) {
			hasAce = true;
		}
	}

	public boolean isBurst() {
		return score() > BURST_SCORE;
	}

	@Override
	public String toString() {
		List<String> cards = new ArrayList<>();
		for (Card card : hands) {
			cards.add(card.toString());
		}
		return String.join(DELIMITER, cards);
	}
}
