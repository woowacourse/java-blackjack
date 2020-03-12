package com.blackjack.domain.card;

public enum Type {
	SPADE("스페이드"),
	DIAMOND("다이아몬드"),
	HEART("하트"),
	CLUB("클럽");

	private String alias;

	Type(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return alias;
	}
}