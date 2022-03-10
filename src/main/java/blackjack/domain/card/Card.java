package blackjack.domain.card;

public class Card {
	private static final int ACE = 1;

	private final String name;
	private final int value;

	public Card(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public boolean isAce() {
		return value == ACE;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
