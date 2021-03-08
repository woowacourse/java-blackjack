package blakcjack.domain.participant;

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
	public String getInitialHand() {
		return cards.getConcatenatedCards();
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
