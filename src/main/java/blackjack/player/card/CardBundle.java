package blackjack.player.card;

import java.util.ArrayList;
import java.util.List;

public class CardBundle {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}
}
