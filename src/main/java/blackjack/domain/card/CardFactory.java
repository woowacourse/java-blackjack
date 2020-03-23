package blackjack.domain.card;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class CardFactory {
	private static final Map<String, Card> CARD_CACHE;

	static {
		CARD_CACHE = Arrays.stream(Symbol.values())
			.flatMap(CardFactory::createBy)
			.collect(collectingAndThen(
				toMap(
					Card::toString,
					Function.identity()),
				Collections::unmodifiableMap));
	}

	private static Stream<Card> createBy(Symbol symbol) {
		return Arrays.stream(Type.values())
			.map(type -> new Card(symbol, type));
	}

	static Card pickUp(Symbol symbol, Type type) {
		return CARD_CACHE.getOrDefault(Card.name(symbol, type), new Card(symbol, type));
	}

	public static List<Card> create() {
		return new ArrayList<>(CARD_CACHE.values());
	}
}
