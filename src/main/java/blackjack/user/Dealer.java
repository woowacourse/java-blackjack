package blackjack.user;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements User {
	private static final String NAME = "딜러";

	private List<Card> cards;

	private Dealer() {
		cards = new ArrayList<>();
	}

	public static Dealer create() {
		return new Dealer();
	}

	@Override
	public void append(Card card) {
		cards.add(card);
	}

	@Override
	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	@Override
	public String getName() {
		return NAME;
	}
}
