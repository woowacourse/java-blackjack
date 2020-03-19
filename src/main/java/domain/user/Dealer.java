package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.strategy.draw.DealerDrawStrategy;

import java.util.Collections;
import java.util.List;

public class Dealer extends User {
	private static final int FIRST_CARD_INDEX = 0;

	public Dealer() {
		drawStrategy = new DealerDrawStrategy();
	}

	@Override
	public Cards openInitialCards() {
		return new Cards(Collections.singletonList(hands.toList().get(FIRST_CARD_INDEX)));
	}

	@Override
	public String toString() {
		return "딜러";
	}
}
