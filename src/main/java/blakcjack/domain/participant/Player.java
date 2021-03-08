package blakcjack.domain.participant;

import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;

import static blakcjack.domain.outcome.Outcome.LOSE;
import static blakcjack.domain.outcome.Outcome.WIN;

public class Player extends Participant {
	public Player(final Name name) {
		super(name);
	}

	@Override
	public String getInitialHandInformation() {
		return super.getCards()
				.getConcatenatedCardsInformation();
	}

	public Outcome judgeOutcomeByBust() {
		if (this.isBust()) {
			return LOSE;
		}
		return WIN;
	}
}
