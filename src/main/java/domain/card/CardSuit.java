package domain.card;

public enum CardSuit {
	SPADE("스페이드"),
	CLOVER("클로버"),
	DIAMOND("다이아몬드"),
	HEART("하트");

	private String suit;

	CardSuit(String suit) {
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}
}
