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
	),
	NORMAL(
		cards -> !BURST.scoreJudge.test(cards) && !BIG_ACE.scoreJudge.test(cards),
		cardsPoint -> cardsPoint
	);

	private final Predicate<Cards> scoreJudge;
	private final Function<Integer, Integer> getScore;

	ScoreType(Predicate<Cards> scoreJudge, Function<Integer, Integer> getScore) {
		this.scoreJudge = scoreJudge;
		this.getScore = getScore;
	}

	public static ScoreType of(Cards cards) {
		return Arrays.stream(ScoreType.values())
			.filter(scoreType -> scoreType.scoreJudge.test(cards))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public int getScore(int cardsPoint) {
		return getScore.apply(cardsPoint);
	}
}
