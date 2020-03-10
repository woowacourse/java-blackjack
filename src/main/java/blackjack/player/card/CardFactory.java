package blackjack.player.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public final class CardFactory {

	private final Stack<Card> blackjackCards;

	private CardFactory() {
		this.blackjackCards = new Stack<>();
		List<Card> cards = Card.getCardCache();
		Collections.shuffle(cards);
		this.blackjackCards.addAll(cards);
	}

	public static CardFactory getInstance() {
		return new CardFactory();
	}

	public Card drawCard() {
		return blackjackCards.pop();
	}
}
