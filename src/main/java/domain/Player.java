package domain;

import java.util.List;

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
		return whoIsHigh(other.getBestScore());
	}

	private Versus whoIsHigh(int otherScore) {
		int myScore = getBestScore();
		if (myScore > otherScore) {
			return Versus.WIN;
		}
		if (myScore == otherScore) {
			return Versus.DRAW;
		}
		return Versus.LOSE;
	}

	public boolean compareName(Name name) {
		return this.name.equals(name);
	}
}
