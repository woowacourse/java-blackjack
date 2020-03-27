package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class ParticipantCards {
	private static final String DELIMITER = ", ";
	private static final int MAX_SCORE = 21;
	private static final int ACE_BONUS = 10;
	private static final int FIRST_CARD_INDEX = 0;
	private static final int ACE_SCORE = 1;

	private final List<Card> cards = new ArrayList<>();

	public void add(Card card) {
		cards.add(card);
	}

	public int calculateScore() {
		int numberOfAce = (int)cards.stream().filter(c -> c.getScore() == ACE_SCORE).count();
		int sum = cards.stream().mapToInt(Card::getScore).sum();
		for (int i = 0; i < numberOfAce; i++) {
			sum = plusTenIfNotBust(sum);
		}
		return sum;
	}

	private int plusTenIfNotBust(int sum) {
		if (sum + ACE_BONUS <= MAX_SCORE) {
			return sum + ACE_BONUS;
		}
		return sum;
	}

	public int getSize() {
		return cards.size();
	}

	public String toStringOneCard() {
		return cards.get(FIRST_CARD_INDEX).toString();
	}

	@Override
	public String toString() {
		List<String> cardNames = cards.stream()
			.map(Card::toString)
			.collect(Collectors.toList());
		return String.join(DELIMITER, cardNames);
	}
}
