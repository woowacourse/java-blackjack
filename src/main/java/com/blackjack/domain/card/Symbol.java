package com.blackjack.domain.card;

public enum Symbol {
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

	private int score;
	private String alias;

	Symbol(int score) {
		this(score, String.valueOf(score));
	}

	Symbol(int score, String alias) {
		this.score = score;
		this.alias = alias;
	}

	public int getScore() {
		return score;
	}

	public boolean isAce() {
		return ACE == this;
	}

	@Override
	public String toString() {
		return alias;
	}
}
