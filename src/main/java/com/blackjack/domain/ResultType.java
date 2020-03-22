package com.blackjack.domain;

public enum ResultType {
	BLACKJACK_WIN(1.5),
	WIN(1),
	DRAW(0),
	LOSE(-1);

	private double profitRate;

	ResultType(double profitRate) {
		this.profitRate = profitRate;
	}

	public double getProfitRate() {
		return profitRate;
	}
}