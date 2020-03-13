package domain;

import domain.card.Cards;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum ScoreType {
	BURST(cards -> cards.getScore() > 21, score -> 0),
	BIG_ACE(cards -> cards.hasAce() && cards.getScore() <= 11, score -> score + 10);

	private Predicate<Cards> scoreTypeJudge;
	private Function<Integer, Integer> scoreConverter;

	ScoreType(Predicate<Cards> scoreTypeJudge, Function<Integer, Integer> scoreConverter) {
		this.scoreTypeJudge = scoreTypeJudge;
		this.scoreConverter = scoreConverter;
	}

	public static int of(Cards cards) {
		return Arrays.stream(ScoreType.values())
				.filter(scoreType -> scoreType.scoreTypeJudge.test(cards))
				.mapToInt(scoreType -> scoreType.scoreConverter.apply(cards.getScore()))
				.findFirst()
				.orElse(cards.getScore());
	}
}
