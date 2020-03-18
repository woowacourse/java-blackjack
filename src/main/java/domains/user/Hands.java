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

	public Hands(List<Card> hands) {
		this.hands = hands;
	}

	Hands(Deck deck) {
		this.hands = deck.initialDraw();

	}

	int size() {
		return hands.size();
	}

	void draw(Deck deck) {
		hands.add(deck.draw());
	}

	public int score() {
		int score = 0;
		boolean hasAce = false;
		for (Card hand : hands) {
			hasAce = checkAce(hand, hasAce);
			score += hand.score();
		}
		score = determineAceScore(score, hasAce);
		return score;
	}

	private int determineAceScore(int score, boolean hasAce) {
		if (score <= ACE_SCORE_CHANGE_POINT && hasAce) {
			return score + ACE_EXTRA_SCORE;
		}
		return score;
	}

	private boolean checkAce(Card card, boolean hasAce) {
		return hasAce || card.isAce();
	}

	public boolean isBurst() {
		return score() > BURST_SCORE;
	}

	public boolean isBlackJack() {
		return score() == BURST_SCORE;
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
