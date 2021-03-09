package blakcjack.domain.participant;

import blakcjack.domain.card.Cards;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import static blakcjack.domain.card.Cards.BLACKJACK_VALUE;
import static blakcjack.domain.outcome.Outcome.LOSE;
import static blakcjack.domain.outcome.Outcome.WIN;

public class Player extends Participant {
	public Player(final Name name) {
		super(name);
	}

	@Override
	public Cards getInitialHand() {
		return cards;
	}

	public boolean hasAffordableScoreForHit() {
		return cards.calculateScore() < BLACKJACK_VALUE;
	}

	public Outcome judgeOutcomeByBust() {
		if (this.isBust()) {
			return LOSE;
		}
		return WIN;
	}
}
