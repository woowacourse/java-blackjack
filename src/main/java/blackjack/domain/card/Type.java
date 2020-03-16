package blackjack.domain.card;

public enum Type {
	SPADE("♠"),
	HEART("♥"),
	CLUB("♣"),
	DIAMOND("♦");

	private final String type;

	Type(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
