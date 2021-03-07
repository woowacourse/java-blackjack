package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import java.util.Collections;
import java.util.List;

import static blakcjack.domain.outcome.Outcome.*;

public class Dealer extends Participant {
	public static final String DEALER_NAME = "딜러";
	private static final int DEALER_MAXIMUM_DRAW_CRITERION = 17;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	public List<Card> getFirstCard() {
		return Collections.singletonList(cards.getFirstCard());
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
		final int dealerScore = cards.calculateScore();

		if (playerScore > dealerScore) {
			return WIN;
		}
		if (playerScore < dealerScore) {
			return LOSE;
		}
		return DRAW;
	}
}
