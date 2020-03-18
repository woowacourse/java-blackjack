package com.blackjack.domain;

import java.util.Arrays;

public enum ResultType {
	WIN("승", 1),
	DRAW("무", 0),
	LOSE("패", -1);

	private String alias;
	private int compareResult;

	ResultType(String alias, int compareResult) {
		this.alias = alias;
		this.compareResult = compareResult;
	}

	public static ResultType of(int compare) {
		return Arrays.stream(values())
				.filter(resultType -> resultType.compareResult == compare)
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public ResultType reverseResultType() {
		if (isLose()) {
			return WIN;
		}
		if (isWin()) {
			return LOSE;
		}
		return DRAW;
	}

	private boolean isWin() {
		return WIN.equals(this);
	}

	private boolean isLose() {
		return LOSE.equals(this);
	}

	@Override
	public String toString() {
		return alias;
	}
}
