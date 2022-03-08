package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participant {

	protected static final String BLANK_NAME_ERROR_MESSAGE = "[Error] 이름은 공백이거나 빈칸일 수 없습니다.";
	protected static final String SHOW_HAND_FORMAT = "%s카드: %s";
	protected static final int BLACK_JACK_NUMBER = 21;
	protected static final String JOINING_DELIMITER = ", ";

	protected final String name;
	protected List<Card> hand;

	public Participant(String name, List<Card> hand) {
		validateName(name);
		this.name = name;
		this.hand = hand;
	}

	protected void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(BLANK_NAME_ERROR_MESSAGE);
		}
	}

	public String showHand() {
		String joinedCards = String.join(JOINING_DELIMITER,
			hand.stream().map(Card::toString).collect(Collectors.toList()));
		return String.format(SHOW_HAND_FORMAT, name, joinedCards);
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public boolean isBurst() {
		return getMinScore() > BLACK_JACK_NUMBER;
	}

	protected int getMinScore() {
		return hand.stream().mapToInt(Card::getPoint).sum();
	}
}
