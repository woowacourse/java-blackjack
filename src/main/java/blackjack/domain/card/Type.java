package blackjack.domain.card;

public enum Type {
	SPADE("스페이드"),
	HEART("하트"),
	CLUB("클럽"),
	DIAMOND("다이아몬드");

	private final String type;

	Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
