package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private static final String NAME_ERROR = "[Error] 이름은 빈 값일 수 없습니다.";

	protected String name;
	protected List<Card> myCards;

	public Person(String name) {
		validateName(name);
		this.name = name;
		this.myCards = new ArrayList<>();
	}

	private void validateName(String name) {
		if(name.isEmpty() || name.isBlank()) {
			throw new IllegalArgumentException(NAME_ERROR);
		}
	}

	public void addCard(Card card) {
		myCards.add(card);
	}

	public int score() {
		return myCards.stream()
			.mapToInt(Card::getNumber)
			.sum();
	}

	public List<Card> getMyCards() {
		return myCards;
	}

	public String getName() {
		return name;
	}
}
