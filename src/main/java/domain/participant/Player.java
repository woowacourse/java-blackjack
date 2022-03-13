package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.result.Versus;

public class Player extends Participant {

	public Player(Name name, List<Card> hand) {
		super(name, hand);
	}

	public Versus compareAtBlackJack(Participant other) {
		boolean isPlayerBlackJack = this.blackJack;
		boolean isOtherBlackJack = other.blackJack;
		if (isOtherBlackJack && isPlayerBlackJack) {
			return Versus.DRAW;
		}
		if (!isOtherBlackJack && isPlayerBlackJack) {
			return Versus.WIN;
		}
		return Versus.LOSE;
	}

	public Versus compareAtFinal(Participant other) {
		if (isBust()) {
			return Versus.LOSE;
		}
		if (this.blackJack || other.isBust()) {
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

	public boolean isNameMatch(Name name) {
		return this.name.equals(name);
	}
}
