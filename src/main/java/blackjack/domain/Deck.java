package blackjack.domain;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Stream;

public class Deck {
	private static final String EMPTY_MESSAGE = "[ERROR] 덱의 카드가 다 소진되었습니다.";
	private static Stack<Card> cards;

	public static Card distributeCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException(EMPTY_MESSAGE);
		}
		return cards.pop();
	}

	public static void generateDeck() {
		cards = new Stack<>();
		Stream.of(CardLetter.values())
			.forEach(cardLetter -> Stream.of(CardSuit.values())
				.forEach(type -> cards.add(new Card(cardLetter, type)))
			);
	}

	public static int getCardsSize() {
		return cards.size();
	}

	public static void shuffleDeck() {
		Collections.shuffle(cards);
	}
}
