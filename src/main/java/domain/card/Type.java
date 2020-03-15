package domain.card;

import java.util.Arrays;

public enum Type {
	ACE("1", 1),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10);

	private final String name;
	private final int score;

	Type(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public static Type of(String passedName) {
		return Arrays.stream(values())
			.filter(type -> type.name.equals(passedName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 타입 이름입니다."));
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
