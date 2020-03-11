package blackjack.domain.card;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CardRepository {
	private static final List<Card> CARDS;

	static {
		CARDS = create();
	}

	private static List<Card> create() {
		return Arrays.stream(Symbol.values())
			.flatMap(CardRepository::createByType)
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	private static Stream<Card> createByType(Symbol symbol) {
		return Arrays.stream(Type.values())
			.map(type -> new Card(symbol, type));
	}

	public static List<Card> cards() {
		return CARDS;
	}
}
