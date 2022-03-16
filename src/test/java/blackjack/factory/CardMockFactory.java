package blackjack.factory;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardMockFactory {

	private static final Map<String, Card> cards;

	static {
		cards = Arrays.stream(Denomination.values())
				.map(denomination -> new Card(Suit.CLOVER, denomination))
				.collect(Collectors.toMap(Card::getDenominationAndSuit, Function.identity()));
	}

	public static Card of(final String information) {
		return cards.get(information);
	}
}
