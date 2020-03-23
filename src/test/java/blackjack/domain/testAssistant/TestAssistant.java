package blackjack.domain.testAssistant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestAssistant {

	private TestAssistant() {
	}

	/**
	 * @param string 예시 "ACE,HEART"
	 */
	public static Card createCard(String string) {
		List<String> symbolAndType = Stream.of(string.split(","))
				.map(String::trim)
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		Symbol symbol = Symbol.valueOf(symbolAndType.get(0));
		Type type = Type.valueOf(symbolAndType.get(1));

		return Card.of(symbol, type);
	}

	/**
	 * @param strings 예시) {"ACE,HEART", "ONE,SPADE", "TWO,CLUB"}
	 */
	public static List<Card> createCards(String... strings) {
		return Stream.of(strings)
				.map(TestAssistant::createCard)
				.collect(Collectors.toList());
	}

	/**
	 * @param strings 예시) {"ACE,HEART", "ONE,SPADE", "TWO,CLUB"}
	 */
	public static Deck createDeck(String... strings) {
		return Deck.of(createCards(strings));
	}
}
