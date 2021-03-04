package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.name.Name;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {
	private static final int DEALER_MAXIMUM_DRAW_CRITERION = 17;
	private static final int FIRST_CARD_POSITION = 0;
	public static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	public List<Card> getFirstCard() {
		return Collections.singletonList(getCards().get(FIRST_CARD_POSITION));
	}

	public boolean isScoreLowerThanMaximumDrawCriterion() {
		return calculateScore() < DEALER_MAXIMUM_DRAW_CRITERION;
	}
}
