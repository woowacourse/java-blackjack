package view;

import java.util.Arrays;

import card.Rank;

public enum RankMessage {
	ACE(Rank.ACE, "A"),
	TWO(Rank.TWO, "2"),
	THREE(Rank.THREE, "3"),
	FOUR(Rank.FOUR, "4"),
	FIVE(Rank.FIVE, "5"),
	SIX(Rank.SIX, "6"),
	SEVEN(Rank.SEVEN, "7"),
	EIGHT(Rank.EIGHT, "8"),
	NINE(Rank.NINE, "9"),
	TEN(Rank.TEN, "10"),
	JACK(Rank.JACK, "J"),
	QUEEN(Rank.QUEEN, "Q"),
	KING(Rank.KING, "K");

	private final Rank rank;
	private final String message;

	RankMessage(final Rank rank, final String message) {
		this.rank = rank;
		this.message = message;
	}

	public static String convertRankToMessage(final Rank rank) {
		return Arrays.stream(values())
			.filter(rankMessage -> rankMessage.rank == rank)
			.map(RankMessage::getMessage)
			.findAny()
			.orElseThrow(IllegalAccessError::new);
	}

	public String getMessage() {
		return message;
	}
}
