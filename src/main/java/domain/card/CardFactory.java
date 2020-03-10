package domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {
	public static List<Card> create() {
		return Stream.of(CardSuit.values())
			.flatMap(CardFactory::createCardBySuit)
			.collect(Collectors.toList());
	}

	private static Stream<Card> createCardBySuit(CardSuit suit) {
		return Stream.of(CardNumber
				.values())
			.map(number -> new Card(suit, number));
	}
}
