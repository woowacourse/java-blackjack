package value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record Count(int value) {
	private static final Map<Integer, Count> CACHE = new ConcurrentHashMap<>();

	public static Count from(final int value) {
		if (value <= 5) {
			return CACHE.computeIfAbsent(value, v -> new Count(v));
		}
		return new Count(value);
	}

	public Count increment() {
		return Count.from(value + 1);
	}

	public Count decrement() {
		return Count.from(value - 1);
	}

	public boolean isZero() {
		return value == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Count count = (Count)o;

		return value == count.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	public boolean isGreaterThan(final Count count) {
		return value > count.value;
	}
}
