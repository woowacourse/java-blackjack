package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardRepository {
	private static final List<Card> cards;

	static {
		cards = Arrays.stream(Symbol.values())
				.flatMap(CardRepository::mapToCardByType)
				.collect(Collectors.toList());
	}

	private static Stream<Card> mapToCardByType(Symbol symbol) {
		return Arrays.stream(Type.values())
				.map(type -> new Card(symbol, type));
	}

	public static List<Card> toList() {
		return Collections.unmodifiableList(cards);
	}
}
