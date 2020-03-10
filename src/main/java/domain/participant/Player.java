package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.card.Card;

public class Player {
	private final String name;
	private final List<Card> cards;

	public Player(String name) {
		validate(name);

		this.name = name;
		this.cards = new ArrayList<>();
	}

	private void validate(String name) {
		validateNull(name);
		validateSpace(name);
	}

	private void validateNull(String name) {
		if (Objects.isNull(name)) {
			throw new NullPointerException("이름은 null이 될 수 없습니다.");
		}
	}

	private void validateSpace(String name) {
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
		}
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
