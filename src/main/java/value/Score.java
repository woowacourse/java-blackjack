package value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record Score(int value) {

	private static final Map<Integer, Score> CACHE = new ConcurrentHashMap<>();

	public static Score from(final int value) {
		if (value <= 21) {
			return CACHE.computeIfAbsent(value, (v) -> new Score(v));
		}
		return new Score(value);
	}

	public Score plus(final Score plusValue) {
		return Score.from(value + plusValue.value);
	}

	public Score minus(final Score minusValue) {
		return Score.from(value - minusValue.value);
	}

	public boolean isGreaterThan(final Score score) {
		return this.value > score.value;
	}

	public boolean isLessThan(final Score score) {
		return this.value < score.value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Score score = (Score)o;

		return value == score.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
