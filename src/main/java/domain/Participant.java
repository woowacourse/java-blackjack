package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participant {
	protected static final String SHOW_HAND_FORMAT = "%s카드: %s";
	protected static final int BLACK_JACK_NUMBER = 21;
	protected static final String JOINING_DELIMITER = ", ";
	protected static final int ACE_COUNT_LOWER_BOUND = 0;
	protected static final int ADDITIONAL_SCORE_ACE = 10;

	protected final Name name;
	protected List<Card> hand;

	public Participant(Name name, List<Card> hand) {
		this.name = name;
		this.hand = new ArrayList<>(hand);
	}

	public String showHand() {
		String joinedCards = String.join(JOINING_DELIMITER,
			hand.stream().map(Card::toString).collect(Collectors.toList()));
		return String.format(SHOW_HAND_FORMAT, name.getName(), joinedCards);
	}

	public String showHandAndBestScore() {
		return String.join(" - 결과 : ", showHand(), String.valueOf(getBestScore()));
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public boolean isBust() {
		return getMinScore() > BLACK_JACK_NUMBER;
	}

	protected int getMinScore() {
		return hand.stream().mapToInt(Card::getPoint).sum();
	}

	public boolean isBlackJack() {
		return getBestScore() == BLACK_JACK_NUMBER;
	}

	public int getBestScore() {
		int aceCount = getAceCount();
		int bestScore = getMinScore();

		while (aceCount > ACE_COUNT_LOWER_BOUND && bestScore + ADDITIONAL_SCORE_ACE <= BLACK_JACK_NUMBER) {
			bestScore += ADDITIONAL_SCORE_ACE;
			aceCount--;
		}
		return bestScore;
	}

	protected int getAceCount() {
		return (int)hand.stream().filter(Card::isAce).count();
	}
}
