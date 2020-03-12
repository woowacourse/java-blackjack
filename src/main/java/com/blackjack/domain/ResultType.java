package com.blackjack.domain;

public enum ResultType {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private String alias;

	ResultType(String alias) {
		this.alias = alias;
	}

	public ResultType convertResultType() {
		if (WIN.equals(this)) {
			return LOSE;
		}
		if (LOSE.equals(this)) {
			return WIN;
		}
		return DRAW;
	}

	@Override
	public String toString() {
		return alias;
	}
}
