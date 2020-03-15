package blackjack.domain.card;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CardFactory {
	private static final List<Card> CARDS;

	static {
		CARDS = Arrays.stream(Symbol.values())
			.flatMap(CardFactory::createBy)
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	private static Stream<Card> createBy(Symbol symbol) {
		return Arrays.stream(Type.values())
			.map(type -> new Card(symbol, type));
	}

	public static List<Card> create() {
		return new ArrayList<>(CARDS);
	}
}
