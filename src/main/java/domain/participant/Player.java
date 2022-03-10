package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.result.Versus;

public class Player extends Participant {
	
	public Player(Name name, List<Card> hand) {
		super(name, hand);
	}

	public Versus initCompare(boolean isDealerBlackJack) {
		boolean isParticipantBlackJack = isBlackJack();
		if (isDealerBlackJack && isParticipantBlackJack) {
			return Versus.DRAW;
		}
		if (!isDealerBlackJack && isParticipantBlackJack) {
			return Versus.WIN;
		}
		return Versus.LOSE;
	}

	public Versus finalCompare(Participant other) {
		if (isBust()) {
			return Versus.LOSE;
		}
		if (other.isBust()) {
			return Versus.WIN;
		}
		return judgeVersus(other.getBestScore());
	}

	private Versus judgeVersus(int otherScore) {
		int myScore = getBestScore();
		if (myScore > otherScore) {
			return Versus.WIN;
		}
		if (myScore < otherScore) {
			return Versus.LOSE;
		}
		return Versus.DRAW;
	}

	public boolean compareName(Name name) {
		return this.name.equals(name);
	}
}
