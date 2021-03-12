package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.name.Name;

public class Dealer extends Participant {
	public static final String DEALER_NAME = "딜러";
	private static final int DEALER_MAXIMUM_DRAW_CRITERION = 17;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	public boolean isScoreLowerThanMaximumDrawCriterion() {
		return cards.calculateScore() < DEALER_MAXIMUM_DRAW_CRITERION;
	}

	@Override
	public Cards getInitialHand() {
		return cards.getFirstCard();
	}
}
