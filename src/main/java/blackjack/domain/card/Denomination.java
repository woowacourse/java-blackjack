package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Denomination {
	ACE("A", 1),
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
	KING("K", 10),
	;

	private final String name;
	private final int score;

	Denomination(final String name, final int score) {
		this.name = name;
		this.score = score;
	}

	public static List<Card> makeSuitSet(final Suit suit) {
		return Arrays.stream(values())
			.map(denomination -> new Card(suit, denomination))
			.collect(Collectors.toList());
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

}
