package domain.gamer;

import domain.card.Hand;
import domain.rule.Rule;

import java.util.function.Function;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_FIRST_OPENED_COUNT = 1;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	@Override
	protected Function<Hand, Boolean> hitStrategy() {
		return Rule::canDealerHit;
	}

	@Override
	protected int firstOpenedCardsCount() {
		return DEALER_FIRST_OPENED_COUNT;
	}
}