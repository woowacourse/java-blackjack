package domain;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum ScoreType {
	BURST(point -> point > 21, point -> 0),
	NORMAL(point -> point <= 21, point -> point);

	private final Predicate<Integer> scoreJudge;
	private final Function<Integer, Integer> getScore;

	ScoreType(Predicate<Integer> scoreJudge, Function<Integer, Integer> getScore) {
		this.scoreJudge = scoreJudge;
		this.getScore = getScore;
	}

	public static ScoreType of(int point) {
		return Arrays.stream(ScoreType.values())
			.filter(scoreType -> scoreType.scoreJudge.test(point))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public int getScore(int point) {
		return getScore.apply(point);
	}
}
