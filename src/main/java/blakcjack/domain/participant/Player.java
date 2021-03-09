package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import static blakcjack.domain.card.Cards.BLACKJACK_VALUE;
import static blakcjack.domain.outcome.Outcome.*;

public class Player extends Participant {
	private final int bettingAmount;

	public Player(final Name name) {
		this(name, 0);
	}

	public Player(final Name name, final int bettingAmount) {
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

	public Outcome judgeOutcomeByBust() {
		if (isBust()) {
			return LOSE;
		}
		return WIN;
	}

	public float calculateProfit(final Outcome outcome) {
		return bettingAmount * outcome.getEarningRate();
	}
}
