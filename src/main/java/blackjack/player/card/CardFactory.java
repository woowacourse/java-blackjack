package blackjack.player.card;

import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	private Stream<Card> makeCard(Symbol symbol) {
		return Arrays.stream(CardNumber.values())
				.map(number -> Card.of(symbol, number));
	}

	public static CardFactory getInstance() {
		return new CardFactory();
	}

	public Card drawCard() {
		return blackjackCards.pop();
	}

}
