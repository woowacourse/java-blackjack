package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Participant {
	protected static final int MAX_SCORE = 21;
	protected static final int ACE_COUNT_LOWER_BOUND = 0;
	protected static final int ADDITIONAL_SCORE_ACE = 10;

	protected final Name name;
	protected final boolean blackJack;
	protected List<Card> hand;

	public Participant(Name name, List<Card> hand) {
		this.name = name;
		this.hand = new ArrayList<>(hand);
		this.blackJack = isMaxScore();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public boolean isBust() {
		return getMinScore() > MAX_SCORE;
	}

	protected int getMinScore() {
		return hand.stream().mapToInt(Card::getPoint).sum();
	}

	public boolean isMaxScore() {
		return getBestScore() == MAX_SCORE;
	}

	public int getBestScore() {
		int aceCount = getAceCount();
		int bestScore = getMinScore();

		while (aceCount > ACE_COUNT_LOWER_BOUND && bestScore + ADDITIONAL_SCORE_ACE <= MAX_SCORE) {
			bestScore += ADDITIONAL_SCORE_ACE;
			aceCount--;
		}
		return bestScore;
	}

	protected int getAceCount() {
		return (int)hand.stream().filter(Card::isAce).count();
	}

	public Name getName() {
		return Name.copyOf(name);
	}

	public List<Card> getHand() {
		return new ArrayList<>(hand);
	}

	public boolean isBlackJack() {
		return blackJack;
	}
}
