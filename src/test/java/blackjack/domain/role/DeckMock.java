package blackjack.domain.role;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class DeckMock extends Deck {

	private final Queue<Card> deck;

	public DeckMock(final List<Card> cards) {
		deck = new LinkedList<>();
		for (Card card : cards) {
			System.out.println(card.getInformation());
			deck.add(card);
		}
	}

	@Override
	public Card draw() {
		return deck.poll();
	}
}
