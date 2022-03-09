package blackjack.domain;

public class Card {
	private final String name;
	private final int value;

	public Card(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public boolean isAce() {
		return value == 1;
	}
}
