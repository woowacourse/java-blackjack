package blackjack.factory;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardMockFactory {

	private static final Map<Denomination, Card> cards;

	static {
		cards = Arrays.stream(Denomination.values())
				.collect(Collectors.toMap(Function.identity(), CardMockFactory::createCard));
	}

	public static Card of(final Denomination denomination) {
		return cards.get(denomination);
	}

	private static Card createCard(Denomination denomination) {
		return new Card(Suit.CLOVER, denomination);
	}
}
