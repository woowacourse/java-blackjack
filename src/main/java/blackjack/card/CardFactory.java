package blackjack.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.card.component.CardNumber;
import blackjack.card.component.Symbol;

public final class CardFactory {

	private final Stack<Card> blackjackCards;

	private CardFactory() {
		List<Card> cards = Arrays.stream(Symbol.values())
			.flatMap(this::makeCard)
			.collect(Collectors.toList());
		Collections.shuffle(cards);

		this.blackjackCards = new Stack<>();
		this.blackjackCards.addAll(cards);
	}

	public static CardFactory getInstance() {
		return new CardFactory();
	}

	private Stream<Card> makeCard(Symbol symbol) {
		return Arrays.stream(CardNumber.values())
			.map(number -> Card.of(symbol, number));
	}

	public Card drawCard() {
		checkEmpty();
		return blackjackCards.pop();
	}

	private void checkEmpty() {
		if (blackjackCards.isEmpty()) {
			throw new NoSuchElementException("모든 패를 뽑았습니다.");
		}
	}
}
