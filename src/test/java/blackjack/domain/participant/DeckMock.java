package blackjack.domain.participant;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class DeckMock extends Deck {

	private final Queue<Card> deck;

	public DeckMock(final List<Card> cards) {
		deck = new LinkedList<>();
		deck.addAll(cards);
	}

	@Override
	public Card draw() {
		return deck.poll();
	}
}
