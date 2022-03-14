package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Deck {
	private static final String EMPTY_MESSAGE = "[ERROR] 덱의 카드가 다 소진되었습니다.";
	private final Stack<Card> cards;

	public Deck() {
		cards = new Stack<>();
		Stream.of(CardDenomination.values())
			.forEach(cardLetter -> Stream.of(CardSuit.values())
				.forEach(type -> cards.push(new Card(cardLetter, type)))
			);
	}

	private Card distributeCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException(EMPTY_MESSAGE);
		}
		return cards.pop();
	}

	public int getCardsSize() {
		return cards.size();
	}

	public void shuffleDeck() {
		Collections.shuffle(cards);
	}

	public List<Card> distributeCards(int amount) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			cards.add(distributeCard());
		}
		return cards;
	}
}
