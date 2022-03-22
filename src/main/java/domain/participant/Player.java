package domain.participant;

import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class Player extends Participant {
	public Player(Name name, Hand hand,
		Betting betting) {
		super(name, hand, betting);
	}

	public boolean canDraw() {
		return !isBust() && !isBlackJack();
	}
}
