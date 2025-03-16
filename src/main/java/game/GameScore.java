package game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record GameScore(int value) {

	private static final Map<Integer, GameScore> CACHE = new ConcurrentHashMap<>();

	public static GameScore from(final int value) {
		if (value <= 21) {
			return CACHE.computeIfAbsent(value, (v) -> new GameScore(v));
		}
		return new GameScore(value);
	}

	public GameScore plus(final GameScore plusValue) {
		return GameScore.from(value + plusValue.value);
	}

	public GameScore minus(final GameScore minusValue) {
		return GameScore.from(value - minusValue.value);
	}

	public boolean isGreaterThan(final GameScore gameScore) {
		return this.value > gameScore.value;
	}

	public boolean isLessThan(final GameScore gameScore) {
		return this.value < gameScore.value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GameScore gameScore = (GameScore)o;

		return value == gameScore.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
