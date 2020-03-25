package blackjack.domain.testAssistant;

import blackjack.domain.card.*;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFactory {

	private TestFactory() {
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
				.map(TestFactory::createCard)
				.collect(Collectors.toList());
	}

	/**
	 * @param strings 예시) {"ACE,HEART", "ONE,SPADE", "TWO,CLUB"}
	 */
	public static Deck createDeck(String... strings) {
		return Deck.of(createCards(strings));
	}

	public static Name createName(String string) {
		return new Name(string);
	}

	public static Money createMoney(String string) {
		return Money.of(string);
	}

	/**
	 * @param strings 예시) {"ACE,HEART", "ONE,SPADE", "TWO,CLUB"}
	 */
	public static Hand createHand(String... strings) {
		return new Hand(createCards(strings));
	}

	public static Score createScore(int score) {
		return Score.of(score);
	}

	public static Dealer createDealer(String... strings) {
		return Dealer.of(createHand(strings));
	}

	public static Player createPlayer(String name, String money, String... hand) {
		return new Player(createName(name), createHand(hand), createMoney(money));
	}

	public static Player createPlayerByName(String name) {
		return new Player(createName(name), createHand(), createMoney("0"));
	}

	public static Player createPlayerByHand(String... hand) {
		return new Player(createName("그니"), createHand(hand), createMoney("0"));
	}

}
