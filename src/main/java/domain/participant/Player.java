package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.result.WinOrLose;

public class Player extends Participant {

	public Player(Name name, List<Card> hand) {
		super(name, hand);
	}

	public WinOrLose compareAtBlackJack(Participant other) {
		boolean isPlayerBlackJack = isBlackJack();
		boolean isOtherBlackJack = other.isBlackJack();
		if (isOtherBlackJack && isPlayerBlackJack) {
			return WinOrLose.DRAW;
		}
		if (!isOtherBlackJack && isPlayerBlackJack) {
			return WinOrLose.WIN;
		}
		return WinOrLose.LOSE;
	}

	public WinOrLose compareAtFinal(Participant other) {
		if (isBust()) {
			return WinOrLose.LOSE;
		}
		if (isBlackJack() || other.isBust()) {
			return WinOrLose.WIN;
		}
		return judgeVersus(other.getBestScore());
	}

	private WinOrLose judgeVersus(int otherScore) {
		int myScore = getBestScore();
		if (myScore > otherScore) {
			return WinOrLose.WIN;
		}
		if (myScore < otherScore) {
			return WinOrLose.LOSE;
		}
		return WinOrLose.DRAW;
	}

	public boolean isNameMatch(Name name) {
		return this.name.equals(name);
	}
}
