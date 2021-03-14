package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

public class Player extends Participant {
	private final Money bettingAmount;

	public Player(final Name name, final Money bettingAmount) {
		super(name);
		this.bettingAmount = bettingAmount;
	}

	public boolean hasAffordableScoreForHit() {
		return cards.isScoreLowerThanBlackjackScore();
	}

	public Money calculateProfit(final Outcome outcome) {
		final float earningRate = outcome.getEarningRate();
		return bettingAmount.calculateProfit(earningRate);
	}

	@Override
	public Cards getInitialHand() {
		return cards.getFirstTwoCards();
	}
}
