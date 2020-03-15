package com.blackjack.domain;

public enum ResultType {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

	private String alias;

	ResultType(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return alias;
	}
}
