package domain;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import domain.card.Cards;

public enum ScoreType {
	BURST(
		cards -> cards.getPoint() > 21,
		cardsPoint -> 0
	),
	BIG_ACE(
		cards -> cards.hasAce() && cards.getPoint() <= 11,
		cardsPoint -> cardsPoint + 10
	);

	private final Predicate<Cards> scoreJudge;
	private final Function<Integer, Integer> getScore;

	ScoreType(Predicate<Cards> scoreJudge, Function<Integer, Integer> getScore) {
		this.scoreJudge = scoreJudge;
		this.getScore = getScore;
	}

	public static int getScoreOf(Cards cards) {
		return Arrays.stream(ScoreType.values())
			.filter(scoreType -> scoreType.scoreJudge.test(cards))
			.mapToInt(scoreType -> scoreType.getScore.apply(cards.getPoint()))
			.findFirst()
			.orElse(cards.getPoint());
	}
}
