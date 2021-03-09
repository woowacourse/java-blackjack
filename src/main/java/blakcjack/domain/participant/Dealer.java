package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import static blakcjack.domain.outcome.Outcome.DRAW;
import static blakcjack.domain.outcome.Outcome.LOSE;

public class Dealer extends Participant {
	public static final String DEALER_NAME = "딜러";
	private static final int DEALER_MAXIMUM_DRAW_CRITERION = 17;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	@Override
	public Cards getInitialHand() {
		Cards hand = new Cards();
		hand.add(cards.getFirstCard());
		return hand;
	}

	public boolean isScoreLowerThanMaximumDrawCriterion() {
		return cards.calculateScore() < DEALER_MAXIMUM_DRAW_CRITERION;
	}

	public Outcome judgeOutcomeOf(final Player player) {
		if (hasAnyBust(this, player)) {
			return player.judgeOutcomeByBust();
		}
		return judgeOutcomeByScore(player);
	}

	private Outcome judgeOutcomeByScore(final Player player) {
		final int playerScore = player.getScore();
		final int dealerScore = this.getScore();

		if (playerScore > dealerScore) {
			return player.getWinOutcome();
		}
		if (playerScore < dealerScore) {
			return LOSE;
		}
		return DRAW;
	}
}
