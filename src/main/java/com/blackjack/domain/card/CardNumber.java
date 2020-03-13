package com.blackjack.domain.card;

public enum CardNumber {
	ACE(1, "A"),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10, "J"),
	QUEEN(10, "Q"),
	KING(10, "K");

	private int number;
	private String alias;

	CardNumber(int number) {
		this(number, String.valueOf(number));
	}

	CardNumber(int number, String alias) {
		this.number = number;
		this.alias = alias;
	}

	public int getNumber() {
		return number;
	}

	public boolean isAce() {
		return ACE == this;
	}

	@Override
	public String toString() {
		return alias;
	}
}
