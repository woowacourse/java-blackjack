package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.money.Money;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import static blakcjack.domain.card.Cards.BLACKJACK_VALUE;

public class Player extends Participant {
	private final Money bettingAmount;

	public Player(final Name name, final Money bettingAmount) {
		super(name);
		this.bettingAmount = bettingAmount;
	}

	@Override
	public Cards getInitialHand() {
		return cards;
	}

	public boolean hasAffordableScoreForHit() {
		return cards.calculateScore() < BLACKJACK_VALUE;
	}

	public Money calculateProfit(final Outcome outcome) {
		return bettingAmount.calculateProfit(outcome);
	}
}
