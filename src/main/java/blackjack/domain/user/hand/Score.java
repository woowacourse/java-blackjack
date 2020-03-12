package blackjack.domain.user.hand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import blackjack.domain.card.Symbol;

public class Score {
	private final static Map<Integer, Score> CACHE = new HashMap<>();

	private final int score;

	static {
		Arrays.stream(Symbol.values())
			.map(Symbol::getSymbol)
			.forEach(symbolValue ->
				CACHE.putIfAbsent(symbolValue, new Score(symbolValue)));
	}

	public Score() {
		this(0);
	}

	private Score(int score) {
		this.score = score;
	}

	public static Score valueOf(int number) {
		Score score = CACHE.get(number);

		if (Objects.isNull(score)) {
			score = new Score(number);
		}
		return score;
	}

	public static Score valueOf(Symbol symbol) {
		return CACHE.get(symbol.getSymbol());
	}

	public Score add(Score score) {
		return new Score(this.score + score.score);
	}
}
