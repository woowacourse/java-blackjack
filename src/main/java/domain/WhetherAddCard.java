package domain;

import java.util.Arrays;

public enum WhetherAddCard {
	Y("y"),
	N("n");

	private final String type;

	WhetherAddCard(String type) {
		this.type = type;
	}

	public static WhetherAddCard of(String type) {
		return Arrays.stream(values())
				.filter(whetherAddCard -> whetherAddCard.type.equals(type))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("y 또는 n이 아닙니다."));
	}

	public boolean isYes() {
		return this == Y;
	}
}
